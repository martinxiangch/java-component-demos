package com.martin.component.model;

public class UserPassItem{
	
	private String type;
	private String name;
	private String pass;
	private String tag;
	
	public UserPassItem() {}
	
	public UserPassItem(String name,String pass,String type, String tag) {
		this.setName(name);
		this.setPass(pass);
		this.setType(type);
		this.setTag(tag);
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
}

