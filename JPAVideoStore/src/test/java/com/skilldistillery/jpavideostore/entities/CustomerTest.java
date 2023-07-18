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

class CustomerTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private Customer cust;

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
		cust = em.find(Customer.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		cust = null;
	}

	// | 1 | 7 | Mary | Smithers | MARY.SMITH@sdvidcustomer.org | 5 | 1 | 2014-05-25
	// 00:00:00 | 2016-09-16 11:32:19 |
	@Test
	void test_Customer_entity_mapping() {
		assertNotNull(cust);
		assertEquals("Mary", cust.getFirstName());
		assertEquals("Smithers", cust.getLastName());
	}

	@Test
	void test_Customer_temporal_mapping() {
		assertNotNull(cust);
		assertNotNull(cust.getCreatedAt());
		assertEquals(2014, cust.getCreatedAt().getYear());
		assertEquals(05, cust.getCreatedAt().getMonthValue());
		assertEquals(25, cust.getCreatedAt().getDayOfMonth());
		assertNotNull(cust.getLastUpdate());
		assertEquals(2016, cust.getLastUpdate().getYear());
		assertEquals(9, cust.getLastUpdate().getMonthValue());
		assertEquals(16, cust.getLastUpdate().getDayOfMonth());
	}

	@Test
	void test_Customer_Address_OneToOne_mapping() {
		Customer cust = em.find(Customer.class, 1);
		assertNotNull(cust);
		assertNotNull(cust.getAddress());
		assertEquals(5, cust.getAddress().getId());
		assertEquals("1913 Hanoi Way", cust.getAddress().getStreet());
		assertEquals("Sasebo", cust.getAddress().getCity());
		assertEquals("35200", cust.getAddress().getPostalCode());
	}

	@Test
	public void test_Customer_Rental_OneToMany_relationship() {
		assertNotNull(cust);
		assertNotNull(cust.getRentals());
		assertTrue(cust.getRentals().size() > 0);
//			assertEquals(95, cust.getRentals().size());
	}

	@Test
	public void test_Customer_Store_ManyToOne_relationship() {
		assertNotNull(cust);
		assertNotNull(cust.getStore());
		assertEquals(7, cust.getStore().getId());
	}

}