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

package com.example.controller.user;

import com.example.dto.DishDTO;
import com.example.dto.LunchMenuDTO;
import com.example.dto.OrderDTO;
import com.example.orm.model.*;
import com.example.orm.repository.*;
import com.example.util.Converter;
import com.example.util.DataHelper;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.ZoneId;
import java.util.Calendar;

@RestController
@RequestMapping(value = "/user")
public class OrderController {


    private final Gson gson;



    private final OrderRepository orderRepository;

    private final LunchMenuRepository lunchMenuRepository;

    private final RestaurantRepository restaurantRepository;

    private final UserRepository userRepository;

    @Autowired
    public OrderController(Gson gson,  OrderRepository orderRepository, LunchMenuRepository lunchMenuRepository, RestaurantRepository restaurantRepository, UserRepository userRepository) {
        this.gson = gson;

        this.orderRepository = orderRepository;
        this.lunchMenuRepository = lunchMenuRepository;
        this.restaurantRepository = restaurantRepository;
        this.userRepository = userRepository;
    }


    @RequestMapping(value = "/order", method = RequestMethod.GET)
    public OrderDTO list() {
        return Converter.convert(orderRepository.findByCustomerLoginAndUpdatedBetween(SecurityContextHolder.getContext().getAuthentication().getName(), DataHelper.getYesterdayOrderTime(), DataHelper.getTodayOrderTime()));
    }


    @RequestMapping(value = "/order/{rest_id}/lunch/{name}", method = RequestMethod.POST)
    public String order(@PathVariable Long rest_id, @PathVariable String name) {
        if(DataHelper.isCanCreateOrder()) {
            Restaurant restaurant = restaurantRepository.findOne(rest_id);
            if (restaurant != null) {
                Order order = orderRepository.findByCustomerLoginAndUpdatedBetween(SecurityContextHolder.getContext().getAuthentication().getName(), DataHelper.getYesterdayOrderTime(), DataHelper.getTodayOrderTime());
                if (order == null) {
                    order = new Order();
                }
                order.setUpdated(new Timestamp(Calendar.getInstance().getTimeInMillis()));
                order.setCustomer(userRepository.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName()));
                order.setLunchMenu(lunchMenuRepository.findByRestaurantAndNameAndUpdatedBetween(restaurant, name, DataHelper.getYesterdayOrderTime(), DataHelper.getTodayOrderTime()));
                orderRepository.save(order);
                return "ok";
            } else
                throw new IllegalArgumentException("Restaurant not found");
        }
        return "Can't create order, you can create order tommorow before 11:00 by"+ ZoneId.systemDefault().getId();
    }




    @RequestMapping(value = "order", method = RequestMethod.DELETE)
    public String delete() {
        orderRepository.deleteByCustomerLoginAndUpdatedBetween(SecurityContextHolder.getContext().getAuthentication().getName(),
                DataHelper.getYesterdayOrderTime(), DataHelper.getTodayOrderTime());
        return "ok";

    }

}
