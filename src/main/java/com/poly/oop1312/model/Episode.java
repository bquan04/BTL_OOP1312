package com.poly.oop1312.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name = "Episode")
@Table(name = "Episode")
public class Episode {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Integer episodeID;
	
	private String episodeName;
	
	@ManyToOne
	@JoinColumn(name = "filmid", referencedColumnName = "filmid")
	Film film;
	
	private String thumbnail;
	
	private String link;
	
	private Date dateUploaded; 
	
	private Boolean active;
	
	public Episode() {
		super();
	}

	public Episode(String episodeName, Film film, String link, Date dateUploaded, Boolean active) {
		super();
		this.episodeName = episodeName;
		this.film = film;
		this.link = link;
		this.dateUploaded = dateUploaded;
		this.active = active;
	}

	public Integer getEpisodeID() {
		return episodeID;
	}

	public void setEpisodeID(Integer episodeID) {
		this.episodeID = episodeID;
	}

	public String getEpisodeName() {
		return episodeName;
	}

	public void setEpisodeName(String episodeName) {
		this.episodeName = episodeName;
	}

	public Film getFilm() {
		return film;
	}

	public void setFilm(Film film) {
		this.film = film;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Date getDateUploaded() {
		return dateUploaded;
	}

	public void setDateUploaded(Date dateUploaded) {
		this.dateUploaded = dateUploaded;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}
	
	public Integer getFilmID() {
		return this.film.getFilmID();
	}

	public String getFilmName() {
		return this.film.getFilmName();
	}
	
	@Override
	public String toString() {
		return "Episode [episodeID=" + episodeID + ", episodeName=" + episodeName + ", thumbnail=" + thumbnail
				+ ", link=" + link + ", dateUploaded=" + dateUploaded + ", active=" + active + "]";
	}

	public Integer getTotalViews() {
		return this.getFilm().getTotalViews();
	}
	
}
