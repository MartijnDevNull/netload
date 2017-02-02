package netload.controller;

import netload.Database.Stats;
import netload.model.Day;
import netload.model.Total;
import netload.model.Week;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Copyright (C) 02/02/17 martijn
 * <p>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
public class StatsController {
    private Stats stats;
    private Scrape scrape;

    public StatsController() {
        stats = new Stats();
        scrape = new Scrape();
    }

    public ArrayList<Day> getAllDays() {
        ArrayList<Day> days = (ArrayList<Day>) stats.getAllDays();
        Collections.sort(days);

        if (days.isEmpty()) {
            throw new NullPointerException("List is empty");
        } else {
            return days;
        }
    }

    public Total getTotalStats(){
        ArrayList<Day> allDays = getAllDays();
        double total=0, up=0, down=0;
        for (Day day: allDays
             ) {
            total+=day.getTotal();
            up+=day.getUp();
            down+=day.getDown();
        }
        return new Total(Math.ceil(total), Math.ceil(up), Math.ceil(down));
    }
}
