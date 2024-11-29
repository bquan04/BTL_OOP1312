package com.poly.oop1312.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity(name = "Person")
@Table(name = "Person")
public class Person {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private int personID;
	private String username;
	private String pass;
	private String email;
	private String fullName;
	private Date dateOfBirth;
	private String avatar;
	private Date registerDay;
	private Boolean adminRole;
	private Boolean active;

	@OneToMany(mappedBy = "person")
	List<Watch> watchs = new ArrayList<Watch>();

	@OneToMany(mappedBy = "person")
	List<Favorite> favorites = new ArrayList<Favorite>();

	public Person() {
	}

	public Person(String username, String pass, String email, String fullName, Date dateOfBirth, String avatar,
			Date registerDay, Boolean adminRole, Boolean active) {
		this.username = username;
		this.pass = pass;
		this.email = email;
		this.fullName = fullName;
		this.dateOfBirth = dateOfBirth;
		this.avatar = avatar;
		this.registerDay = registerDay;
		this.adminRole = adminRole;
		this.active = active;
	}

	public int getPersonID() {
		return personID;
	}

	public void setPersonID(int id) {
		this.personID = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public Date getRegisterDay() {
		return registerDay;
	}

	public void setRegisterDay(Date registerDay) {
		this.registerDay = registerDay;
	}

	public Boolean getAdminRole() {
		return adminRole;
	}

	public void setAdminRole(Boolean adminRole) {
		this.adminRole = adminRole;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public List<Watch> getWatchs() {
		return watchs;
	}

	public void setWatchs(List<Watch> watchs) {
		this.watchs = watchs;
	}

	public List<Favorite> getFavorites() {
		return favorites;
	}

	public void setFavorites(List<Favorite> favorites) {
		this.favorites = favorites;
	}

	@Override
	public String toString() {
		return "Person [personID=" + personID + ", username=" + username + ", pass=" + pass + ", email=" + email
				+ ", fullName=" + fullName + ", dateOfBirth=" + dateOfBirth + ", avatar=" + avatar + ", registerDay="
				+ registerDay + ", adminRole=" + adminRole + ", active=" + active + "]";
	}

}
