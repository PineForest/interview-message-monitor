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

import java.util.Observable;

public class Messaging extends Observable {
    private int countMessages;
    private int sequence;
    private long minDelayInMillis;
    private long maxDelayInMillis;

    public Messaging(int countMessages, long minDelayInMillis, long maxDelayInMillis) {
        this.countMessages = countMessages;
        sequence = 0;
        this.minDelayInMillis = minDelayInMillis;
        this.maxDelayInMillis = maxDelayInMillis;
    }

    public void generateMessages() {
        while (countMessages-- > 0) {
            ++sequence;
            long delayInMillis = ((long) (Math.random() * (maxDelayInMillis - minDelayInMillis + 1))) + minDelayInMillis;
            try {
                sleep(delayInMillis);
                setChanged();
                notifyObservers(generateMessage());
                clearChanged();
            } catch (InterruptedException ie) {
                throw new RuntimeException(
                        String.format("Could not delay %d millis between messages for message number %d", delayInMillis,
                                sequence), ie);
            }
        }
    }

    private void sleep(long delayInMillis) throws InterruptedException {
        Thread.sleep(delayInMillis);
    }

    private Message generateMessage() {
        return new Message(String.format("Generated message %d", sequence));
    }
}
