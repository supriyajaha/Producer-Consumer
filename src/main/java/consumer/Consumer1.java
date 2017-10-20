package consumer;

import data.Message;
import queue.Queue;

public class Consumer1 extends Consumer{

    public Consumer1(Queue messageQueue, String consumerName) {
        super(messageQueue, consumerName);
    }

    public void consume_1(Message message) {
        System.out.println(this.getConsumerName()+" consumed Message -" + message.getData());
    }
}
