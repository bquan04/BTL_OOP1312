package com.poly.oop1312.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poly.oop1312.dao.FilmDAO;
import com.poly.oop1312.dao.FavoriteDAO;
import com.poly.oop1312.model.Favorite;
import com.poly.oop1312.model.Film;
import com.poly.oop1312.model.Person;

@WebServlet({"/favorite", "/delete-favorite"})
public class FavoriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    FilmDAO filmDAO = new FilmDAO();
    FavoriteDAO favoriteDAO = new FavoriteDAO();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Person person = (Person) request.getSession().getAttribute("currentPerson");
		List<Film> filmList = filmDAO.findAllPersonFavorited(person.getPersonID());
		
		String path = request.getRequestURI();
		if(path.contains("delete-favorite")) {
			Integer filmid = Integer.parseInt(request.getParameter("filmid"));
			Integer personid = Integer.parseInt(request.getParameter("personid"));
			Favorite favorite = favoriteDAO.findByFK(filmid, personid);
			
			favoriteDAO.delete(favorite);
			filmList = filmDAO.findAllPersonFavorited(person.getPersonID());
		}
		
		request.setAttribute("filmList", filmList);
		request.getRequestDispatcher("/views/jsp/favorite.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
