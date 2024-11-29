package com.poly.oop1312.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import com.poly.oop1312.dao.PersonDAO;
import com.poly.oop1312.model.Person;
import com.poly.oop1312.service.CookieUtil;

@WebServlet("/register")
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PersonDAO personDAO  = new PersonDAO();
    SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
	CookieUtil cookie = new CookieUtil();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/views/jsp/register.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
			String fullName = request.getParameter("fullName");
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String email = request.getParameter("email");
			String birth = request.getParameter("birthday");
			Date currentDate = new Date(); 
			Person person = new Person(username, password, email, fullName, 
					format.parse(birth), "avt.jpg", currentDate, false, true);
			
			request.setAttribute("person", person);
			if(personDAO.findByUsername(username)!=null) {
				request.setAttribute("errorUsername", "Username đã tồn tại");
				request.setAttribute("person", person);
				request.getRequestDispatcher("/views/jsp/register.jsp").forward(request, response);
			}else {
				boolean check = personDAO.insert(person);
				if (check) {
					session.setAttribute("currentPerson", person);
					response.sendRedirect("home");
				} else {
					doGet(request, response);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

}
