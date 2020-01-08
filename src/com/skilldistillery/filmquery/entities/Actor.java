package com.skilldistillery.filmquery.entities;

public class Actor {
	int id;
	String first_name;
	String last_name;
	
	public Actor() {
		
	}
	
	public Actor(int id, String first_name, String last_name) {
		super();
		this.id = id;
		this.first_name = first_name;
		this.last_name = last_name;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	@Override
	public String toString() {
		return "Actor [id=" + id + ", " + (first_name != null ? "first_name=" + first_name + ", " : "")
				+ (last_name != null ? "last_name=" + last_name : "") + "]";
	}
	
	

}
