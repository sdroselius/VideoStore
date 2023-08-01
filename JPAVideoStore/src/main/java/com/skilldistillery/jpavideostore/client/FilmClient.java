package com.skilldistillery.jpavideostore.client;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.skilldistillery.jpavideostore.entities.Film;
import com.skilldistillery.jpavideostore.entities.Language;

public class FilmClient {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("VideoStore");
		EntityManager em = emf.createEntityManager();

//		String query = "SELECT DISTINCT f.actors FROM Film f where f.rating = :rating";
////		List<Actor> actors = em.createQuery(query, Actor.class)
//		List<Object> results = em.createQuery(query, Object.class)
//			  .setParameter("rating", xyz)
//		      .getResultList();

//		List<Actor> actors = new ArrayList<>();

//		for (Object object : results) {
//			actors.add((Actor)object);
//		}

//		results.forEach( x -> actors.add((Actor)x) );
//
//		for (Actor actor : actors) {
//			System.out.println(actor.getFirstName() + " " + actor.getLastName());
//		}
//		Rating xyz = Rating.G;
//
//		String query = "SELECT DISTINCT f FROM Film f JOIN FETCH f.actors a WHERE f.rating = :rating";
//		List<Film> films = em.createQuery(query, Film.class)
//				  .setParameter("rating", xyz)
//			      .getResultList();
//		
//		em.close();
//		
//		for (Film film : films) {
//			System.out.println(film.getTitle() + ": " + film.getActors().size());
//		}

		Film film = new Film();
		film.setTitle("Something2");
		film.setDescription("A description");

		if (film.getLanguage() == null) {
			Language lang = em.find(Language.class, 1);
			film.setLanguage(lang); // sets the foreign key language_id to 1 when persisted
		}

		System.out.println(film);
		System.out.println(film.getLanguage());
		em.getTransaction().begin();
		em.persist(film);
		em.getTransaction().commit();

		System.out.println(film);
//		Scanner kb = new Scanner(System.in);
//		
//		System.out.println("Before em.find");
//		kb.nextLine();
//		Film film = em.find(Film.class, 1);
//		
//		System.out.println("After em.find, before toString");
//		kb.nextLine();
//		System.out.println(film);
////		film.getActors().size();
//		System.out.println("After toString, before getActors");
//		kb.nextLine();
//		System.out.println(film.getActors());
//		
//		kb.close();
		emf.close();
	}

}