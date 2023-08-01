package com.skilldistillery.jpavideostore.client;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.skilldistillery.jpavideostore.entities.Customer;
import com.skilldistillery.jpavideostore.entities.Film;
import com.skilldistillery.jpavideostore.entities.Rental;
import com.skilldistillery.jpavideostore.entities.Store;

public class JPQLRelationshipsLab {
	
	private static EntityManagerFactory emf
		= Persistence.createEntityManagerFactory("VideoStore");
	
	public List<Store> getStoresByState(String state) {
		EntityManager em = emf.createEntityManager();
		String jpql = "SELECT store from Store store WHERE store.address.state = :st";
//		String jpql = "SELECT s FROM Store s JOIN Address a ON s.address.id = a.id WHERE a.state = :st";
		List<Store> stores = em.createQuery(jpql, Store.class)
				               .setParameter("st", state)
				               .getResultList();
		em.close();
		return stores;
	}
	
	public List<Rental> getRentalsForCustomerWithCustomerId(int id) {
		EntityManager em = emf.createEntityManager();
		String jpql = "SELECT r FROM Rental r WHERE r.customer.id = :custId";
		List<Rental> rentals = em.createQuery(jpql, Rental.class)
				.setParameter("custId", id)
				.getResultList();
		
//		String jpql = "SELECT c.rentals FROM Customer c WHERE c.id = :custId";
//		List<Object> objects = em.createQuery(jpql, Object.class)
//				                 .setParameter("custId", id)
//				                 .getResultList();
//		List<Rental> rentals = new ArrayList<>();
//		for (Object object : objects) {
//			rentals.add((Rental)object);
//		}
		
//		List<Rental> rentals = null;
//		Customer cust = em.find(Customer.class, id);
//		if (cust != null) {
//			rentals = cust.getRentals();
//			rentals.size();
//		}
		
		em.close();
		return rentals;
	}
	
	public List<Film> getFilmsForActorWithId(int id) {
		EntityManager em = emf.createEntityManager();
		List<Film> films = null;
		
//		String jpql = "SELECT f FROM Film f WHERE f.actor.id = :actorId"; //NO
//		String jpql = "SELECT f FROM Film f WHERE f.actors.id = :actorId"; //NOPE.
		String jpql = "SELECT f FROM Film f JOIN f.actors a WHERE a.id = :actorId"; //YAY!
		films = em.createQuery(jpql, Film.class)
				  .setParameter("actorId", id)
				  .getResultList();
		
		em.close();
		return films;
	}

	public int getNumberOfFilmsForCategoryWithName(String name) {
		EntityManager em = emf.createEntityManager();
 		int count = -1;
 		
 		String jpql = "SELECT f FROM Film f JOIN f.categories cat WHERE cat.name = :catName";
 		List<Film> films = em.createQuery(jpql, Film.class)
 				.setParameter("catName", name)
 				.getResultList();
 		count = films.size();
 		
// 		String jpql = "SELECT COUNT(f) FROM Film f JOIN f.categories cat WHERE cat.name = :catName";
// 		long longCount = em.createQuery(jpql, Long.class)
// 				             .setParameter("catName", name)
// 				             .getSingleResult();
// 		count = (int) longCount;
 		
		em.close();
		return count;
	}
	
	
	
}
