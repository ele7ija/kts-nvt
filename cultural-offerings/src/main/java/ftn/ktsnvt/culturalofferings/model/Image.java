package ftn.ktsnvt.culturalofferings.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Image {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "type")
	private String type;
	
	// image bytes can have large lengths so we specify a value
	// which is more than the default length for picByte column
	 @Column(name = "picByte", length = 1000)
	 private byte[] picByte;
	 
	 public Image() {}
	
	 public Image(Long id, String name, String type, byte[] picByte) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.picByte = picByte;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public byte[] getPicByte() {
		return picByte;
	}
	
	public void setPicByte(byte[] picByte) {
		this.picByte = picByte;
	}

}
