package com.poly.oop1312.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name = "Watch")
@Table(name = "Watch")
public class Watch {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private int watchID;
	
	@ManyToOne
	@JoinColumn(name = "PersonID", referencedColumnName = "PersonID")
	Person person;
	
	@ManyToOne
	@JoinColumn(name = "FilmID", referencedColumnName = "FilmID")
	Film film;
	
	private Date watchDate;

	public Watch() {
		super();
	}

	public Watch(Person person, Film film, Date watchDate) {
		super();
		this.person = person;
		this.film = film;
		this.watchDate = watchDate;
	}

	public int getWatchID() {
		return watchID;
	}

	public void setWatchID(int watchID) {
		this.watchID = watchID;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Film getFilm() {
		return film;
	}

	public void setFilm(Film film) {
		this.film = film;
	}

	public Date getWatchDate() {
		return watchDate;
	}

	public void setWatchDate(Date watchDate) {
		this.watchDate = watchDate;
	}
	
}
