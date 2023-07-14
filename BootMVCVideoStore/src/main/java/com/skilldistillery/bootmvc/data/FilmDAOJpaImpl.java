package com.skilldistillery.bootmvc.data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.skilldistillery.jpavideostore.entities.Film;

@Service
@Transactional
public class FilmDAOJpaImpl implements FilmDAO {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public Film findById(int id) {
		return em.find(Film.class, id);
	}

	@Override
	public List<Film> findAll() {
		String jpql = "SELECT f FROM Film f";
		return em.createQuery(jpql, Film.class).getResultList();
	}

}
