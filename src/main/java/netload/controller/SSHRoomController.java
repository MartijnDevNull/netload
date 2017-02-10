package netload.controller;

import netload.model.SSHRoom;
import org.apache.log4j.Logger;
import sun.misc.IOUtils;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Scanner;

/**
 * Copyright (C) 10/02/17 martijn.
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
public class SSHRoomController {
    private static SSHRoomController instance = null;
    private InputStream input;
    private Properties properties;
    private Logger log;

    protected SSHRoomController() {
        properties = new Properties();
        log = org.apache.log4j.Logger.getLogger(SSHRoom.class.getName());
        try {
            properties.load(new FileInputStream(getFile("sshroom.properties")));
        } catch (IOException e) {
            log.error(e.getMessage());
            log.info("sshroom.properties not found, have you created this file?");
        }
    }

    public static SSHRoomController getInstance() {
        if (instance == null) {
            instance = new SSHRoomController();
        }
        return instance;
    }

    private SSHRoom getSSHRoom() {
        return new SSHRoom(properties.getProperty("outlet"),
                properties.getProperty("housenumber"), properties.getProperty("roomnumber"));
    }

    public String getScrapeURL() {
        SSHRoom room = getSSHRoom();
        return String.format("http://helpdesk.sshnet.nl/v12/q/netload.php?outlet=%s&housenr=%s&roomnr=%s&submit=submit",
                room.getOutlet(), room.getHouseNumber(), room.getRoomNumber());
    }

    private File getFile(String fileName) {
        StringBuilder result = new StringBuilder("");
        ClassLoader classLoader = getClass().getClassLoader();
        return new File(classLoader.getResource(fileName).getFile());
    }
}
