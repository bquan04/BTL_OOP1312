package com.poly.oop1312.dao;

import java.util.List;

import com.poly.oop1312.dao_detail.AbstractDAO;
import com.poly.oop1312.dao_detail.IEpisodeDAO;
import com.poly.oop1312.model.Episode;
import com.poly.oop1312.model.Film;

public class EpisodeDAO extends AbstractDAO<Episode> implements IEpisodeDAO{

	@Override
	public List<Episode> findAllByFilmID(Integer id) {
		String jpql = "SELECT o FROM Episode o WHERE o.film.filmID = ?0 ORDER BY o.dateUploaded DESC";
		return super.findMany(Episode.class, jpql, id);
	}

	@Override
	public List<Episode> findAllByFilmIdASC(Integer id) {
		String jpql = "SELECT o FROM Episode o WHERE o.film.filmID = ?0 ORDER BY o.dateUploaded ASC";
		return super.findMany(Episode.class, jpql, id);
	}

	@Override
	public Episode findLastestUploaded(Integer filmid) {
		String jpql = "SELECT o FROM Episode o WHERE o.film.filmID = ?0 ORDER BY o.dateUploaded DESC";
		return super.findOne(Episode.class, jpql, filmid);
	}

	@Override
	public List<Episode> findAllLastestEpByFilmID() {
		String jpql = "SELECT e\r\n"
				+ "FROM Episode e\r\n"
				+ "WHERE e.dateUploaded = (\r\n"
				+ "    SELECT MAX(subE.dateUploaded)\r\n"
				+ "    FROM Episode subE\r\n"
				+ "    WHERE subE.film.filmID = e.film.filmID\r\n"
				+ "    		AND subE.film.active = TRUE\r\n"
				+ ")\r\n"
				+ "ORDER BY e.dateUploaded DESC";
		return super.findMany(Episode.class, jpql);
	}
	
}
