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

class PaymentTest {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private Payment payment;

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
		payment = em.find(Payment.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		payment = null;
	}

//	| id | rental_id | amount | payment_date        |
//	+----+-----------+--------+---------------------+
//	|  1 |        76 |   2.99 | 2014-05-25 11:30:37 |

	@Test
	void test_Payment_entity_mapping() {
		assertNotNull(payment);
		assertEquals(2.99, payment.getAmount());
		assertNotNull(payment.getPaymentDate());
		assertEquals(2014, payment.getPaymentDate().getYear());
		assertEquals(5, payment.getPaymentDate().getMonthValue());
	}
	
	@Test
	void test_Payment_Rental_ManyToOne_mapping() {
		assertNotNull(payment);
		assertNotNull(payment.getRental());
		assertEquals(76, payment.getRental().getId());
	}

}