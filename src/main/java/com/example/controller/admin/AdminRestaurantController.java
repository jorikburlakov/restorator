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

import com.example.dto.OrderDTO;
import com.example.dto.RestaurantDTO;
import com.example.orm.model.Restaurant;
import com.example.orm.repository.OrderRepository;
import com.example.orm.repository.RestaurantRepository;
import com.example.util.Converter;
import com.example.util.DataHelper;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/admin")
public class AdminRestaurantController {


    private final Gson gson;

    private final OrderRepository orderRepository;

    private final RestaurantRepository restaurantRepository;

    @Autowired
    public AdminRestaurantController(Gson gson, OrderRepository orderRepository, RestaurantRepository restaurantRepository) {
        this.gson = gson;
        this.orderRepository = orderRepository;
        this.restaurantRepository = restaurantRepository;
    }


    @RequestMapping(value = "/restaurants", method = RequestMethod.GET)
    public Iterable<RestaurantDTO> list() {
        return Converter.convertRestaurant(restaurantRepository.findAll(), false);
    }


    @RequestMapping(value = "/restaurants", method = RequestMethod.POST)
    public String add(@RequestBody String body) {
        RestaurantDTO restaurant = gson.fromJson(body, RestaurantDTO.class);

        restaurantRepository.save(new Restaurant(restaurant.getName(), restaurant.getDescriptions()));
        return "ok";
    }

    @RequestMapping(value = "/restaurants/{id}", method = RequestMethod.PUT)
    public String update(@PathVariable Long id, @RequestBody String body) {
        Restaurant restaurant = restaurantRepository.findOne(id);
        if (restaurant != null) {
            RestaurantDTO dto = gson.fromJson(body, RestaurantDTO.class);
            if (dto.getName() != null && !dto.getName().isEmpty())
                restaurant.setName(dto.getName());
            if (dto.getDescriptions() != null && !dto.getDescriptions().isEmpty())
                restaurant.setDescription(dto.getDescriptions());
            restaurantRepository.save(restaurant);
            return "ok";
        } else
            throw new IllegalArgumentException("Restaurant not found");
    }

    @RequestMapping(value = "/restaurants/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable Long id) {
        restaurantRepository.delete(id);
        return "ok";
    }

    @RequestMapping(value = "/restaurants/{id}/orders", method = RequestMethod.GET)
    public List<OrderDTO> ordersToday(@PathVariable Long id) {
        Restaurant restaurant = restaurantRepository.findOne(id);
        if (restaurant != null) {
      return Converter.convertOrder(orderRepository.findByLunchMenuRestaurantAndUpdatedBetween(restaurant, DataHelper.getYesterdayOrderTime(), DataHelper.getTodayOrderTime()));

        } else
            throw new IllegalArgumentException("Restaurant not found");
    }

//    @RequestMapping(value = "/restaurants/{id}/ordersTomorrow", method = RequestMethod.GET)
//    public List<OrderDTO> ordersTomorrow(@PathVariable Long id) {
//        Restaurant restaurant = restaurantRepository.findOne(id);
//        if (restaurant != null) {
//            return Converter.convertOrder(orderRepository.findByLunchMenuRestaurantAndUpdatedBetween(restaurant, DataHelper.getTodayOrderTime(), DataHelper.getTomorrowOrderTime()));
//
//        } else
//            throw new IllegalArgumentException("Restaurant not found");
//    }

}
