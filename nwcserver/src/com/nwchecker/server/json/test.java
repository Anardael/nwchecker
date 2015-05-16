/*package com.nwchecker.server.json;

import java.util.ArrayList;
import java.util.List;

import com.nwchecker.server.model.Task;

public class test {
	public static void main(String[] args) {
		Task task1 = new Task();
		task1.setTitle("I am title 1");
		task1.setComplexity(5);
		task1.setRate(123);
		task1.setDescription("this is a description.");
		Task task2 = new Task();
		task2.setTitle("I am title 2");
		task2.setRate(123);
		task2.setDescription("this is a description.");
		Task task3 = new Task();
		task3.setTitle("I am title 3");
		task3.setComplexity(5);
		task3.setRate(123);
		task3.setDescription("this is a description.");
		List<Task> tasks = new ArrayList<Task>();
		tasks.add(task1);
		tasks.add(task2);
		tasks.add(task3);
		List<TaskJson> th = JsonUtil.createJsonList(TaskJson.class, tasks);
		for (TaskJson t : th) {
			System.out.println(t.getTitle() + " " + t.getDescription());
		}
	}
}
*/