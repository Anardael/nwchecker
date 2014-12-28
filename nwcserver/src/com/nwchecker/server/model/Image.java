package com.nwchecker.server.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Images")
public class Image {

	@Id
	@Column(name="image_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(name="image_data")
	private byte[] image;
	
	public Image() {
		this.id = 0;
		this.image = null;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
}
