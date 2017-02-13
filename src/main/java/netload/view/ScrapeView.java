package netload.view;

import netload.Exceptions.ScraperException;
import netload.controller.ScrapeController;
import netload.model.Day;
import netload.model.Week;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

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
public class ScrapeView extends View {
    private ScrapeController scraper;

    public ScrapeView() {
        super("ScrapeView");
        scraper = new ScrapeController();
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
}
