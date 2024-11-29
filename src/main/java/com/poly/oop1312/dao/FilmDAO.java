package com.poly.oop1312.dao;
 
import java.util.List;

import javax.persistence.TypedQuery;

import com.poly.oop1312.dao_detail.AbstractDAO;
import com.poly.oop1312.dao_detail.IFilmDAO;
import com.poly.oop1312.model.Film;

public class FilmDAO extends AbstractDAO<Film> implements IFilmDAO {
	
	@Override
	public List<Film> findByGenre(int genreID) {
		String jpql = "SELECT o FROM Film o WHERE o.genre.genreID = ?0";
		TypedQuery<Film> query = em.createQuery(jpql,Film.class);
		query.setParameter(0, genreID);
		query.setMaxResults(8);
		return query.getResultList();
	}

	@Override
	public List<Film> findAllByGenre(int genreID) {
		String jpql = "SELECT o FROM Film o WHERE o.genre.genreID = ?0";
		TypedQuery<Film> query = em.createQuery(jpql,Film.class);
		query.setParameter(0, genreID);
		return query.getResultList();
	}

	@Override
	public List<Film> findRecentFilms() {
		String jpql = "SELECT o FROM Film o WHERE o.active = 1 ORDER BY o.dateUploaded DESC";
		TypedQuery<Film> query = em.createQuery(jpql,Film.class);
		return query.getResultList();
	}

	@Override
	public List<Film> findByName(String filmName, boolean active) {
		String jpql = "SELECT o FROM Film o WHERE o.active = ?0 AND o.filmName like ?1";
		return super.findMany(Film.class, jpql, active, "%"+filmName+"%");
	}

	@Override
	public List<Film> findNotActive() {
		String jpql = "SELECT o FROM Film o  WHERE o.active = 0";
		return super.findMany(Film.class, jpql);
	}

	@Override
	public Film findLastestUploaded() {
		String jpql = "SELECT o FROM Film o WHERE o.active = ?0 ORDER BY o.dateUploaded DESC";
		return super.findOne(Film.class, jpql, true);
	}

	@Override
	public List<Film> findAllByDateUpdated() {
		String jpql = "SELECT o FROM Film o  WHERE o.active = 1 ORDER BY o.dateUpdated DESC";
		return super.findMany(Film.class, jpql);
	}

	@Override
	public List<Film> findByNameGenre(String filmName, Integer genreID, boolean active) {
		String jpql = "SELECT o FROM Film o  WHERE o.active = ?0 AND o.filmName like ?1 AND o.genre.genreID = ?2";
		return super.findMany(Film.class, jpql, active, "%"+filmName+"%", genreID);
	}

	@Override
	public List<Film> findAllPersonWatched(Integer id) {
		String jpql = "SELECT f \r\n"
				+ "FROM Film f, Watch w \r\n"
				+ "WHERE f.filmID = w.film.filmID AND w.person.personID = ?0 \r\n"
				+ "ORDER BY w.watchDate DESC\r\n";
		return super.findMany(Film.class, jpql, id);
	}

	@Override
	public List<Film> findAllPersonFavorited(Integer id) {
		String jpql = "SELECT f \r\n"
				+ "FROM Film f, Favorite o \r\n"
				+ "WHERE f.filmID = o.film.filmID AND o.person.personID = ?0 AND o.active = 1\r\n"
				+ "ORDER BY o.favoriteDate DESC\r\n";
		return super.findMany(Film.class, jpql, id);
	}
	
}
