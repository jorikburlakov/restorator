/*
 * Copyright 2014-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.controller.admin;

import com.example.dto.DishDTO;
import com.example.dto.LunchMenuDTO;
import com.example.orm.model.Dish;
import com.example.orm.model.LunchMenu;
import com.example.orm.model.LunchMenuDish;
import com.example.orm.model.Restaurant;
import com.example.orm.repository.*;
import com.example.util.Converter;
import com.example.util.DataHelper;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Calendar;

@RestController
@RequestMapping(value = "/admin")
public class LunchMenuController {


    private final Gson gson;

    private final LunchMenuDishRepository lunchMenuDishRepository;

    private final DishRepository dishRepository;

    private final LunchMenuRepository lunchMenuRepository;

    private final RestaurantRepository restaurantRepository;

    private final UserRepository userRepository;

    @Autowired
    public LunchMenuController(Gson gson, LunchMenuDishRepository lunchMenuDishRepository, DishRepository dishRepository, LunchMenuRepository lunchMenuRepository, RestaurantRepository restaurantRepository, UserRepository userRepository) {
        this.gson = gson;
        this.lunchMenuDishRepository = lunchMenuDishRepository;
        this.dishRepository = dishRepository;
        this.lunchMenuRepository = lunchMenuRepository;
        this.restaurantRepository = restaurantRepository;
        this.userRepository = userRepository;
    }


    @RequestMapping(value = "/restaurants/{rest_id}/lunchToday", method = RequestMethod.GET)
    public Iterable<LunchMenuDTO> listToday(@PathVariable Long rest_id) {
        Restaurant restaurant = restaurantRepository.findOne(rest_id);
        if (restaurant != null) {

//            return Converter.convertLunchMenu(lunchMenuRepository.findByRestaurant(restaurant));
            return Converter.convertLunchMenu(lunchMenuRepository.findByRestaurantAndUpdatedBetween(restaurant
                    , DataHelper.getYesterdayOrderTime()
                    , DataHelper.getTodayOrderTime()));
        } else
            throw new IllegalArgumentException("Restaurant not found");
    }

    @RequestMapping(value = "/restaurants/{rest_id}/lunchTomorrow", method = RequestMethod.GET)
    public Iterable<LunchMenuDTO> listTomorrow(@PathVariable Long rest_id) {
        Restaurant restaurant = restaurantRepository.findOne(rest_id);
        if (restaurant != null) {

//            return Converter.convertLunchMenu(lunchMenuRepository.findByRestaurant(restaurant));
            return Converter.convertLunchMenu(lunchMenuRepository.findByRestaurantAndUpdatedBetween(restaurant
                    , DataHelper.getTodayOrderTime()
                    , DataHelper.getTomorrowOrderTime()));
        } else
            throw new IllegalArgumentException("Restaurant not found");
    }


    @RequestMapping(value = "/restaurants/{rest_id}/lunch", method = RequestMethod.POST)
    public String add(@PathVariable Long rest_id, @RequestBody String body) {
        Restaurant restaurant = restaurantRepository.findOne(rest_id);
        if (restaurant != null) {
            LunchMenuDTO dto = gson.fromJson(body, LunchMenuDTO.class);
            LunchMenu menu = lunchMenuRepository.findByRestaurantAndNameAndUpdatedBetween(restaurant, dto.getName()
                    , DataHelper.getTodayOrderTime()
                    , DataHelper.getTomorrowOrderTime());
            if (menu == null) {
                menu = new LunchMenu();
                if (dto.getName() != null && !dto.getName().isEmpty())
                    menu.setName(dto.getName());
                else
                    throw new IllegalArgumentException("Menu name is empty");
                menu.setDescription(dto.getDescription());
                menu.setAuthor(userRepository.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName()));
                menu.setUpdated(new Timestamp(Calendar.getInstance().getTimeInMillis()));
                menu.setRestaurant(restaurant);
                menu = lunchMenuRepository.save(menu);
                for (DishDTO dish : dto.getDishes()) {
                    Dish entity = dishRepository.findByName(dish.getName());
                    if (entity == null) {
                        entity = dishRepository.save(new Dish(dish.getName(), dish.getDescription(), dish.getCalories()));
                    }
                    lunchMenuDishRepository.save(new LunchMenuDish(dish.getPrice(), menu, entity, dish.getCount()));
                }
                return "ok";
            } else
                throw new IllegalArgumentException("Menu with this name on today already exist");
        } else
            throw new IllegalArgumentException("Restaurant not found");

    }

    @RequestMapping(value = "/restaurants/{rest_id}/lunch", method = RequestMethod.PUT)
    public String update(@PathVariable Long rest_id, @RequestBody String body) {
        Restaurant restaurant = restaurantRepository.findOne(rest_id);
        if (restaurant != null) {
            LunchMenuDTO dto = gson.fromJson(body, LunchMenuDTO.class);
            LunchMenu menu = lunchMenuRepository.findByRestaurantAndNameAndUpdatedBetween(restaurant, dto.getName()
                    , DataHelper.getTodayOrderTime()
                    , DataHelper.getTomorrowOrderTime());
            if (menu != null) {
                if (dto.getName() != null && !dto.getName().isEmpty())
                    menu.setName(dto.getName());
                else
                    throw new IllegalArgumentException("Menu name is empty");
                menu.setDescription(dto.getDescription());
                menu.setAuthor(userRepository.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName()));
                menu.setUpdated(new Timestamp(Calendar.getInstance().getTimeInMillis()));
                lunchMenuDishRepository.delete(menu.getDishes());
                for (DishDTO dish : dto.getDishes()) {
                    Dish entity = dishRepository.findByName(dish.getName());
                    if (entity == null) {
                        entity = dishRepository.save(new Dish(dish.getName(), dish.getDescription(), dish.getCalories()));
                    }
                    lunchMenuDishRepository.save(new LunchMenuDish(dish.getPrice(), menu, entity,dish.getCount()));
                }

                return "ok";
            } else
                throw new IllegalArgumentException("Menu with this name on today not exist");
        } else
            throw new IllegalArgumentException("Restaurant not found");

    }


    @RequestMapping(value = "/restaurants/{rest_id}/lunch/{name}", method = RequestMethod.DELETE)
    public String delete(@PathVariable Long rest_id, @PathVariable String name) {
        Restaurant restaurant = restaurantRepository.findOne(rest_id);
        if (restaurant != null) {
            lunchMenuRepository.deleteByRestaurantAndNameAndUpdatedBetween(restaurant, name, DataHelper.getTodayOrderTime(), DataHelper.getTomorrowOrderTime());
            return "ok";
        } else
            throw new IllegalArgumentException("Restaurant not found");
    }

}
