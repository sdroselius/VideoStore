package com.skilldistillery.jpavideostore.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Language {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;

	@OneToMany(mappedBy = "language")
	private List<Film> films;

	public Language() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Film> getFilms() {
		return films;
	}

	public void setFilms(List<Film> films) {
		this.films = films;
	}

	public void addFilm(Film film) {
		if (film == null) {
			films = new ArrayList<>();
		}
		if (!films.contains(film)) {
			films.add(film);
			film.setLanguage(this);
		}
	}

	public void removeFilm(Film film) {
		if (films != null && films.contains(film)) {
			films.remove(film);
			film.setLanguage(null);
		}
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Language [id=").append(id).append(", name=").append(name).append("]");
		return builder.toString();
	}
}