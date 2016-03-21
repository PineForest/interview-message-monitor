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
