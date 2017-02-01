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
import java.util.ArrayList;

import netload.Database.Stats;
import netload.controller.Scrape;
import netload.controller.Update;
import netload.model.Day;
import netload.model.Week;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class View {
    private Scrape scraper;
    private Update update;

    public View() {
        scraper = new Scrape();
        update = new Update();
    }

    @RequestMapping("/days/all")
    public ArrayList<Day> getAllDays() {
        try {
            return scraper.getAllDays();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @RequestMapping("/week/list")
    public ArrayList<Day> getWeekList() {
        try {
            return scraper.getWeekList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @RequestMapping("/week")
    public Week getWeek() {
        try {
            return scraper.getWeek();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @RequestMapping("/update")
    public boolean update() {
        try {
            return update.update();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
