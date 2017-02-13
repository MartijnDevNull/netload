package netload.view;

import netload.Exceptions.DatabaseException;
import netload.controller.StatsController;
import netload.model.Day;
import netload.model.Total;
import netload.model.Week;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (C) 13/02/17 martijn.
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

@RestController
public class StatsView extends View{
    private StatsController statsController;

    public StatsView() {
        super("StatsController");
        statsController = new StatsController();
    }

    @CrossOrigin(origins = "*")
    @RequestMapping("/stats/days")
    public List<Day> getAllDaysStat() throws DatabaseException {
        try {
            return statsController.getAllDays();
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new DatabaseException("Can't get days");
        }
    }

    @CrossOrigin(origins = "*")
    @RequestMapping("/stats/total")
    public Total getAllDaysStatTotal() throws DatabaseException {
        try {
            return statsController.getTotalStats();
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new DatabaseException("Can't get total days");
        }
    }

    @CrossOrigin(origins = "*")
    @RequestMapping("/stats/weeks")
    public ArrayList<Week> getWeeksTotal() throws DatabaseException {
        try {
            return statsController.getTotalWeeks();
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new DatabaseException("Can't get total weeks");
        }
    }
}
