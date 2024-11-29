package com.poly.oop1312.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.poly.oop1312.dao.FilmDAO;
import com.poly.oop1312.dao.GenreDAO;
import com.poly.oop1312.model.Film;
import com.poly.oop1312.model.Genre;

@MultipartConfig()
@WebServlet({"/film-manager", "/notactive-films", "/delete-film", "/hide-film",  "/restore-film"})
public class FilmManagerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    FilmDAO filmDAO = new FilmDAO();
    GenreDAO genreDAO = new GenreDAO();
	List<Genre> genreList = genreDAO.findAll(Genre.class, false);
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Film> filmList = filmDAO.findRecentFilms();
		request.getSession().setAttribute("activeFilmCheck", true);
		
		String path = request.getRequestURI();
		if (path.contains("notactive-films")) {
			filmList = filmDAO.findNotActive();
			request.getSession().setAttribute("activeFilmCheck", false);
		} else if (path.contains("delete-film")) {
			doDeleteFilm(request, response);
			return;
		} else if (path.contains("hide-film")) {
			doHide(request, response);
			return;
		} else if(path.contains("restore-film")) {
			doRestore(request, response);
			return;
		}
		
		request.setAttribute("filmList", filmList);
		request.setAttribute("genreList", genreList);
		request.getRequestDispatcher("/views/jsp/filmManager.jsp").forward(request, response);
	}
	
	private void doDeleteFilm(HttpServletRequest request, HttpServletResponse response) {
		try {
			Integer id = Integer.parseInt(request.getParameter("id"));
			Film film = filmDAO.findByID(Film.class, id);
			filmDAO.delete(film);
			response.sendRedirect("film-manager");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private void doHide(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer id = Integer.parseInt(request.getParameter("id"));
		Film film = filmDAO.findByID(Film.class, id);
		film.setActive(false);
		filmDAO.update(film);
		response.sendRedirect("film-manager");
	}
	
	private void doRestore(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer id = Integer.parseInt(request.getParameter("id"));
		Film film = filmDAO.findByID(Film.class, id);
		film.setActive(true);
		filmDAO.update(film);
		response.sendRedirect("film-manager");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		List<Film> filmList;
		Integer genreid = Integer.parseInt(request.getParameter("filmGenre"));
		Boolean active = (Boolean) request.getSession().getAttribute("activeFilmCheck");
		String name = request.getParameter("searchFilm");
		if (genreid > 0) {
			filmList = filmDAO.findByNameGenre(name, genreid, active);
		} else {
			filmList = filmDAO.findByName(name, active);
		}
		if (filmList.size() > 0) {
			request.setAttribute("searchRs","KẾT QUẢ TÌM KIẾM: " + name);
		} else {
			request.setAttribute("searchRs","KẾT QUẢ TÌM KIẾM: " + "Không tìm thấy phim phù hợp.");
		}
		
		request.setAttribute("inputTxt", name);
		request.setAttribute("checkGenre", genreid);
		request.setAttribute("filmList", filmList);
		request.setAttribute("genreList", genreList);
		request.getRequestDispatcher("/views/jsp/filmManager.jsp").forward(request, response);
	}

}
