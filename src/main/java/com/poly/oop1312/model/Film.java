package com.poly.oop1312.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Film")
public class Film {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer filmID;
	private String filmName;
	@ManyToOne
	@JoinColumn(name = "genreid", referencedColumnName = "genreid")
	private Genre genre;
	private String poster;
	private String descriptions;
	private String quality;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateUploaded;
	private Boolean active;
	private Date dateUpdated;
	private Integer lastestEpID;
	private Integer totalViews;
	private Integer totalFavorites;
	
	@OneToMany(mappedBy = "film", cascade = { CascadeType.ALL })
	List<Watch> watchs = new ArrayList<Watch>();

	@OneToMany(mappedBy = "film", cascade = { CascadeType.ALL })
	List<Favorite> favorites = new ArrayList<Favorite>();

	@OneToMany(mappedBy = "film", cascade = { CascadeType.ALL })
	List<Episode> episodes = new ArrayList<Episode>();

	public Film() {
	}

	public Film(String filmName, Genre genre, String descriptions, String quality, Date dateUploaded,
			Boolean active, Date dateUpdated, Integer totalViews, Integer totalFavorites) {
		this.filmName = filmName;
		this.genre = genre;
		this.descriptions = descriptions;
		this.quality = quality;
		this.dateUploaded = dateUploaded;
		this.active = active;
		this.dateUpdated = dateUpdated;
		this.totalViews = totalViews;
		this.totalFavorites = totalFavorites;
	}

	public Integer getFilmID() {
		return filmID;
	}

	public void setFilmID(Integer filmID) {
		this.filmID = filmID;
	}

	public String getFilmName() {
		return filmName;
	}

	public void setFilmName(String filmName) {
		this.filmName = filmName;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}


	public String getDescriptions() {
		return descriptions;
	}

	public void setDescriptions(String descriptions) {
		this.descriptions = descriptions;
	}

	public String getQuality() {
		return quality;
	}

	public void setQuality(String quality) {
		this.quality = quality;
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

	public Date getDateUpdated() {
		return dateUpdated;
	}

	public void setDateUpdated(Date dateUpdated) {
		this.dateUpdated = dateUpdated;
	}

	public Integer getLastestEpID() {
		return lastestEpID;
	}

	public void setLastestEpID(Integer lastestEpID) {
		this.lastestEpID = lastestEpID;
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

	public List<Episode> getEpisodes() {
		return episodes;
	}

	public void setEpisodes(List<Episode> episodes) {
		this.episodes = episodes;
	}
	
	public Integer getTotalViews() {
		return totalViews;
	}

	public void setTotalViews(Integer totalViews) {
		this.totalViews = totalViews;
	}

	public Integer getTotalFavorites() {
		return totalFavorites;
	}

	public void setTotalFavorites(Integer totalFavorites) {
		this.totalFavorites = totalFavorites;
	}

	public int getTotalEpisodes() {
		if (episodes == null || episodes.isEmpty()) {
			return 0;
		}
		int totalEpisodes = episodes.size();
		return totalEpisodes;
	}
	
	public Integer getFirstEpID() {
		if (episodes == null || episodes.isEmpty()) {
			return null;
		}
		
		return this.getEpisodes().get(this.getEpisodes().size()-1).getEpisodeID();
	}
	
	public String getLastEpName() {
		if (episodes == null || episodes.isEmpty()) {
			return null;
		}
		
		return this.getEpisodes().get(this.getEpisodes().size()-1).getEpisodeName();
	}

	@Override
	public String toString() {
		return "Film [filmID=" + filmID + ", filmName=" + filmName + ", genre=" + genre + ", poster=" + poster
				+ ", descriptions=" + descriptions + ", quality=" + quality + ", dateUploaded=" + dateUploaded
				+ ", active=" + active + "]";
	}

}
