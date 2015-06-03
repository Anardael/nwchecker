package com.nwchecker.server.controller;

import com.nwchecker.server.exceptions.ContestAccessDenied;
import com.nwchecker.server.json.ErrorMessage;
import com.nwchecker.server.json.ValidationResponse;
import com.nwchecker.server.model.Contest;
import com.nwchecker.server.model.Task;
import com.nwchecker.server.model.TaskData;
import com.nwchecker.server.service.ContestService;
import com.nwchecker.server.service.TaskService;
import com.nwchecker.server.validators.TaskValidator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * <h1>Task Controller</h1> This spring controller contains mapped methods, that
 * allows teacher to create, edit and remove tasks from edited contest.
 * <p>
 * <b>Note:</b>Only teacher allows to create and change tasks. Other users can
 * only view tasks information
 *
 * @author Roman Zayats
 * @version 1.0
 */
@Controller
public class TaskController {
	private final long MAX_FILE_SIZE_BYTE = 20971520;

	private static final Logger LOG = Logger.getLogger(TaskController.class);

	@Autowired
	private TaskService taskService;
	@Autowired
	private ContestService contestService;
	@Autowired
	private MessageSource messageSource;
	@Autowired
	private TaskValidator taskValidator;

	/**
	 * This mapped method used to receive new task data and create new task.
	 * <p>
	 * <b>Note:</b>Only TEACHER has rights to use this method.
	 *
	 * @param contestId
	 *            ID of contest that task belongs to
	 * @param principal
	 *            This is general information about user, who tries to call this
	 *            method
	 * @param request
	 *            HttpServletRequest object
	 * @param task
	 *            Data set that contains new information about new task
	 * @param result
	 *            General spring interface that used in data validation
	 * @return Status "SUCCESS" or "FAIL"
	 * @throws MaxUploadSizeExceededException
	 *             The size of uploading file is higher than allows
	 * @throws IOException
	 *             Error occurred while sending/receiving file
	 */
	@PreAuthorize("hasRole('ROLE_TEACHER')")
	@RequestMapping(value = "/newTaskJson.do", method = RequestMethod.POST)
	public @ResponseBody ValidationResponse newTaskJson(
			@RequestParam("contestId") int contestId, Principal principal,
			MultipartHttpServletRequest request,
			@ModelAttribute(value = "task") Task task, BindingResult result)
			throws MaxUploadSizeExceededException, IOException {
		if (!contestService.checkIfUserHaveAccessToContest(principal.getName(),
				contestId)) {
			throw new ContestAccessDenied(principal.getName()
					+ " tried to edit Contest. Access denied.");
		}
		// Json response object:
		ValidationResponse res = ValidationResponse.createValidationResponse();
		// validation in new TaskValidator:
		taskValidator.validate(task, result);
		// if there are errors:
		if (result.hasErrors()) {
			LOG.info("Task validation failed.");
			// set json status FAIL:
			res.setStatus("FAIL");
			List<FieldError> allErrors = result.getFieldErrors();
			List<ErrorMessage> errorMessages = new ArrayList<ErrorMessage>();
			for (FieldError objectError : allErrors) {
				errorMessages.add(ErrorMessage.createErrorMessage(objectError
						.getField(), messageSource.getMessage(objectError,
						LocaleContextHolder.getLocale())));
			}
			// set all errors:
			res.setErrorMessageList(errorMessages);
		} else {
			LOG.info("Task validation passed.");
			// if validation passed:
			res.setStatus("SUCCESS");
			// I/O files:
			Iterator<String> itr = request.getFileNames();
			LinkedList<TaskData> data = new LinkedList<TaskData>();
			while (itr.hasNext()) {
				TaskData newData = new TaskData();
				MultipartFile mpf = request.getFile(itr.next());
				newData.setInputData(mpf.getBytes());
				
				mpf = request.getFile(itr.next());
				newData.setOutputData(mpf.getBytes());
				newData.setTask(task);
				data.add(newData);
				// check if size is less than 20mb:

				if (newData.getInputData().length > MAX_FILE_SIZE_BYTE
						|| newData.getOutputData().length > MAX_FILE_SIZE_BYTE) {
					throw new MaxUploadSizeExceededException(1);
				}
			}
			// set i/o data to task:
			task.setInOutData(data);
			// get Contest for this Task:
			Contest c = contestService.getContestByID(contestId);
			// if Task is new:
			if (task.getId() == 0) {
				c.getTasks().add(task);
				task.setContest(c);
				// set contest for task(set foreign key for Contest):
				taskService.addTask(task);
			} else {
				// get task data files available:
				List<TaskData> availableData = taskService.getTaskById(
						task.getId()).getInOutData();
				task.getInOutData().addAll(availableData);
				task.setContest(c);
				taskService.updateTask(task);
			}
			LOG.info("Task have been successfully saved.");
			res.setResult(String.valueOf(task.getId()));
		}
		return res;
	}

	/**
	 * This mapped method used to delete task from contest.
	 * <p>
	 * <b>Note:</b>Only TEACHER has rights to use this method.
	 *
	 * @param contestId
	 *            ID of contest that task belongs to
	 * @param principal
	 *            This is general information about user, who tries to call this
	 *            method
	 * @param taskId
	 *            ID of task that will be removed
	 * @return Status "SUCCESS" or "FAIL"
	 */
	@PreAuthorize("hasRole('ROLE_TEACHER')")
	@RequestMapping(value = "/deleteTaskJson.do", method = RequestMethod.GET)
	public @ResponseBody ValidationResponse processDeleteTaskJson(
			@RequestParam("contestId") int contestId, Principal principal,
			@RequestParam("taskId") int taskId) {
		if (!contestService.checkIfUserHaveAccessToContest(principal.getName(),
				contestId)) {
			throw new ContestAccessDenied(principal.getName()
					+ " tried to edit Contest. Access denied.");
		}
		Contest c = contestService.getContestByID(contestId);
		for (int i = 0; i < c.getTasks().size(); i++) {
			if (c.getTasks().get(i).getId() == taskId) {
				c.getTasks().remove(i);
			}
		}
		contestService.updateContest(c);
		LOG.info("Task (id=" + taskId + ") have been successfully deleted.");
		return ValidationResponse.createValidationResponse("SUCCESS");
	}

