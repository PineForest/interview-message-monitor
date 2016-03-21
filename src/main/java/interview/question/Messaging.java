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
