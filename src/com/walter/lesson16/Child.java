package com.walter.lesson16;

public class Child {
	private String names, location, age, gender;
	public Child(String names, String location, String age, String gender) {
		super();
		this.names = names;
		this.location = location;
		this.age = age;
		this.gender = gender;
	}

	public String getNames() {
		return names;
	}

	public void setNames(String names) {
		this.names = names;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

}
