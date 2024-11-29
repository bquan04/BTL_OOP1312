package com.poly.oop1312.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;

import com.poly.oop1312.dao.PersonDAO;
import com.poly.oop1312.model.Person;
import com.poly.oop1312.service.CookieUtil;

import javax.servlet.RequestDispatcher;

@WebServlet(name = "Login", urlPatterns = {"/login"})
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    PersonDAO personDAO  = new PersonDAO();
    SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
	CookieUtil cookie = new CookieUtil();
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie[] cs = request.getCookies();
		for (Cookie c : cs) {
			if (c.getName().equals("username")) {
				request.setAttribute("username", c.getValue());
			} else if (c.getName().equals("password")) {
				request.setAttribute("password", c.getValue());
			}
		}
		request.getRequestDispatcher("/views/jsp/login.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Person person = personDAO.findByUsernameAndPass(username, password);
		
		HttpSession session = request.getSession();
		
		session.setAttribute("username", username);
		request.setAttribute("password", password);
		
		if(person != null) {
			session.setAttribute("currentPerson", person);
			if (request.getParameter("rememberLogin") != null) {
				CookieUtil.add(username, password, 1, response);
			} else {
				CookieUtil.add(username, password, 0, response);
			}
			response.sendRedirect("home");
		} else {
			request.setAttribute("message", "Đăng Nhập Thất Bại");
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/views/jsp/login.jsp");
			rd.forward(request, response);
		}
	}

}
