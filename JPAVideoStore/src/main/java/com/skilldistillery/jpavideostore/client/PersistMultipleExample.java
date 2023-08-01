package com.skilldistillery.jpavideostore.client;

import java.time.LocalDateTime;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.skilldistillery.jpavideostore.entities.Customer;
import com.skilldistillery.jpavideostore.entities.InventoryItem;
import com.skilldistillery.jpavideostore.entities.Payment;
import com.skilldistillery.jpavideostore.entities.Rental;
import com.skilldistillery.jpavideostore.entities.Staff;

public class PersistMultipleExample {
	private static EntityManagerFactory emf 
		= Persistence.createEntityManagerFactory("VideoStore");

	public static void main(String[] args) {
		EntityManager em = emf.createEntityManager();
		
		// 1: Create an instance of a Rental entity.
		Rental r = new Rental();

		// 2: Call the various setter methods on your new entity to populate its fields.
		r.setStaff(em.find(Staff.class, 1));
		r.setCustomer(em.find(Customer.class, 1));
		r.setInventoryItem(em.find(InventoryItem.class, 1));
		r.setRentalDate(LocalDateTime.now());
		r.setReturnDate(LocalDateTime.now());

		// 3: Create an instance of a *Payment* entity.
		Payment p = new Payment();

		// 4: Call the setter methods on the *Payment* entity.
		p.setAmount(0.99);
		p.setPaymentDate(LocalDateTime.now());

		// 5: Add the *Payment* to the *Rental* by calling the `addPayment()` method.
		r.addPayment(p);
//		p.setRental(r);

		// 6: Persist the *Rental*, then the *Payment* using *EntityManager*’s
		// `persist()` method.
		System.out.println(r);
		System.out.println(p);
		em.getTransaction().begin();
		em.persist(r);
		em.persist(p);
		em.getTransaction().commit();
		System.out.println(r);
		System.out.println(p);
		System.out.println(p.getRental().getId());
	}
	
	
}