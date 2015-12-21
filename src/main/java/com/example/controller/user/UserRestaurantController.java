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

import com.example.dto.RestaurantDTO;
import com.example.orm.model.Restaurant;
import com.example.orm.repository.LunchMenuRepository;
import com.example.orm.repository.RestaurantRepository;
import com.example.util.Converter;
import com.example.util.DataHelper;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/user")
public class UserRestaurantController {


    private final Gson gson;

    private final RestaurantRepository restaurantRepository;

    private final LunchMenuRepository lunchMenuRepository;

    @Autowired
    public UserRestaurantController(Gson gson, RestaurantRepository restaurantRepository, LunchMenuRepository lunchMenuRepository) {
        this.gson = gson;
        this.restaurantRepository = restaurantRepository;
        this.lunchMenuRepository = lunchMenuRepository;
    }


    @RequestMapping(value = "/restaurants/", method = RequestMethod.GET)
    public Iterable<RestaurantDTO> list() {
        return Converter.convertRestaurant(lunchMenuRepository.findByUpdatedBetween(DataHelper.getYesterdayOrderTime(), DataHelper.getTodayOrderTime()).stream().map(v -> v.getRestaurant()).collect(Collectors.toList()), true);
    }

}
