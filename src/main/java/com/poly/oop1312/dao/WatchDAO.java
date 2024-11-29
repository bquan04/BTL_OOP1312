package com.poly.oop1312.dao;

import com.poly.oop1312.dao_detail.AbstractDAO;
import com.poly.oop1312.model.Watch;

public class WatchDAO extends AbstractDAO<Watch> {
	public Watch findByFK(Integer id1, Integer id2) {
		String jpql = "SELECT o FROM Watch o WHERE o.film.filmID = ?0 AND o.person.personID = ?1";
		return super.findOne(Watch.class, jpql, id1, id2);
	}
}
