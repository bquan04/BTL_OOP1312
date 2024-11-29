package com.poly.oop1312.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity(name = "Favorite")
@Table(name ="Favorite",uniqueConstraints = {
		@UniqueConstraint(columnNames = {"personid","filmid"})
})
public class Favorite {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Integer favoriteID;
	
	@ManyToOne
	@JoinColumn(name = "PersonID", referencedColumnName = "PersonID")
	Person person;
	
	@ManyToOne
	@JoinColumn(name = "FilmID", referencedColumnName = "FilmID")
	Film film;
	
	private Date favoriteDate;
	
	private Boolean active;
	
	public Favorite() {
	}

	public Favorite(Person person, Film film, Date favoriteDate, Boolean active) {
		super();
		this.person = person;
		this.film = film;
		this.favoriteDate = favoriteDate;
		this.active = active;
	}

	public int getFavoriteID() {
		return favoriteID;
	}

	public void setFavorID(int favorID) {
		this.favoriteID = favorID;
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

	public Date getFavoriteDate() {
		return favoriteDate;
	}

	public void setFavoriteDate(Date favoriteDate) {
		this.favoriteDate = favoriteDate;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}
	
}
