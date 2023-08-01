package com.skilldistillery.jpavideostore.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Film {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String title;
	private String description;
	@Column(name = "release_year")
	private int releaseYear;
	@Column(name = "rental_duration")
	private int rentalDuration;
	@Column(name = "rental_rate")
	private double rentalRate;
	private int length;
	@Column(name = "replacement_cost")
	private double replacementCost;

	@Enumerated(EnumType.STRING)
	private Rating rating;

	@ManyToOne
	@JoinColumn(name = "language_id")
	private Language language;

	@ManyToMany(mappedBy = "films")
//	@ManyToMany(mappedBy = "films",fetch = FetchType.EAGER)
	private List<Actor> actors;

	@ManyToMany(mappedBy = "films")
	private List<Category> categories;

	@OneToMany(mappedBy = "film")
	private List<InventoryItem> inventoryItems;
	//TODO - add/remove methods?
	
	public Film() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
	}

	public double getRentalRate() {
		return rentalRate;
	}

	public void setRentalRate(double rentalRate) {
		this.rentalRate = rentalRate;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getRentalDuration() {
		return rentalDuration;
	}

	public void setRentalDuration(int rentalDuration) {
		this.rentalDuration = rentalDuration;
	}

	public double getReplacementCost() {
		return replacementCost;
	}

	public void setReplacementCost(double replacementCost) {
		this.replacementCost = replacementCost;
	}

	public Rating getRating() {
		return rating;
	}

	public void setRating(Rating rating) {
		this.rating = rating;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public List<Actor> getActors() {
		return actors;
	}

	public void setActors(List<Actor> actors) {
		this.actors = actors;
	}

	public void addActor(Actor actor) {
		if (actors == null) {
			actors = new ArrayList<>();
		}
		if (!actors.contains(actor)) {
			actors.add(actor);
			actor.addFilm(this);
		}
	}

	public void removeActor(Actor actor) {
		if (actors != null && actors.contains(actor)) {
			actors.remove(actor);
			actor.removeFilm(this);
		}
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public void addCategory(Category cat) {
		if (categories == null) { categories = new ArrayList<>();}
		if ( ! categories.contains(cat)) {
			categories.add(cat);
			cat.addFilm(this);
		}
	}

	public void removeCategory(Category cat) {
		if (categories != null && categories.contains(cat)) {
			categories.remove(cat);
			cat.removeFilm(this);
		}
	}

	public List<InventoryItem> getInventoryItems() {
		return inventoryItems;
	}

	public void setInventoryItems(List<InventoryItem> inventoryItems) {
		this.inventoryItems = inventoryItems;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Film other = (Film) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Film [id=").append(id).append(", title=").append(title).append(", description=")
				.append(description).append(", releaseYear=").append(releaseYear).append(", rentalRate=")
				.append(rentalRate).append(", length=").append(length).append(", replacementCost=")
				.append(replacementCost).append("]");
		return builder.toString();
	}
}