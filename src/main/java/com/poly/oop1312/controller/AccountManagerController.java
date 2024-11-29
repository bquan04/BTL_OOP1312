package com.poly.oop1312.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poly.oop1312.dao.PersonDAO;
import com.poly.oop1312.model.Person; 
import com.poly.oop1312.service.ExcelUtil;

@WebServlet({"/account-manager", "/active-acc", "/disabled-acc", "/delete-acc", "/deactive", "/restore-acc"})
public class AccountManagerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    PersonDAO personDAO = new PersonDAO();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		List<Person> personList = personDAO.findPersonByRegisterDay();
		
		String path = request.getRequestURI();
		if(path.contains("active-acc")) {
			personList = personDAO.findPersonsActive(true);
		} else if(path.contains("disabled-acc")) {
			personList = personDAO.findPersonsActive(false);
		} else if(path.contains("deactive")) {
			Integer personid = Integer.parseInt(request.getParameter("id"));
			Person p = personDAO.findByID(Person.class, personid);
			p.setActive(false);
			personDAO.update(p);
			response.sendRedirect("account-manager");
			return;
		} else if(path.contains("restore-acc")) {
			Integer personid = Integer.parseInt(request.getParameter("id"));
			Person p = personDAO.findByID(Person.class, personid);
			p.setActive(true);
			personDAO.update(p);
			response.sendRedirect("account-manager");
			return;
		} else if(path.contains("delete-acc")) {
			doDeleteAcc(request, response);
			return;
		}
		
		boolean check = exportExcel();
		request.setAttribute("err", check);
		request.setAttribute("personList", personList);
		request.getRequestDispatcher("/views/jsp/accountManager.jsp").forward(request, response);
	}
	
	private void doDeleteAcc(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer personid = Integer.parseInt(request.getParameter("id"));
		Person person = personDAO.findByID(Person.class, personid);
		personDAO.delete(person);
		response.sendRedirect("account-manager");
	}
	
	private boolean exportExcel() {
		try {
			List<Person> list = personDAO.findPersonByRegisterDay();
			String[] header = { "Stt", "ID", "Username", "Họ và tên", 
					"Birth", "Avatar", 
					"Ngày đăng ký", "Email",
					"Role", "Trạng thái" };
			String exportPath = "/export/account.xlsx";
			String absolutePath = getServletContext().getRealPath("/") + exportPath;
			ExcelUtil.writeExcel(absolutePath, list, "account", header);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String searchAccount = request.getParameter("searchAccount");
		List<Person> personList = personDAO.findPersonsSearched(searchAccount);
		
		request.setAttribute("searchInput", searchAccount);
		request.setAttribute("personList", personList);
		request.getRequestDispatcher("/views/jsp/accountManager.jsp").forward(request, response);
	}

}
