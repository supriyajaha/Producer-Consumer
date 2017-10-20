package consumer;

import data.Message;
import queue.Queue;

public class Consumer3 extends Consumer {

    public Consumer3(Queue messageQueue, String consumerName) {
        super(messageQueue, consumerName);
    }

    public void consume_3(Message message) {
        System.out.println(this.getConsumerName() + " consumed Message -" + message.getData());
    }

}
