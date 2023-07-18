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

class StoreTest {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private Store store;

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
		store = em.find(Store.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		store = null;
	}

	@Test
	void test_Store_entity_mapping() {
		assertNotNull(store);
	}

	@Test
	void test_Store_Address_OneToOne_relationship_mapping() {
		assertNotNull(store);
		assertNotNull(store.getAddress());
		assertEquals("264 Blanchard Ave", store.getAddress().getStreet());
		assertEquals("Seattle", store.getAddress().getCity());
	}
	
	@Test
	void test_Store_staff_OneToOne_relationship_mapping() {
		assertNotNull(store);
		assertNotNull(store.getManager());
		assertEquals("Dutch", store.getManager().getFirstName());
		assertEquals("LaFever", store.getManager().getLastName());
	}
	
	@Test
	void test_Store_Customer_OneToMany_relationship_mapping() {
		assertNotNull(store);
		assertNotNull(store.getCustomers());
		assertNotNull(store.getCustomers().size() > 0);
	}
	
	@Test
	void test_Store_Staff_OneToMany_relationship_mapping() {
		assertNotNull(store);
		assertNotNull(store.getEmployees());
		assertNotNull(store.getEmployees().size() > 0);
	}
	


}