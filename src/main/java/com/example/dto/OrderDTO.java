package com.example.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alpha on 20.12.2015.
 */
public class OrderDTO {


    private Long updated;

    private LunchMenuDTO lunchMenu;

    private RestaurantDTO restaurant;

    public Long getUpdated() {
        return updated;
    }

    public void setUpdated(Long updated) {
        this.updated = updated;
    }

    public LunchMenuDTO getLunchMenu() {
        return lunchMenu;
    }

    public void setLunchMenu(LunchMenuDTO lunchMenu) {
        this.lunchMenu = lunchMenu;
    }

    public RestaurantDTO getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(RestaurantDTO restaurant) {
        this.restaurant = restaurant;
    }
}
