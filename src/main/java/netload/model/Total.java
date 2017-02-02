package netload.model;

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
public class Total {
    private double total;
    private double up;
    private double down;

    public Total() {
    }

    public Total(double total, double up, double down) {
        this.total = total;
        this.up = up;
        this.down = down;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getUp() {
        return up;
    }

    public void setUp(double up) {
        this.up = up;
    }

    public double getDown() {
        return down;
    }

    public void setDown(double down) {
        this.down = down;
    }
}
