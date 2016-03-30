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

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Observer;

import static org.mockito.AdditionalMatchers.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.powermock.api.mockito.PowerMockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Messaging.class)
public class MessagingTest {
    @Mock
    private Observer observer1;
    @Mock
    private Observer observer2;
    @Mock
    private Observer observer3;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @Test
    public void test_generateMessages_oneObserver_oneMessage_noSleepRange() throws Exception {
        Messaging messaging = spy(new Messaging(1, 100L, 100L));
        doNothing().when(messaging, "sleep", anyLong());
        messaging.addObserver(observer1);

        messaging.generateMessages();

        verifyPrivate(messaging, times(1)).invoke("sleep", eq(100L));
        verify(observer1, times(1)).update(eq(messaging), refEq(new Message("Generated message 1")));
    }

    @Test
    public void test_generateMessages_threeObservers_oneMessage_sleepRange() throws Exception {
        Messaging messaging = spy(new Messaging(1, 100L, 200L));
        doNothing().when(messaging, "sleep", anyLong());
        messaging.addObserver(observer1);
        messaging.addObserver(observer2);
        messaging.addObserver(observer3);

        messaging.generateMessages();

        verifyPrivate(messaging, times(1)).invoke("sleep", and(geq(100L), leq(200L)));
        verify(observer1, times(1)).update(eq(messaging), refEq(new Message("Generated message 1")));
        verify(observer2, times(1)).update(eq(messaging), refEq(new Message("Generated message 1")));
        verify(observer3, times(1)).update(eq(messaging), refEq(new Message("Generated message 1")));
    }

    @Test
    public void test_generateMessages_oneObserver_threeMessages_noSleepRange() throws Exception {
        Messaging messaging = spy(new Messaging(3, 100L, 200L));
        doNothing().when(messaging, "sleep", anyLong());
        messaging.addObserver(observer1);

        messaging.generateMessages();

        verifyPrivate(messaging, times(3)).invoke("sleep", and(geq(100L), leq(200L)));
        verify(observer1, times(1)).update(eq(messaging), refEq(new Message("Generated message 1")));
        verify(observer1, times(1)).update(eq(messaging), refEq(new Message("Generated message 2")));
        verify(observer1, times(1)).update(eq(messaging), refEq(new Message("Generated message 3")));
    }

    @Test
    public void test_generateMessages_threeObservers_threeMessages_sleepRange() throws Exception {
        Messaging messaging = spy(new Messaging(3, 100L, 200L));
        doNothing().when(messaging, "sleep", anyLong());
        messaging.addObserver(observer1);
        messaging.addObserver(observer2);
        messaging.addObserver(observer3);

        messaging.generateMessages();

        verifyPrivate(messaging, times(3)).invoke("sleep", and(geq(100L), leq(200L)));
        for (int i = 0; i < 3; ++i) {
            Observer observer = i == 0 ? observer1 : i == 1 ? observer2 : observer3;
            verify(observer, times(1)).update(eq(messaging), refEq(new Message("Generated message 1")));
            verify(observer, times(1)).update(eq(messaging), refEq(new Message("Generated message 2")));
            verify(observer, times(1)).update(eq(messaging), refEq(new Message("Generated message 3")));
        }
    }
}
