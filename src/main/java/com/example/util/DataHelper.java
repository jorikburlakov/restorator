package com.example.util;

import java.sql.Timestamp;
import java.time.*;
import java.util.Date;

/**
 * Created by Alpha on 20.12.2015.
 */
public class DataHelper {

    public static Timestamp getTodayOrderTime(){
        LocalTime midnight = LocalTime.of(11, 00);
        LocalDate today = LocalDate.now(ZoneId.systemDefault());
        LocalDateTime todayMidnight = LocalDateTime.of(today, midnight);
        return  new Timestamp(Date.from(todayMidnight.atZone(ZoneId.systemDefault()).toInstant()).getTime());
    }

    public static Timestamp getTomorrowOrderTime(){
        LocalTime midnight = LocalTime.of(11, 00);
        LocalDate today = LocalDate.now(ZoneId.systemDefault());
        LocalDateTime todayMidnight = LocalDateTime.of(today, midnight);
        LocalDateTime tomorrowMidnight = todayMidnight.plusDays(1);
        return new Timestamp(Date.from(tomorrowMidnight.atZone(ZoneId.systemDefault()).toInstant()).getTime());
    }

    public static Timestamp getYesterdayOrderTime(){
        LocalTime midnight = LocalTime.of(11, 00);
        LocalDate today = LocalDate.now(ZoneId.systemDefault());
        LocalDateTime todayMidnight = LocalDateTime.of(today, midnight);
        LocalDateTime yesterdaywMidnight = todayMidnight.minusDays(1);
        return new Timestamp(Date.from(yesterdaywMidnight.atZone(ZoneId.systemDefault()).toInstant()).getTime());
    }

    public static boolean isCanCreateOrder(){
        LocalTime midnight = LocalTime.of(11, 00);
        LocalTime now = LocalTime.now();
        return now.isBefore(midnight);
    }


}
