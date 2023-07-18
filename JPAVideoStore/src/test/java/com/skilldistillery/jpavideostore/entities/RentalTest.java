package com.skilldistillery.jpavideostore.entities;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RentalTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private Rental rental;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		emf = Persistence.createEntityManagerFactory("VideoStore");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		emf.close();
	}

	@BeforeEach
	void setUp() throws Exception {
		em = emf.createEntityManager();
		rental = em.find(Rental.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		rental = null;
	}

	// | 1 | 2014-05-24 22:53:30 | 14072 | 130 | 2014-05-26 22:04:30 | 46 |

	@Test
	void test_Rental_entity_mapping() {
		assertNotNull(rental);
		assertNotNull(rental.getRentalDate());
		assertEquals(2014, rental.getRentalDate().getYear());
		assertEquals(05, rental.getRentalDate().getMonthValue());
		assertNotNull(rental.getReturnDate());
	}

	@Test
	void test_Rental_Staff_ManyToOne_relationship_mapping() {
		assertNotNull(rental);
		assertNotNull(rental.getStaff());
		assertEquals("William", rental.getStaff().getFirstName());
		assertEquals("Tingvold", rental.getStaff().getLastName());
	}

	@Test
	void test_Rental_Customer_ManyToOne_relationship_mapping() {
		assertNotNull(rental);
		assertNotNull(rental.getCustomer());
		assertEquals("Charlotte", rental.getCustomer().getFirstName());
		assertEquals("Hunter", rental.getCustomer().getLastName());
	}

}