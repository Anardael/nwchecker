package com.nwchecker.server.controller;

import com.nwchecker.server.breadcrumb.annotations.Link;
import com.nwchecker.server.dao.CompilerDAO;
import com.nwchecker.server.model.Contest;
import com.nwchecker.server.model.ContestPass;
import com.nwchecker.server.model.Task;
import com.nwchecker.server.model.User;
import com.nwchecker.server.service.ContestPassService;
import com.nwchecker.server.service.ContestService;
import com.nwchecker.server.service.TaskPassService;
import com.nwchecker.server.service.TaskService;
import com.nwchecker.server.service.UserService;

import java.io.IOException;
import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * <h1>Contest Pass Controller</h1> This spring controller contains mapped
 * methods, that allows users to pass contests and view contests results.
 * <p>
 * <b>Note:</b>Only USERs allows to took part in contests.
 *
 * @author Roman Zayats
 * @author Stanislav Krasovskyi
 * @version 1.0
 * @since 2015-03-03
 */
@Controller("contestPassController")
public class ContestPassController {
	//Maximum upload file size
	private final long MAX_FILE_SIZE_BYTE = 20971520;

	@Autowired
	private UserService userService;
	@Autowired
	private TaskService taskService;
	@Autowired
	private ContestPassService contestPassService;
	@Autowired
	private CompilerDAO compilerService;
	@Autowired
	private TaskPassService taskPassService;
	@Autowired
	private ContestService contestService;

	@Link(label = "task.caption", family = "contests", parent = "contest.caption")
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/passContest", method = RequestMethod.GET)
	public String getContestForPass(Principal principal, @RequestParam("id") int contestId, Model model) {
		Contest currentContest = contestService.getContestByID(contestId);

		// if not redirect from getTaskForPass method
		if (!model.containsAttribute("currentTask")) {
			Task firstTaskCurrentContest = currentContest.getTasks().get(0);
			model.addAttribute("taskSuccessRate", taskPassService .getTaskSuccessRateById(firstTaskCurrentContest.getId()));
			model.addAttribute("currentTask", firstTaskCurrentContest);
		}
		model.addAttribute("contest", currentContest);
		model.addAttribute("isArchive", (currentContest.getStatus() == Contest.Status.ARCHIVE));
		model.addAttribute("contestEndTimeGTM", contestService.getContestEndTime(currentContest));
		model.addAttribute( "taskResults", contestPassService.getTaskResultsForContestByUserName(principal.getName(), currentContest));
		model.addAttribute("compilers", compilerService.getAllCompilers());

		return "nwcserver.tasks.pass";
	}

	/**
	 * This mapped method used to return page that allows user to pass contest.
	 * <p>
	 * <b>Note:</b>Only USER has rights to use this method.
	 *
	 * @param principal
	 *            This is general information about user, who tries to call this
	 *            method
	 * @param taskId
	 *            Id of selected task
	 * @param model
	 *            Spring Framework model for this page
	 * @return Page when user can continue passing contest if <b>success</b>.
	 *         Page <b>accessDenied403.jsp</b> if <b>fails</b>.
	 */
	@Link(label = "task.caption", family = "contests", parent = "contest.caption")
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/passTask", method = RequestMethod.GET)
	public String getTaskForPass(Principal principal,
			@RequestParam("id") int taskId, Model model) {
		Task currentTask = taskService.getTaskById(taskId);

		model.addAttribute("taskSuccessRate",
				taskPassService.getTaskSuccessRateById(taskId));
		model.addAttribute("currentTask", currentTask);

		return getContestForPass(principal, currentTask.getContest().getId(),
				model);
	}

	/**
	 * This mapped method used to receive user's answer for one task, send this
	 * data to <b>checker</b>, receive answer and return it to user.
	 * <p>
	 * <b>Note:</b>Only USER has rights to use this method.
	 *
	 * @param principal
	 *            This is general information about user, who tries to call this
	 *            method
	 * @param taskId
	 *            ID of task that submits
	 * @param compilerId
	 *            ID of compiler that must be used to by checker
	 * @param file
	 *            File that contains user's source code that get solution of
	 *            task problem
	 * @return Result data
	 * @throws IOException
	 *             If problems occurs while file sending/receiving
	 * @throws ClassNotFoundException
	 */
	@RequestMapping(value = "/submitTask", method = RequestMethod.POST)
	@PreAuthorize("hasRole('ROLE_USER')")
	public @ResponseBody Map<String, Object> submitTask(Principal principal,
			@RequestParam(value = "id") int taskId,
			@RequestParam(value = "compilerId") int compilerId,
			@RequestParam("file") MultipartFile file) throws IOException, ClassNotFoundException {
		Map<String, Object> result = new LinkedHashMap<>();
		if (file.getSize() > MAX_FILE_SIZE_BYTE) {
			result.put("fileTooLarge", true);
			return result;
		}
		Task task = taskService.getTaskById(taskId);
		User user = userService.getUserByUsername(principal.getName());
		// check access:
		ContestPass contestPass = null;
		if (task.getContest().getStatus() == Contest.Status.GOING) {
			// check if user contains contestPass:
			for (ContestPass c : user.getContestPassList()) {
				if (c.getContest().equals(task.getContest())) {
					contestPass = c;
				}
			}
		}
		result = contestPassService.checkTask(contestPass, task, compilerId, file.getBytes(), user);
		return result;
	}
}
