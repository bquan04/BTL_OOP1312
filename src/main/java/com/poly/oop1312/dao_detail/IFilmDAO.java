package com.poly.oop1312.dao_detail;

import java.util.List;
import com.poly.oop1312.model.Film;

public interface IFilmDAO {
	List<Film> findByGenre(int genreID);
	List<Film> findAllByGenre(int genreID);
	List<Film> findRecentFilms();
	List<Film> findByNameGenre(String filmName, Integer genreID, boolean active);
	List<Film> findByName(String filmName, boolean active);
	List<Film> findNotActive();
	List<Film> findAllByDateUpdated();
	List<Film> findAllPersonWatched(Integer id);
	List<Film> findAllPersonFavorited(Integer id);
	Film findLastestUploaded();
}
