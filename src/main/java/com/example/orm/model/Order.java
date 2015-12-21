

package com.example.orm.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "orders")
public class Order implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orders_seq_gen")
	@SequenceGenerator(name = "orders_seq_gen", sequenceName = "orders_id_seq")
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id", nullable = false)
	private User customer;

	@Column(name = "updated", nullable = false)
	private Timestamp updated;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "menu_id", nullable = false)
	private LunchMenu lunchMenu;



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public User getCustomer() {
		return customer;
	}

	public void setCustomer(User customer) {
		this.customer = customer;
	}

	public Timestamp getUpdated() {
		return updated;
	}

	public void setUpdated(Timestamp updated) {
		this.updated = updated;
	}

	public LunchMenu getLunchMenu() {
		return lunchMenu;
	}

	public void setLunchMenu(LunchMenu lunchMenu) {
		this.lunchMenu = lunchMenu;
	}
}
