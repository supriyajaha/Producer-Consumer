package consumer;

import data.Message;
import queue.Queue;

public class Consumer4 extends Consumer {

    public Consumer4(Queue messageQueue, String consumerName) {
        super(messageQueue, consumerName);
    }

    public void consume_4(Message message) {
        System.out.println(this.getConsumerName() + " consumed Message -" + message.getData());
    }

}
