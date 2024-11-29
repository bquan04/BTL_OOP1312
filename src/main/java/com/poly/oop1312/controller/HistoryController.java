package com.poly.oop1312.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poly.oop1312.dao.FilmDAO;
import com.poly.oop1312.dao.WatchDAO;
import com.poly.oop1312.model.Film;
import com.poly.oop1312.model.Person;
import com.poly.oop1312.model.Watch;

@WebServlet({"/history", "/delete-history"})
public class HistoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    FilmDAO filmDAO = new FilmDAO();
    WatchDAO watchDAO = new WatchDAO();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Person person = (Person) request.getSession().getAttribute("currentPerson");
		List<Film> filmList = filmDAO.findAllPersonWatched(person.getPersonID());
		
		String path = request.getRequestURI();
		if(path.contains("delete-history")) {
			Integer filmid = Integer.parseInt(request.getParameter("filmid"));
			Integer personid = Integer.parseInt(request.getParameter("personid"));
			Watch watch = watchDAO.findByFK(filmid, personid);
			
			watchDAO.delete(watch);
			filmList = filmDAO.findAllPersonWatched(person.getPersonID());
		}
		
		request.setAttribute("filmList", filmList);
		request.getRequestDispatcher("/views/jsp/history.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
