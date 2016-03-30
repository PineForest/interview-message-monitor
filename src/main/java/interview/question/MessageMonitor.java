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

/**
 * EXERCISE
 * <ul>
 * <li>Build a Monitoring system that (3 points)</li>
 * <ul>
 * <li>Monitors a message generating sub-system</li>
 * <li>Allows Users that are interested in being notified about the messages to register</li>
 * <li>Notify the messages to the registered users</li>
 * </ul>
 * <li>Write unit tests to test the system (2 points)</li>
 * </ul>
 * Please note the following:
 * <ul>
 * <li>Simple standalone java program that generates messages randomly and sends it to clients that are registered</li>
 * <li>This program need not be interactive. Shoot for self running model</li>
 * <li>It is not required to use any J2EE api’s or frameworks</li>
 * </ul>
 * My assumptions:
 * <ul>
 * <li>I read "it generates messages randomly" to mean that the message frequency is random, not necessarily the
 * content.</li>
 * <li>the messaging and monitoring subsystems are within the same VM - the wording could suggest that the generating
 * subsystem be in a separate Java VM then the monitor(s). I chose not to "read into" the description and use the
 * simplest interpretation. Also supporting this guideline is the definition of the monitoring code as being a system
 * and the message generating code as being a subsystem. This may imply the latter is part of the system.</li>
 * <li>the messaging subsystem and monitoring system run in the same thread - there is no requirement that specifies
 * this or any other threading approach. For me, running these in separate threads is easier both conceptually and at
 * a practical level, but they add a small amount of extra work that is not required.</li>
 * </ul>
 */
public class MessageMonitor {
    public static void main(String[] args) {
        Monitor a = new Monitor("A");
        Monitor b = new Monitor("B");
        Monitor c = new Monitor("C");
        Messaging messaging = new Messaging(20, 100L, 5000L);
        messaging.addObserver(a);
        messaging.addObserver(b);
        messaging.addObserver(c);

        messaging.generateMessages();
    }
}
