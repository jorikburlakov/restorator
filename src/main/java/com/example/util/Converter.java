package com.example.util;

import com.example.dto.DishDTO;
import com.example.dto.LunchMenuDTO;
import com.example.dto.OrderDTO;
import com.example.dto.RestaurantDTO;
import com.example.orm.model.LunchMenu;
import com.example.orm.model.LunchMenuDish;
import com.example.orm.model.Order;
import com.example.orm.model.Restaurant;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alpha on 20.12.2015.
 */
public class Converter {

    public static List<RestaurantDTO> convertRestaurant(Iterable<Restaurant> list, boolean withMenu) {
        List<RestaurantDTO> result = new ArrayList<>();
        for (Restaurant entity : list) {
            result.add(convert(entity, withMenu));
        }
        return result;
    }

    public static RestaurantDTO convert(Restaurant entity, boolean withMenu) {
        RestaurantDTO result = new RestaurantDTO();
        result.setId(entity.getId());
        result.setName(entity.getName());
        result.setDescriptions(entity.getDescription());
        if (withMenu && entity.getMenu() != null)
            result.setMenu(convertLunchMenu(entity.getMenu()));
        return result;
    }

    public static List<LunchMenuDTO> convertLunchMenu(Iterable<LunchMenu> list) {
        List<LunchMenuDTO> result = new ArrayList<>();
        for (LunchMenu entity : list) {
            result.add(convert(entity));
        }
        return result;
    }

    public static LunchMenuDTO convert(LunchMenu entity) {
        LunchMenuDTO result = new LunchMenuDTO();
        result.setId(entity.getId());
        result.setName(entity.getName());
        result.setDescription(entity.getDescription());
        result.setUpdated(entity.getUpdated().getTime());
        result.setDishes(convertDish(entity.getDishes()));
        return result;
    }


    private static List<DishDTO> convertDish(Iterable<LunchMenuDish> dishes) {
        List<DishDTO> result = new ArrayList<>();
        for (LunchMenuDish entity : dishes) {
            result.add(convert(entity));
        }
        return result;
    }

    private static DishDTO convert(LunchMenuDish entity) {
        DishDTO result = new DishDTO();
        result.setId(entity.getId());
        result.setPrice(entity.getPrice());
        result.setName(entity.getDish().getName());
        result.setCalories(entity.getDish().getCalories());
        result.setDescription(entity.getDish().getDescription());
        result.setCount(entity.getCount());
        return result;
    }


    public static List<OrderDTO> convertOrder(List<Order> list) {
        List<OrderDTO> result = new ArrayList<>();
        for (Order entity : list) {
            result.add(convert(entity));
        }
        return result;
    }

    public static OrderDTO convert(Order order) {
        OrderDTO result = new OrderDTO();
        result.setUpdated(order.getUpdated().getTime());
        result.setLunchMenu(convert(order.getLunchMenu()));
        result.setRestaurant(convert(order.getLunchMenu().getRestaurant(), false));
        return result;
    }
}
