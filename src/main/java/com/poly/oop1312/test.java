package com.poly.oop1312;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import java.time.LocalDate;

import com.poly.oop1312.dao.EpisodeDAO;
import com.poly.oop1312.dao.FilmDAO;
import com.poly.oop1312.dao.GenreDAO;
import com.poly.oop1312.dao.PersonDAO;
import com.poly.oop1312.model.Episode;
import com.poly.oop1312.model.Film;
import com.poly.oop1312.model.Genre;
import com.poly.oop1312.model.Person;
import com.poly.oop1312.service.JpaUtil;

public class test {
	public static void main(String[] args) throws ParseException {
		FilmDAO filmDAO = new FilmDAO();
		EpisodeDAO episodeDAO = new EpisodeDAO();
		
		Episode e = episodeDAO.findByID(Episode.class, 77);
		Film f = filmDAO.findByID(Film.class, 6);
		Integer cnt = e.getTotalViews();
		System.out.println(cnt + " " + f.getTotalViews());
	}
}
