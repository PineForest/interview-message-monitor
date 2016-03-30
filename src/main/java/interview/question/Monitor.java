/*
 * Copyright © 2016  David Williams
 *
 * This file is part of the interview-message-monitor project.
 *
 * interview-message-monitor is free software: you can redistribute it and/or modify it under the terms of the
 * Lesser GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * interview-message-monitor is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the Lesser GNU General Public
 * License for more details.
 *
 * You should have received a copy of the Lesser GNU General Public License along with interview-message-monitor.
 * If not, see <a href="http://www.gnu.org/licenses/">www.gnu.org/licenses/</a>.
 */

package interview.question;

import java.util.Objects;
import java.util.Observable;
import java.util.Observer;

public class Monitor implements Observer {
    private String name;

    public Monitor(String name) {
        this.name = name;
    }

    public void update(Observable o, Object arg) {
        if (o instanceof Messaging && arg instanceof Message) {
            Message message = (Message) arg;
            System.out.println(String.format("Monitor %s received this message: %s", name, message.getValue()));
            System.out.flush();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Monitor monitor = (Monitor) o;
        return Objects.equals(name, monitor.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
