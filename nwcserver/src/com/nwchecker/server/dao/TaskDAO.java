package com.nwchecker.server.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.nwchecker.server.model.Task;
import com.nwchecker.server.util.ImageTagUrlParser;
import com.nwchecker.server.util.SessionFactoryUtil;

public class TaskDAO {
	
	public static int addTask(Task task) {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		session.beginTransaction();
		int id = (int) session.save(task);
		session.getTransaction().commit();
		session.close();
		return id;
	}
	
	public static Task getTaskById(int taskId) {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		Query query = session.createQuery("FROM Task WHERE id = :id");
		query.setParameter("id", taskId);
		List<?> queryResult = query.list();
		session.close();
		Task task = null;
		if (queryResult.size() >= 1) {
			task = (Task) queryResult.get(0);
		}
		return task;
	}
	
	public static boolean deleteTask(Task task) {
		if (deleteTaskDescImages(task.getDescription())) {
			Session session = SessionFactoryUtil.getSessionFactory().openSession();
			Query query = session.createQuery("DELETE FROM Task WHERE id = :id");
			query.setParameter("id", task.getId());
			int result = query.executeUpdate();
			session.close();
			return (result >= 1);
		}
		return false;
	}
	
	private static boolean deleteTaskDescImages(String taskDesc) {
		ImageTagUrlParser parser = new ImageTagUrlParser();
		parser.setString(taskDesc);
		while (!parser.isEnd()) {
			String imageUrl = parser.nextUrl();
			if (imageUrl != "") {
				String imageID = imageUrl.substring(imageUrl.lastIndexOf('=') + 1,
													imageUrl.length());
				int id = Integer.parseInt(imageID);
				if (ImageDAO.deleteImageById(id)) {
					return false;
				}
			}
		}
		return true;
	}
}
