package com.nwchecker.server.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.nwchecker.server.model.Task;
import com.nwchecker.server.util.ImagesUploader;
import com.nwchecker.server.util.SessionFactoryUtil;

public class TaskDAO {
	
	public static int addTask(Task task, String imagesDomain) {
		task = uploadTaskImages(task, imagesDomain);
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		session.beginTransaction();
		int id = (int) session.save(task);
		session.getTransaction().commit();
		session.close();
		return id;
	}
	
	private static Task uploadTaskImages(Task task, String imagesDomain) {
		ImageTagUrlParser parser = new ImageTagUrlParser();
		parser.setString(task.getDescription());
		parser = parseAndUploadImages(parser, imagesDomain);
		task.setDescription(parser.getString());
		return task;
	}
	
	private static ImageTagUrlParser parseAndUploadImages(ImageTagUrlParser parser, String imagesDomain) {
		while (!parser.isEnd()) {
			String imageUrl = parser.nextUrl();
			if (imageUrl != "") {
				Integer imageId = ImagesUploader.uploadImage(imageUrl);
				parser.replaceUrl(imagesDomain + "?ImageID=" +imageId.toString());
			}
		}
		return parser;
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
		if (deleteTaskImages(task)) {
			Session session = SessionFactoryUtil.getSessionFactory().openSession();
			Query query = session.createQuery("DELETE FROM Task WHERE id = :id");
			query.setParameter("id", task.getId());
			int result = query.executeUpdate();
			session.close();
			return (result >= 1);
		}
		return false;
	}
	
	private static boolean deleteTaskImages(Task task) {
		ImageTagUrlParser parser = new ImageTagUrlParser();
		parser.setString(task.getDescription());
		return parseAndDeleteImages(parser);
	}
	
	private static boolean parseAndDeleteImages(ImageTagUrlParser parser) {
		while (!parser.isEnd()) {
			String imageUrl = parser.nextUrl();
			if (imageUrl != "") {
				String imageID = 
						imageUrl.substring(imageUrl.lastIndexOf('=') + 1, imageUrl.length());
				if (!ImageDAO.deleteImageById(new Integer(imageID))) {
					return false;
				}
			}
		}
		return true;
	}
}
