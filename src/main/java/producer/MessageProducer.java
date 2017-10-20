package producer;

import data.Message;
import queue.*;

public class MessageProducer implements Producer {
    private Queue queue;

    public MessageProducer(Queue queue) {
        this.queue = queue;
    }

    public void produce() {
        for (int index = 1; index <= 5; index++) {
            String messageName = "m"+index;
            Message message = new Message("m"+index);
            queue.addMessage(message,messageName);
            try {
                Thread.sleep(index*10);
            } catch (InterruptedException e) {
                System.err.println("InterruptedException occurred during making producer thread sleep, suppressing it");
            }
        }
        //Adding Message m1 again to see if Order of consumption is Consumer3,Consumer2/consumer1,consumer4
        //and subscription list shows correct value in multithreaded environment
        Message message = new Message("m1");
        queue.addMessage(message,"m1");
    }

    @Override
    public void run() {
        produce();
    }
}
