package com.poly.oop1312.dao_detail;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.poly.oop1312.service.JpaUtil;

public abstract class AbstractDAO<T> {
	public static final EntityManager em = JpaUtil.getEntityManager();

	@Override
	protected void finalize() throws Throwable {
		em.close();
	}

	public T findByID(Class<T> clazz, Integer id) {
		return em.find(clazz, id);
	}

	public List<T> findAll(Class<T> clazz, Boolean existActive) {
		String enityName = clazz.getSimpleName();
		StringBuilder jpql = new StringBuilder();
		jpql.append("SELECT o FROM ").append(enityName).append(" o");
		if (existActive) {
			jpql.append(" WHERE active = 1");
		} 
		TypedQuery<T> query = em.createQuery(jpql.toString(), clazz);
		return query.getResultList();
	}

	public T findOne(Class<T> clazz, String sql, Object... params) {
		TypedQuery<T> query = em.createQuery(sql, clazz);
		for (int i = 0; i < params.length; i++) {
			query.setParameter(i, params[i]);
		}
		List<T> list = query.getResultList();
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}

	public List<T> findMany(Class<T> clazz, String sql, Object... params) {
		// select o from users o where o.id =?0 and o.name =?1
		TypedQuery<T> query = em.createQuery(sql, clazz);
		for (int i = 0; i < params.length; i++) {
			query.setParameter(i, params[i]);
		}
		return query.getResultList();
	}

	public List<T> findManyPage(Class<T> clazz, String sql, int pageNumber, int pageSize, Object... params) {
		// select o from users o where o.id =?0 and o.name =?1
		TypedQuery<T> query = em.createQuery(sql, clazz);
		for (int i = 0; i < params.length; i++) {
			query.setParameter(i, params[i]);
		}
		query.setFirstResult((pageNumber - 1) * pageSize);
		query.setMaxResults(pageSize);
		return query.getResultList();

	}

	public boolean insert(T enity) {
		try {
			em.getTransaction().begin();
			em.persist(enity);
			em.getTransaction().commit();
			return true;
		} catch (Exception e) {
			em.getTransaction().rollback();
		}
		return false;
	}

	public boolean update(T enity) {
		try {
			em.getTransaction().begin();
			em.merge(enity);
			em.getTransaction().commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		}
		return false;
	}

	public boolean delete(T enity) {
		try {
			em.getTransaction().begin();
			em.remove(enity);
			em.getTransaction().commit();
			return true;
		} catch (Exception e) {
			em.getTransaction().rollback();
		}
		return false;
	}
	
	public boolean deleteAllRows(Class<T> clazz) {
		try {
			em.getTransaction().begin();
			String enityName = clazz.getSimpleName();
			StringBuilder jpql = new StringBuilder();
			jpql.append("DELETE FROM ").append(enityName);
			TypedQuery<T> query = em.createQuery(jpql.toString(), clazz);
			query.executeUpdate();
			em.getTransaction().commit();
			return true;
		} catch (Exception e) {
			em.getTransaction().rollback();
		}
		return false;
	}
	
}
