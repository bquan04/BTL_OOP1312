package com.poly.oop1312.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.poly.oop1312.dao.EpisodeDAO;
import com.poly.oop1312.dao.FilmDAO;
import com.poly.oop1312.dao.GenreDAO;
import com.poly.oop1312.model.Episode;
import com.poly.oop1312.model.Film;
import com.poly.oop1312.model.Genre;

@MultipartConfig
@WebServlet("/edit-film")
public class EditFilmController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	FilmDAO filmDAO = new FilmDAO();
    GenreDAO genreDAO = new GenreDAO();
    EpisodeDAO episodeDAO = new EpisodeDAO();
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	List<Genre> genreList = genreDAO.findAll(Genre.class, false);
	List<Episode> episodeList;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String actionParams = request.getParameter("action");
		HttpSession session = request.getSession();

		switch (actionParams) {
		case "new":
			doAdd(session, request, response);
			break;
		case "update":
			doUpdate(session, request, response);
			break;
		default: 
			throw new IllegalArgumentException("Unexpected value: " + actionParams);
		}
	}

	private void doUpdate(HttpSession session, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		session.setAttribute("edit", "update");
		Integer id = Integer.parseInt(request.getParameter("id"));
		Film updatingFilm = filmDAO.findByID(Film.class, id);
		episodeList = episodeDAO.findAllByFilmID(id);
		session.setAttribute("editingFilmID", updatingFilm.getFilmID());
		
		request.setAttribute("genreList", genreList);
		request.setAttribute("checkGenre", updatingFilm.getGenre().getGenreID());
		request.setAttribute("film", updatingFilm);
		request.setAttribute("episodeList", episodeList);
		request.setAttribute("editTitle", "Cập Nhật Phim");
		request.setAttribute("editBtnName", "Lưu");
		request.getRequestDispatcher("/views/jsp/editFilm.jsp").forward(request, response);
	}

	private void doAdd(HttpSession session, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		session.setAttribute("edit", "new");
		episodeList = episodeDAO.findAllByFilmID(0);
		Film addingFilm = filmDAO.findByID(Film.class, 0);
		session.setAttribute("editingFilmID", addingFilm.getFilmID());
				
		request.setAttribute("genreList", genreList);
		request.setAttribute("film", addingFilm);
		request.setAttribute("filmimg", (String) session.getAttribute("film"));
		request.setAttribute("episodeList", episodeList);
		request.setAttribute("editTitle", "Thêm Phim Mới");
		request.setAttribute("editBtnName", "Thêm");
		request.getRequestDispatcher("/views/jsp/editFilm.jsp").forward(request, response);

	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		Integer editingFilmID =  (Integer) request.getSession().getAttribute("editingFilmID");
		Film film = filmDAO.findByID(Film.class, editingFilmID);
		Integer totalViews = film.getTotalViews();
		Integer totalFavorites = film.getTotalFavorites();
		
		String filmName = request.getParameter("filmName");
		int genreID = Integer.parseInt(request.getParameter("genreID"));
		String quality = request.getParameter("quality");
		boolean activeCheck = Boolean.parseBoolean(request.getParameter("active"));
		String descriptions = request.getParameter("descriptions");
		Genre genre = genreDAO.findByID(Genre.class, genreID);
		long millis=System.currentTimeMillis();  
		Date date;
		
		String editChoice = (String) request.getSession().getAttribute("edit");
		
		if(editChoice.equals("new")) {
			date = new Date(millis);
		} else {
			date = filmDAO.findByID(Film.class, editingFilmID).getDateUploaded();
		}
		Film editingFilm = new Film(filmName, genre, descriptions, quality, date, activeCheck, new Date(), 0, 0);
		editingFilm.setPoster(film.getPoster());
        try {
			// Lấy phần file ảnh từ request
			Part photo = request.getPart("imgPoster");

			if(photo != null) {
				String realPath = request.getServletContext().getRealPath("/images/film_posters");
				// Tạo thư mục nếu chưa tồn tại
				if (!Files.exists(Path.of(realPath))) {
			        Files.createDirectories(Path.of(realPath));
			    }
				String fileName = Path.of(photo.getSubmittedFileName()).getFileName().toString();
				photo.write(realPath + "/" + fileName);
				editingFilm.setPoster(fileName);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		} 
        	
        if(!episodeList.isEmpty()) {
        	Integer lastEpID = episodeDAO.findLastestUploaded(editingFilmID).getEpisodeID();
        	editingFilm.setLastestEpID(lastEpID);
        }
        if(editChoice.equals("update")) {
        	editingFilm.setFilmID(editingFilmID);
        	editingFilm.setTotalViews(totalViews);
        	editingFilm.setTotalFavorites(totalFavorites);
        } else {
        	editingFilm.setFilmID(null);
        }
        
        if(!episodeList.isEmpty()) {
        	filmDAO.update(editingFilm);
        	if(!editChoice.equals("update")) {
				for(Episode ep:episodeList) { 
					ep.setFilm(filmDAO.findLastestUploaded());
					episodeDAO.update(ep);
				}
			} else {
				for(Episode ep:episodeList) { 
					ep.setFilm(filmDAO.findByID(Film.class, editingFilmID));
					episodeDAO.update(ep);
				}
			}
			request.getSession().setAttribute("filmid", "film ID: " + editingFilm.getFirstEpID());
			request.getSession().setAttribute("lastEpisodeId", episodeList.get(0).getEpisodeID());
			response.sendRedirect("film-manager");
        } else {
			if(editChoice.equals("update")) {
				response.sendRedirect("edit-film?action=update&id=" + editingFilmID);
			} else {
				request.setAttribute("addEpError",true);
				request.setAttribute("genreList", genreList);
				request.setAttribute("checkGenre", genre.getGenreID());
				request.setAttribute("film", editingFilm);
				request.setAttribute("editTitle", "Thêm Phim Mới");
				request.setAttribute("editBtnName", "Thêm");
				request.getRequestDispatcher("/views/jsp/editFilm.jsp").forward(request, response);
			}
			
		}
	}

}
