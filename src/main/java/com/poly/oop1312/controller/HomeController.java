package com.poly.oop1312.controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.poly.oop1312.dao.EpisodeDAO;
import com.poly.oop1312.dao.FilmDAO;
import com.poly.oop1312.dao.GenreDAO;
import com.poly.oop1312.model.Episode;
import com.poly.oop1312.model.Film;
import com.poly.oop1312.model.Genre;

@WebServlet("/home")
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    FilmDAO filmDAO = new FilmDAO();
    EpisodeDAO episodeDAO = new EpisodeDAO();
    GenreDAO genreDAO = new GenreDAO();
	List<Genre> genreList = genreDAO.findAll(Genre.class, false);
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Film> recentFilmList = filmDAO.findRecentFilms();
		List<Film> filmRecentUpdateList = filmDAO.findAllByDateUpdated();
		List<Episode> lastestEpUploaded = episodeDAO.findAllLastestEpByFilmID();
		
		request.setAttribute("recentFilmList", recentFilmList);
		request.setAttribute("filmRecentUpdateList", filmRecentUpdateList);
		request.setAttribute("lastestEpUploadedList", lastestEpUploaded);
		request.setAttribute("genreList", genreList);
		request.getRequestDispatcher("/views/jsp/home.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		List<Film> filmList;
		Integer genreid = Integer.parseInt(request.getParameter("filmGenre"));
		String name = request.getParameter("searchFilm");
		
		if (genreid > 0) {
			filmList = filmDAO.findByNameGenre(name, genreid, true);
		} else {
			filmList = filmDAO.findByName(name, true);
		}
		if (filmList.size() > 0) {
			request.setAttribute("searchRs","KẾT QUẢ TÌM KIẾM: " + name);
		} else {
			request.setAttribute("searchRs","KẾT QUẢ TÌM KIẾM: " + "Không tìm thấy phim phù hợp.");
		}
		
		request.setAttribute("inputTxt", name);
		request.setAttribute("checkGenre", genreid);
		request.setAttribute("filmList", filmList);
		request.getRequestDispatcher("/views/jsp/searchFilm.jsp").forward(request, response);
	}
	
}
