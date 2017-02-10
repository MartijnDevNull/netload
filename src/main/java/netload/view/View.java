package netload.view;
/**
 * Copyright (C) 01/02/17 martijn
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
import netload.Exceptions.DatabaseException;
import netload.Exceptions.ScraperException;
import netload.Exceptions.UpdateException;
import netload.controller.ScrapeController;
import netload.controller.StatsController;
import netload.controller.UpdateController;
import netload.model.Day;
import netload.model.Total;
import netload.model.Week;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class View {
    private ScrapeController scraper;
    private UpdateController updateController;
    private StatsController statsController;
    static Logger log;

    public View() {
        scraper = new ScrapeController();
        updateController = new UpdateController();
        statsController = new StatsController();
        log = Logger.getLogger(View.class.getName());
    }

    @RequestMapping("/scrape/days")
    @CrossOrigin(origins = "*")
    public ArrayList<Day> getAllDays() throws ScraperException {
        try {
            return scraper.getAllDays();
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ScraperException("Something went wrong scraping days");
        }
    }

    @CrossOrigin(origins = "*")
    @RequestMapping("/scrape/week/list")
    public ArrayList<Day> getWeekList() throws ScraperException {
        try {
            return scraper.getWeekList();
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ScraperException("Something went wrong listing week");
        }
    }

    @CrossOrigin(origins = "*")
    @RequestMapping("/scrape/week")
    public Week getWeek() throws ScraperException {
        try {
            return scraper.getWeek();
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ScraperException("Something went wrong scraping week");
        }
    }

    @CrossOrigin(origins = "*")
    @RequestMapping("/update")
    public boolean update() throws UpdateException {
        try {
            return updateController.update();
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new UpdateException("Can't update database");
        }
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
