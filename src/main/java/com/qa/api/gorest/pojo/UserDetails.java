package com.qa.api.gorest.pojo;

public class UserDetails {

	
	private String name;
	private String gender;
	private String status;
	private String email;
	private Links links;
	


	public UserDetails(String name, String gender, String status, String email, Links links) {
		
		this.name = name;
		this.gender = gender;
		this.status = status;
		this.email = email;
		this.links = links;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Links getLinks() {
		return links;
	}

	public void setLinks(Links links) {
		this.links = links;
	}

	
	
	
	
}
