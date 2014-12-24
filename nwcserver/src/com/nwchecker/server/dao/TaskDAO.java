package com.nwchecker.server.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.nwchecker.server.model.Task;
import com.nwchecker.server.util.SessionFactoryUtil;

public class TaskDAO {

	public static void addTask(Task task) {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(task);
		session.getTransaction().commit();
		session.close();
	}
	
	public static List<Task> getAllTasks() {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		Query query = session.createQuery("FROM Task");
		List<Task> list = (List<Task>) query.list();
		session.close();
		return list;
	}
}
