package com.poly.oop1312.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.poly.oop1312.model.Person;
import com.poly.oop1312.dao.PersonDAO;

@MultipartConfig
@WebServlet("/edit-account")
public class EditAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PersonDAO personDAO  = new PersonDAO();
    SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Person person = (Person) request.getSession().getAttribute("currentPerson");
		
		request.setAttribute("person", person);
		request.getRequestDispatcher("/views/jsp/editAccount.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
			Person currentPerson = (Person) session.getAttribute("currentPerson");
			String fullName = request.getParameter("fullName");
			String username = request.getParameter("username");
			String email = request.getParameter("email");
			String birth = request.getParameter("birth");
			Integer id = currentPerson.getPersonID();
			Date registerDate = currentPerson.getRegisterDay();
			boolean adminRole = currentPerson.getAdminRole();
			String currentUsername = currentPerson.getUsername();
			String pass = currentPerson.getPass();
			Person person = new Person(username, pass, email, fullName, format.parse(birth), "avt.jpg", registerDate, adminRole,
					true);
			person.setPersonID(id);
			person.setAvatar(currentPerson.getAvatar());
			request.setAttribute("person", person);
			
			try {
				// Lấy phần file ảnh từ request
				Part photo = request.getPart("imgAvt");

				if(photo != null) {
					String realPath = request.getServletContext().getRealPath("/images/users_avt");
					// Tạo thư mục nếu chưa tồn tại
					if (!Files.exists(Path.of(realPath))) {
				        Files.createDirectories(Path.of(realPath));
				    }
					String fileName = Path.of(photo.getSubmittedFileName()).getFileName().toString();
					photo.write(realPath + "/" + fileName);
					person.setAvatar(fileName);
				}

			} catch (IOException e) {
				e.printStackTrace();
			} catch (ServletException e) {
				e.printStackTrace();
			} 
			
			int errCnt = 0;
			
			if (personDAO.findByUsername(username) != null && !username.equals(currentUsername)) {
				request.setAttribute("errorUsername", "Username đã tồn tại");
				request.setAttribute("person", person);
				errCnt++;
				
			}
			
			if(errCnt > 0) {
				request.getRequestDispatcher("/views/jsp/editAccount.jsp").forward(request, response);
			} else {
				boolean check = personDAO.update(person);
				if (check) {
					session.setAttribute("currentPerson", person);
					response.sendRedirect("home");
				} else {
					doGet(request, response);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