	/**
	 * This mapped method used to return new modal form for task edit.
	 * <p>
	 * <b>Note:</b>Only TEACHER has rights to use this method.
	 *
	 * @param model
	 *            Spring Framework model for this page
	 * @param taskId
	 *            ID of task
	 * @param contestId
	 *            ID of contest that task belongs to
	 * @return <b>createNewTaskForm.jsp</b> Returns modal form for task edit
	 */
	@PreAuthorize("hasRole('ROLE_TEACHER')")
	@RequestMapping(value = "/newTaskForm.do", method = RequestMethod.GET)
	public String newTaskFormJson(Model model,
			@RequestParam("taskId") int taskId,
			@RequestParam("contestId") int contestId) {
		Contest c = new Contest();
		List<Task> tasks = new LinkedList<Task>();
		Task t = new Task();
		t.setInputFileName("in");
		t.setOutputFileName("out");
		tasks.add(t);
		c.setTasks(tasks);
		model.addAttribute("contestId", contestId);
		model.addAttribute("taskIndex", taskId);
		model.addAttribute("contest", c);
		return "fragments/createNewTaskForm";
	}

	/**
	 * This mapped method used to receive task test data.
	 * <p>
	 * <b>Note:</b>Only TEACHER has rights to use this method.
	 *
	 * @param contestId
	 *            ID of contest
	 * @param principal
	 *            This is general information about user, who tries to call this
	 *            method
	 * @param testId
	 *            ID of test
	 * @param type
	 *            Type of test data
	 * @param response
	 *            HttpServletResponse object
	 * @throws IOException
	 *             Error occurred while sending/receiving file
	 */
	@PreAuthorize("hasRole('ROLE_TEACHER')")
	@RequestMapping(value = "/getTaskTestData", method = RequestMethod.GET)
	public void getFile(@RequestParam("contestId") int contestId,
			Principal principal, @RequestParam("testId") int testId,
			@RequestParam("type") String type, HttpServletResponse response)
			throws IOException {
		if (!contestService.checkIfUserHaveAccessToContest(principal.getName(),
				contestId)) {
			throw new ContestAccessDenied(principal.getName()
					+ " tried to edit Contest. Access denied.");
		}
		// first of all: find test file:
		TaskData data = taskService.getTaskData(testId);
		ByteArrayInputStream stream = null;
		if (type != null && type.equals("in")) {
			stream = new ByteArrayInputStream(data.getInputData());
		}
		if (type != null && type.equals("out")) {
			stream = new ByteArrayInputStream(data.getOutputData());
		}

		response.setContentType("application/txt");
		response.setHeader("Content-Disposition", "attachment; filename="
				+ (type.equals("in") ? "in" : "out") + "_id" + testId + ".txt");
		org.apache.commons.io.IOUtils.copy(stream, response.getOutputStream());
		response.flushBuffer();
	}

	/**
	 * This mapped method used to return list of task tests.
	 * <p>
	 * <b>Note:</b>Only TEACHER has rights to use this method.
	 *
	 * @param contestId
	 *            ID of contest that task belongs to
	 * @param principal
	 *            This is general information about user, who tries to call this
	 *            method
	 * @param taskId
	 *            ID of task
	 * @param localTaskId
	 *            Task ID that used in current page
	 * @param model
	 *            Spring Framework model for this page
	 * @return Returns list of task tests
	 */
	@PreAuthorize("hasRole('ROLE_TEACHER')")
	@RequestMapping(value = "/getAvailableTests", method = RequestMethod.GET)
	public String getTestFiles(@RequestParam("contestId") int contestId,
			Principal principal, @RequestParam("taskId") int taskId,
			@RequestParam("localTaskId") int localTaskId, Model model) {
		if (!contestService.checkIfUserHaveAccessToContest(principal.getName(),
				contestId)) {
			throw new ContestAccessDenied(principal.getName()
					+ " tried to edit Contest. Access denied.");
		}
		Task t = taskService.getTaskById(taskId);
		model.addAttribute("taskData", t.getInOutData());
		model.addAttribute("taskId", localTaskId);
		model.addAttribute("contestId", contestId);
		return "fragments/taskDataView";

	}

	/**
	 * This mapped method used to delete test task file.
	 * <p>
	 * <b>Note:</b>Only TEACHER has rights to use this method.
	 *
	 * @param contestId
	 *            ID of contest
	 * @param principal
	 *            This is general information about user, who tries to call this
	 *            method
	 * @param testId
	 *            ID of test
	 * @return Status "SUCCESS" or "FAIL"
	 */
	@PreAuthorize("hasRole('ROLE_TEACHER')")
	@RequestMapping(value = "/deleteTaskTestFile", method = RequestMethod.GET)
	public @ResponseBody ValidationResponse deleteTestFile(
			@RequestParam("contestId") int contestId, Principal principal,
			@RequestParam("taskTestId") int testId) {
		if (!contestService.checkIfUserHaveAccessToContest(principal.getName(),
				contestId)) {
			throw new ContestAccessDenied(principal.getName()
					+ " tried to edit Contest. Access denied.");
		}
		// delete taskData:
		taskService.deleteTaskData(testId);
		return ValidationResponse.createValidationResponse("SUCCESS");
	}
}
