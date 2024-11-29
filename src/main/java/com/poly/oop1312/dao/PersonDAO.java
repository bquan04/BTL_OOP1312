package com.poly.oop1312.dao;

import java.util.List;

import com.poly.oop1312.dao_detail.AbstractDAO;
import com.poly.oop1312.dao_detail.IPersonDAO;
import com.poly.oop1312.model.Person;

public class PersonDAO extends AbstractDAO<Person> implements IPersonDAO {

	@Override
	public Person findByUsername(String username) {
		String jpql = "SELECT o FROM Person o WHERE o.username = ?0";
		return super.findOne(Person.class, jpql, username);
	}

	@Override
	public Person findByEmail(String email) {
		String jpql = "SELECT o FROM Person o WHERE o.email = ?0";
		return super.findOne(Person.class, jpql, email);
	}

	@Override
	public Person findByUsernameAndPass(String username, String password) {
		String jpql = "SELECT o FROM Person o WHERE o.username = ?0 AND o.pass = ?1 AND o.active = 1";
		return super.findOne(Person.class, jpql, username, password);
	}

	@Override
	public List<Person> findPersonsWatchedFilm(int filmID) {
		String jpql = "SELECT o.person FROM Watch o WHERE o.film.filmID = ?0";
		return super.findMany(Person.class, jpql, filmID);
	}

	@Override
	public List<Person> findPersonsFavoritedFilm(int filmID) {
		String jpql = "SELECT o.person FROM Favorite o WHERE o.film.filmID = ?0";
		return super.findMany(Person.class, jpql, filmID);
	}

	@Override
	public List<Person> findPersonsActive(boolean active) {
		String jpql = "SELECT o FROM Person o WHERE o.active = ?0 ORDER BY o.registerDay DESC";
		return super.findMany(Person.class, jpql, active);
	}

	@Override
	public List<Person> findPersonsSearched(String input) {
		String jpql = "SELECT o FROM Person o WHERE o.username like ?0 OR o.fullName like ?1 OR o.email like ?2";
		return super.findMany(Person.class, jpql,"%" + input + "%", "%" + input + "%", "%" + input + "%");
	}

	@Override
	public List<Person> findPersonByRegisterDay() {
		String jpql = "SELECT o FROM Person o ORDER BY o.registerDay DESC";
		return super.findMany(Person.class, jpql);
	}
	
}
