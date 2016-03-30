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

public class Message {
    private String value;

    public Message(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
