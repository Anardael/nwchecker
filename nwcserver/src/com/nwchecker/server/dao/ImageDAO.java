package com.nwchecker.server.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.nwchecker.server.model.Image;
import com.nwchecker.server.util.SessionFactoryUtil;

public class ImageDAO {
	
	public static int addImage(Image image) {
		image = compressImage(image);
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		session.beginTransaction();
		int id = (int) session.save(image);
		session.getTransaction().commit();
		session.close();
		return id;
	}
	
	private static Image compressImage(Image image) {
		//TODO
		return image;
	}
	
	public static Image getImageById(int imageId) {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		Query query = session.createQuery("FROM Image WHERE id = :id");
		query.setParameter("id", imageId);
		List<?> queryResult = query.list();
		session.close();
		Image image = null;
		if (queryResult.size() >= 1) {
			image = (Image) queryResult.get(0);
			image = decompressImage(image);
		}
		return image;
	}
	
	private static Image decompressImage(Image image) {
		//TODO
		return image;
	}
	
	public static boolean deleteImageById(int imageId) {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		Query query = session.createQuery("DELETE FROM Image WHERE id = :id");
		query.setParameter("id", imageId);
		int result = query.executeUpdate();
		session.close();
		return (result >= 1);
	}
}
