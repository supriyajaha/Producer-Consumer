package consumer;

import data.Message;
import queue.Queue;

public class Consumer2 extends Consumer {

    public Consumer2(Queue messageQueue, String consumerName) {
        super(messageQueue, consumerName);
    }

    public void consume_2(Message message) {
        System.out.println(this.getConsumerName() + " consumed Message -" + message.getData());
    }

}
