

package com.example.orm.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "lunch_menu_dish")
public class LunchMenuDish implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lunch_menu_dish_seq_gen")
    @SequenceGenerator(name = "lunch_menu_dish_seq_gen", sequenceName = "lunch_menu_dish_id_seq")
    private Long id;


    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "count", columnDefinition = "int default 1", nullable = false)
    private Integer count = 1;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "lunch_id", nullable = false)
    private LunchMenu lunchMenu;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "dish_id", nullable = false)
    private Dish dish;


    public LunchMenuDish() {
    }

    public LunchMenuDish(BigDecimal price, LunchMenu lunchMenu, Dish dish, Integer count) {
        this.price = price;
        this.lunchMenu = lunchMenu;
        this.dish = dish;
        if (count != null)
            this.count = count;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LunchMenu getLunchMenu() {
        return lunchMenu;
    }

    public void setLunchMenu(LunchMenu lunchMenu) {
        this.lunchMenu = lunchMenu;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
