import consumer.Consumer1;
import consumer.Consumer2;
import consumer.Consumer3;
import consumer.Consumer4;
import producer.MessageProducer;
import producer.Producer;
import queue.JsonMessageQueue;
import queue.Queue;

public class TestApplication {
    public static void main(String[] args) throws InterruptedException {
        //1. Create Queue with capacity 2
        Queue queue = new JsonMessageQueue(2);
        //2. Create Producer
        Producer messageProducer = new MessageProducer(queue);
        //3. Create 4 Consumers and name them
        Consumer1 consumer1 = new Consumer1(queue, "consumer_1");
        Consumer2 consumer2 = new Consumer2(queue, "consumer_2");
        Consumer3 consumer3 = new Consumer3(queue, "consumer_3");
        Consumer4 consumer4 = new Consumer4(queue, "consumer_4");

        /*4. Subscribe consumers to messages
        Consumer 4 depends on Consumer1,2,3; Consumer2 depends on Consumer 3; Consumer1 depends on Consumer3
        So order of Consumption should be Consumer3, Consumer1 || Consumer2, Consumer 4(end)*/
        consumer3.subscribe("m1","consume_3");
        consumer2.subscribe("m1","consume_2",consumer3);
        consumer1.subscribe("m1","consume_1",consumer3);
        consumer4.subscribe("m1","consume_4",consumer1,consumer2,consumer3);

        /*4.2
            Consumer 1 depends on Consumer 2 for Message "m2"
            So order should be Consumer2, Consumer1
         */
        consumer2.subscribe("m2", "consume_2");
        consumer1.subscribe("m2", "consume_1", consumer2);

        //4.3 Consumer 2 depends on itself for Message "m3" -- Expect Exception on console
        consumer2.subscribe("m3", "consume_2",consumer2);

        //4.4 Consumer 1 provides wrong callBackMethod for Message "m4" -- Expect Exception on console
        consumer1.subscribe("m4", "consume_2");

        //4.5 Consumer 2 subscribes for message "m4" without any dependencies -- Successfully consumed
        consumer2.subscribe("m4", "consume_2");

        //4.6 Consumer 3 subscribed for message "m4" without its dependent consumer1 subscribing for it - Expect exception and subscribe is failed
        consumer3.subscribe("m4", "consume_3",consumer1);

        //4.7 No one subscribed for message "m5"
        System.out.println();

        //5. Start producer Thread which produces messages - m1, m2, m3,m4 ,m5
        Thread producerThread = new Thread(messageProducer);
        producerThread.start();
        //6. Setting Queue to new Capacity
        Thread.sleep(80);
        queue.setQueueCapacity(4);
        System.out.println("Exiting Main Application");

    }
}

