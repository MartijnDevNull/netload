package netload.controller;

import netload.model.Day;
import netload.model.Week;
import org.joda.time.DateTime;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
public class ScrapeController {
    private String URL;
    private SimpleDateFormat formatter;

    public ScrapeController() {
        formatter = new SimpleDateFormat("yyyy-MM-dd");
        URL = SSHRoomController.getInstance().getScrapeURL();
    }

    public ArrayList<Day> getAllDays() throws IOException, ParseException {
        ArrayList<Day> days = new ArrayList<>();
        Iterator<Element> elements = getElements();

        // skip first
        elements.next();
        while(elements.hasNext()) {
            Iterator<Element> row = elements.next().select("td").iterator();
            Day day = new Day();

            while (row.hasNext()){
                day.setDatum(formatter.parse(row.next().text()));
                day.setTotal(Double.parseDouble(row.next().text()));
                day.setDown(Double.parseDouble(row.next().text()));
                day.setUp(Double.parseDouble(row.next().text()));
            }
            days.add(day);
        }
        return days;
    }

    public ArrayList<Day> getWeekList() throws IOException, ParseException {
        List<Day> allDays = getAllDays();
        ArrayList<Day> week = new ArrayList<>();

        for (Day day: allDays
             ) {
            DateTime dayTime = new DateTime(day.getDatum());
            week.add(day);
            if (dayTime.getDayOfWeek() == 7) {
                break;
            }
        }

        return week;
    }

    public Week getWeek() throws IOException, ParseException {
        Week week = new Week();
        List<Day> days = getWeekList();
        double total=0, up=0, down=0;
        for (Day day: days
             ) {
            total+=day.getTotal();
            up+=day.getUp();
            down+=day.getDown();
        }
        return new Week(0, new DateTime(days.get(0).getDatum()).getWeekOfWeekyear(), Math.ceil(total), Math.ceil(up), Math.ceil(down));
    }

    public Iterator<Element> getElements() throws IOException {
        Document doc = Jsoup.connect(this.URL).get();
        Element table = doc.select("table").first();
        return table.select("tr").iterator();
    }
}
