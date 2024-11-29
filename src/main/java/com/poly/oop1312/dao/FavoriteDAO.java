package com.poly.oop1312.dao;

import com.poly.oop1312.dao_detail.AbstractDAO;
import com.poly.oop1312.model.Favorite;
import com.poly.oop1312.model.Watch;

public class FavoriteDAO extends AbstractDAO<Favorite>{
	public Favorite findByFK(Integer id1, Integer id2) {
		String jpql = "SELECT o FROM Favorite o WHERE o.film.filmID = ?0 AND o.person.personID = ?1";
		return super.findOne(Favorite.class, jpql, id1, id2);
	}
}
