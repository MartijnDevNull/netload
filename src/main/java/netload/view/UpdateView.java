package netload.view;

import netload.Exceptions.UpdateException;
import netload.controller.UpdateController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
public class UpdateView extends View {
    private UpdateController updateController;

    public UpdateView() {
        super("UpdateController");
        updateController = new UpdateController();
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
}
