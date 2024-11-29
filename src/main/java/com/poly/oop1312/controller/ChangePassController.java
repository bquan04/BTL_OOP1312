package com.poly.oop1312.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.poly.oop1312.dao.PersonDAO;
import com.poly.oop1312.model.Person;

@WebServlet("/change-pass")
public class ChangePassController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PersonDAO personDAO = new PersonDAO();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/views/jsp/changePassword.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Person currentPerson = (Person) session.getAttribute("currentPerson");
		
		Integer id = currentPerson.getPersonID();
		String currentPass = currentPerson.getPass();
		String oldPass = request.getParameter("oldPass");
		String newPass = request.getParameter("newPass");
		String comfirmPass = request.getParameter("comfirmPass");
		
		Person person = personDAO.findByID(Person.class, id);
		boolean errCheck = false;
		if(!currentPass.equals(oldPass)) {
			request.setAttribute("oldPassErr", "Mật khẩu không đúng. Vui lòng nhập lại!");
			errCheck = true;
		}
		
		if(newPass.equals(currentPass)) {
			request.setAttribute("newPassErr", "Mật khẩu mới không được trùng mật khẩu cũ!");
			errCheck = true;
		}
		
		if(!comfirmPass.equals(newPass)) {
			request.setAttribute("comfirmPassErr", "Không khớp!");
			errCheck = true;
		}
		
		if(errCheck) {
			request.setAttribute("oldPass", oldPass);
			request.setAttribute("newPass", newPass);
			request.setAttribute("comfirmPass", comfirmPass);
			request.getRequestDispatcher("/views/jsp/changePassword.jsp").forward(request, response);
		} else {
			person.setPass(newPass);
			personDAO.update(person);
			response.sendRedirect("home");
		}
		
	}

}
