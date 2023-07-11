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

class FilmTest {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private Film film;

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
		film = em.find(Film.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		film = null;
	}

//	|  1 | ACADEMY DINOSAUR | A Epic Drama... |         1993 |           3 |               6 |        0.99 |     86 |            20.99 | PG     | Deleted Scenes,Behind the Scenes |
	@Test
	void test_Film_entity_mapping() {
		assertNotNull(film);
		assertEquals("ACADEMY DINOSAUR", film.getTitle());
		assertTrue(film.getDescription().startsWith("A Epic Drama"));
		assertEquals(1993, film.getReleaseYear());
		assertEquals(0.99, film.getRentalRate(), .001);
		assertEquals(86, film.getLength());
		assertEquals(20.99, film.getReplacementCost(), .001);
	}

}