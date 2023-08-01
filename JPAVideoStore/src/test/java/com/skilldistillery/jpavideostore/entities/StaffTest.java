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

class StaffTest {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private Staff emp;

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
		emp = em.find(Staff.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		emp = null;
	}

	@Test
	void test_Staff_entity_mapping() {
		assertNotNull(emp);
		assertEquals("Larry", emp.getFirstName());
		assertEquals("Kong", emp.getLastName());
	}

	@Test
	void test_Staff_Address_ManyToOne_relationship_mapping() {
		assertNotNull(emp);
		assertNotNull(emp.getAddress());
		assertEquals("370 E. Rochelle Blvd", emp.getAddress().getStreet());
		assertEquals("Las Vegas", emp.getAddress().getCity());
	}
	
	@Test
	void test_Staff_Store_ManyToOne_mapping() {
		assertNotNull(emp);
		assertNotNull(emp.getStore());
		assertEquals(4, emp.getStore().getId());
	}
	
	@Test
	void test_Staff_supervisor_ManyToOne_mapping_no_supervisor() {
		assertNotNull(emp);
		assertNull(emp.getSupervisor());
	}

	@Test
	void test_Staff_supervisor_ManyToOne_mapping_with_supervisor() {
		emp = em.find(Staff.class, 10);
		assertNotNull(emp);
		assertNotNull(emp.getSupervisor());
		assertEquals(54, emp.getSupervisor().getId());
	}
	
	@Test
	void test_Staff_supervisee_OneToMany_mapping_with_supervisees() {
		assertNotNull(emp);
		assertNotNull(emp.getSupervisees());
		assertTrue(emp.getSupervisees().size() > 0);
	}
	
}