package netload.controller;

import netload.database.Stats;

import java.io.IOException;
import java.text.ParseException;

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
public class Update {
    private Stats stats;
    private Scrape scrape;

    public Update() {
        stats = new Stats();
        scrape = new Scrape();
    }

    public boolean update() throws IOException, ParseException {
        scrape.getAllDays().forEach(day -> {
            try {
                stats.addDay(day);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });
        return true;
    }
}
