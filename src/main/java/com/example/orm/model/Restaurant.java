

package com.example.orm.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "restourants")
public class Restaurant implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "restourants_seq_gen")
	@SequenceGenerator(name = "restourants_seq_gen", sequenceName = "restourants_id_seq")
	private Long id;

	@Column(name="name", nullable = false)
	private String name;


	@Column(name="description")
	private String description;


	@OneToMany(fetch = FetchType.EAGER, cascade =CascadeType.ALL, mappedBy = "restaurant")
	private List<LunchMenu> menu = new ArrayList<>();


	public Restaurant() {
	}

	public Restaurant(String name, String description) {
		this.name = name;
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<LunchMenu> getMenu() {
		return menu;
	}

	public void setMenu(List<LunchMenu> menu) {
		this.menu = menu;
	}
}
