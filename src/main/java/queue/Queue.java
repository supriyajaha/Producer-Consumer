package queue;

import consumer.Consumer;
import data.Message;
import exception.ConsumerAlreadySubscribedException;
import exception.InvalidDependentConsumers;

public interface Queue {
    void setQueueCapacity(int size);

    int getRemainingQueueCapacity();

    int getQueueSize();

    void addMessage(Message message, String messageName);

    void subscribe(Consumer consumer, String messageName, String callbackMethod, Consumer... dependentConsumers) throws InvalidDependentConsumers, ConsumerAlreadySubscribedException;
}
