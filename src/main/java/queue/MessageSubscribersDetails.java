package queue;

import consumer.Consumer;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class MessageSubscribersDetails implements Cloneable{
    //Consumer and its CallBack Method
    private Map<Consumer,String> consumerDetails;
    //Consumer an its dependent consumers
    private Map<Consumer,List<Consumer>> dependentConsumers;
    private Set<Consumer> subscribedConsumers;

    public MessageSubscribersDetails(Map<Consumer, String> consumerDetails, Map<Consumer, List<Consumer>> dependentConsumers, Set<Consumer> subscribedConsumers) {
        this.consumerDetails = consumerDetails;
        this.dependentConsumers = dependentConsumers;
        this.subscribedConsumers = subscribedConsumers;
    }

    public Map<Consumer, String> getConsumerDetails() {
        return consumerDetails;
    }

    public void setConsumerDetails(Map<Consumer, String> consumerDetails) {
        this.consumerDetails = consumerDetails;
    }

    public Map<Consumer, List<Consumer>> getDependentConsumers() {
        return dependentConsumers;
    }

    public void setDependentConsumers(Map<Consumer, List<Consumer>> dependentConsumers) {
        this.dependentConsumers = dependentConsumers;
    }

    public Set<Consumer> getSubscribedConsumers() {
        return subscribedConsumers;
    }

    public void setSubscribedConsumers(Set<Consumer> subscribedConsumers) {
        this.subscribedConsumers = subscribedConsumers;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

