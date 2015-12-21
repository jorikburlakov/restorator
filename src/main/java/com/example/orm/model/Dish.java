

package com.example.orm.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "dishes")
public class Dish implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dishes_seq_gen")
    @SequenceGenerator(name = "dishes_seq_gen", sequenceName = "dishes_id_seq")
    private Long id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;


    @Column(name="description")
    private String description;

    @Column(name = "calories")
    private Integer calories;

    public Dish() {
    }

    public Dish(String name, String description, Integer calories) {
        this.name = name;
        this.description = description;
        this.calories = calories;
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

    public Integer getCalories() {
        return calories;
    }

    public void setCalories(Integer calories) {
        this.calories = calories;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
