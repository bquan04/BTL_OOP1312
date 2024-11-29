package com.poly.oop1312.dao_detail;

import java.util.List;

import com.poly.oop1312.model.Person;

public interface IPersonDAO {
	 Person findByUsername(String username);
	 Person findByEmail(String email);
	 Person	findByUsernameAndPass(String username, String password);
	 List<Person> findPersonsWatchedFilm(int filmID);
	 List<Person> findPersonsFavoritedFilm(int filmID);
	 List<Person> findPersonsActive(boolean active);
	 List<Person> findPersonsSearched(String input);
	 List<Person> findPersonByRegisterDay();
}
