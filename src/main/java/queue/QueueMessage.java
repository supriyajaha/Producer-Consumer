package queue;

import data.Message;

public class QueueMessage {
    private String messageName;
    private int retryCount;
    private Message payload;

    public QueueMessage(String messageName, int retryCount, Message payload) {
        this.messageName = messageName;
        this.retryCount = retryCount;
        this.payload = payload;
    }

    public QueueMessage() {
    }

    public String getMessageName() {
        return messageName;
    }

    public void setMessageName(String messageName) {
        this.messageName = messageName;
    }

    public int getRetryCount() {
        return retryCount;
    }

    public void setRetryCount(int retryCount) {
        this.retryCount = retryCount;
    }

    public Message getPayload() {
        return payload;
    }

    public void setPayload(Message payload) {
        this.payload = payload;
    }

    @Override
    public String toString() {
        return "QueueMessage{" +
                "messageName='" + messageName + '\'' +
                ", retryCount=" + retryCount +
                ", payload=" + payload +
                '}';
    }
}
