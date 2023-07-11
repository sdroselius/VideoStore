package com.skilldistillery.jpavideostore.entities;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.skilldistillery.jpavideostore.client.JPQLLab;

class JPQLTest {
	
	private JPQLLab lab;

	@BeforeEach
	void setUp() throws Exception {
		lab = new JPQLLab();
	}

	@AfterEach
	void tearDown() throws Exception {
		lab = null;
	}

	@Test
	void test_getRangeOfCustomers() {
		List<Customer> customers = lab.getRangeOfCustomers(100, 110);
		assertNotNull(customers);
		assertTrue(customers.size() > 0);
		assertEquals(11, customers.size());
	}

	//|  3 |        4 | Linda      | Williams  | LINDA.WILLIAMS@sdvidcustomer.org |          7 |      1 | 2014-05-27 17:07:36 | 2015-12-28 17:07:36 |
	@Test
	void test_getCustomerEmailByName() {
		String email = lab.getCustomerEmailByName("Linda", "Williams");
		assertNotNull(email);
		assertEquals("LINDA.WILLIAMS@sdvidcustomer.org", email);
	}
	
	@Test
	public void test_getFilmByTitle() {
		Film f = lab.getFilmByTitle("ACADEMY DINOSAUR");
		assertNotNull(f);
		assertEquals(1, f.getId());
	}
	
	@Test
	public void test_getFilmByTitle_returns_null_for_invalid_title() {
		Film f = lab.getFilmByTitle("AAAAAAAAAAA");
		assertNull(f);
	}
	
	@Test
	public void test_getFilmsTitlesByReleaseYear() {
		List<String> titles = lab.getFilmsTitlesByReleaseYear(1993);
		assertNotNull(titles);
		assertTrue(titles.size() > 0);
		assertEquals(26, titles.size());
	}
	
	
}