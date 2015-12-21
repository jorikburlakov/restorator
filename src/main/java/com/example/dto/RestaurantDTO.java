package com.example.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alpha on 20.12.2015.
 */
public class RestaurantDTO {

    private Long id;

    private String name;

    private String descriptions;


    private List<LunchMenuDTO> menu = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public List<LunchMenuDTO> getMenu() {
        return menu;
    }

    public void setMenu(List<LunchMenuDTO> menu) {
        this.menu = menu;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
