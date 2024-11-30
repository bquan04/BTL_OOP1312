package com.poly.oop1312.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.poly.oop1312.dao.EpisodeDAO;
import com.poly.oop1312.dao.FavoriteDAO;
import com.poly.oop1312.dao.FilmDAO;
import com.poly.oop1312.dao.GenreDAO;
import com.poly.oop1312.dao.WatchDAO;
import com.poly.oop1312.model.Episode;
import com.poly.oop1312.model.Favorite;
import com.poly.oop1312.model.Film;
import com.poly.oop1312.model.Genre;
import com.poly.oop1312.model.Person;
import com.poly.oop1312.model.Watch;

@WebServlet("/film-detail")
public class FilmDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	FilmDAO filmDAO = new FilmDAO();
    GenreDAO genreDAO = new GenreDAO();
    EpisodeDAO episodeDAO = new EpisodeDAO();
    WatchDAO watchDAO = new WatchDAO();
    FavoriteDAO favoriteDAO = new FavoriteDAO();
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	List<Genre> genreList = genreDAO.findAll(Genre.class, false);
	List<Episode> episodeList;
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String actionParams = request.getParameter("action");
		Integer id = Integer.parseInt(request.getParameter("filmid"));
		HttpSession session = request.getSession();

		switch (actionParams) {
		case "watch":
			doWatch(session, id, request, response);
			break;
		case "favorite":
			doFavorite(session, id, request, response);
			break;
		case "unFavorite":
			doUnFavorite(session, id, request, response);
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + actionParams);
		}

	}

	private void doFavorite(HttpSession session, Integer filmid, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer episodeid = Integer.parseInt(request.getParameter("episodeid"));
		Film film = filmDAO.findByID(Film.class, filmid);
		Person person = (Person) session.getAttribute("currentPerson");
		Favorite favorite = favoriteDAO.findByFK(filmid, person.getPersonID());
		
		if(favorite == null) {
			film.setTotalFavorites(film.getTotalFavorites() + 1);
			filmDAO.update(film);
			favoriteDAO.update(new Favorite(person, film, new Date(), true));
		} else {
			favorite.setActive(true);
			favoriteDAO.update(favorite);
		}
		response.sendRedirect("film-detail?action=watch&filmid=" + filmid + "&episodeid=" + episodeid);
	}

	private void doUnFavorite(HttpSession session, Integer filmid, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer episodeid = Integer.parseInt(request.getParameter("episodeid"));
		Film film = filmDAO.findByID(Film.class, filmid);
		Person person = (Person) session.getAttribute("currentPerson");
		Favorite favorite = favoriteDAO.findByFK(filmid, person.getPersonID());
		
		if(person != null) {
			film.setTotalFavorites(film.getTotalFavorites() - 1);
			filmDAO.update(film);
			favorite.setActive(false);
			favoriteDAO.update(favorite);
		}
		response.sendRedirect("film-detail?action=watch&filmid=" + filmid + "&episodeid=" + episodeid);
	}
	
	private void doWatch(HttpSession session, Integer filmid, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer episodeid = Integer.parseInt(request.getParameter("episodeid"));
		Film film = filmDAO.findByID(Film.class, filmid);
		List<Episode> episodeList = episodeDAO.findAllByFilmIdASC(filmid);
		Episode episode = episodeDAO.findByID(Episode.class, episodeid);
		Person person = (Person) session.getAttribute("currentPerson");
		
		request.setAttribute("film", film);
		request.setAttribute("episodeList", episodeList);
		request.setAttribute("episode", episode);
		request.setAttribute("watchingEpID", episodeid);
		List<Film> sameGenreFilmList = filmDAO.findAllByGenre(film.getGenre().getGenreID());
		
		if(person != null) {
			Watch watch = watchDAO.findByFK(filmid, person.getPersonID());
			if(watch == null) {
				film.setTotalViews(film.getTotalViews() + 1);
				filmDAO.update(film);
				watchDAO.update(new Watch(person, film, new Date()));
			} else {
				watch.setWatchDate(new Date());
				watchDAO.update(watch);
			}
			
			Favorite favorite = favoriteDAO.findByFK(filmid, person.getPersonID());
			if(favorite != null) {
				request.setAttribute("checkFav", favorite.getActive());
			} else {
				request.setAttribute("checkFav", false);
			}
		}
		
		request.getRequestDispatcher("/views/jsp/filmDetail.jsp").forward(request, response);
	}


}
