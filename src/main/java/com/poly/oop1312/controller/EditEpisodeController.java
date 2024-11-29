package com.poly.oop1312.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
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
@WebServlet("/edit-episode")
public class EditEpisodeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	FilmDAO filmDAO = new FilmDAO();
	GenreDAO genreDAO = new GenreDAO();
	List<Genre> genreList = genreDAO.findAll(Genre.class, false);
    EpisodeDAO episodeDAO = new EpisodeDAO();
    List<Episode> episodeList = new ArrayList<Episode>();
    
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String episodeName = request.getParameter("episodeName");
		String episodeLink = request.getParameter("episodeLink");
		
		String realPath = request.getServletContext().getRealPath("/images/episode_thumbnails");
        // Lấy phần file ảnh từ request
        Part photo = request.getPart("episodeThumbnail");
        String fileName = Path.of(photo.getSubmittedFileName()).getFileName().toString();
        // Tạo thư mục nếu chưa tồn tại
        if (!Files.exists(Path.of(realPath))) {
            Files.createDirectories(Path.of(realPath));
        }
        
        Integer filmid = (Integer) request.getSession().getAttribute("editingFilmID");
        Film film = filmDAO.findByID(Film.class, filmid);
        String editChoice = (String) request.getSession().getAttribute("edit");
        
        Episode editingEpisode = new Episode(episodeName, film, episodeLink, new Date(), true);
        
        photo.write(realPath + "/" + fileName);
        editingEpisode.setThumnail(fileName);
		
		boolean check = episodeDAO.update(editingEpisode);
		
		if(check) {
        	if(editChoice.equals("update")) {
        		response.sendRedirect("edit-film?action=update&id=" + filmid);
        	} else {
        		response.sendRedirect("edit-film?action=new");
        	}
			
		}
	}

}
