package edu.ssafy.dto;

import java.io.Serializable;
import java.util.Arrays;

public class MemberDto implements Serializable {
	private String id;
	private String password;
	private String name;
	private String[] hobby;
	public MemberDto() {
		
	}
	public MemberDto(String id, String password, String name, String age, String[] hobby) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
		this.hobby = hobby;
		this.age = age;
	}
	private String age;  //연산이 필요하면 int로 하고 연산이 필요한 것이 없으면 String
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String[] getHobby() {
		return hobby;
	}
	public void setHobby(String[] hobby) {
		this.hobby = hobby;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "MemberDto [id=" + id + ", password=" + password + ", name=" + name + ", hobby=" + Arrays.toString(hobby)
				+ ", age=" + age + "]";
	}
	
}
