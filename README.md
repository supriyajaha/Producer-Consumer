# Producer-Consumer
Implemented single producer and multiple consumers in multi-threaded environment

# PROBLEM STATEMENT:
Design an efficient in-memory queueing system with low latency requirements
Functional specification:
	1•	Queue holds JSON messages
	2•	Allow subscription of Consumers to messages that match a particular expression
	3•	Consumers register callbacks that will be invoked whenever there is a new message
	4•	Queue will have one producer and multiple consumers
	5•	Consumers might have dependency relationships between them. For ex, if there are three consumers A, B and C. One dependency relationship can be that C     	    cannot consume a particular message before A and B have consumed it. C -> (A,B) (-> means must process after)
	6•	Queue is bounded in size and completely held in-memory. Size is configurable.
	7•	Handle concurrent writes and reads consistently between producer and consumers.
	8•	Provide retry mechanism to handle failures in message processing.

PRE-REQUISITES:
Java 1.7
Maven

RUNNING APPLICATION:
Run TestApplication.java

ASSUMPTIONS and INSTRUCTIONS:
QUEUE:
1. Queue holds JSON messages but payload contains only one type of object - Message.java
2. Queue holds JSON string of objects - JsonMessageQueue.java - which contains information such as
    i. Message Name  - Specific Message name for which Consumer has subscribed to
   ii. Retry Counter - Counter to keep track of how many times retry of message takes place
  iii. Payload       - Contains Message Object
3. Queue capacity can be configured at runtime. If new capacity is less than existing queue size(number of elements in the queue),
   queue is not resized.
4. Queue contains Map<MessageName : String,SubscriberInformation: MessageSubscribersDetails>.

CONSUMERS
Consumers are of Type Consumer.java(Abstract Class). They implement methods -
    i. Subscribe    - Consumer subscribes for specific MessageName,list of dependent consumers,callBack method name
   ii. ConsumerName - Each consumer is given a consumerName to identity uniquely in logs.

PRODUCER
Producer is type of Producer.java(Interface). They implement methods -
    i. produce   - Produces Message Objects

OPERATIONS:
1. Subscribe:
    i. If dependent consumers are not subscribed, consumer is not subscribed to the message
   ii. For subscribing, SubscriberMap is filled:
        Key   : MessageName
        Value : MessageSubscribersDetails - Map<Consumer, CallBackMethod> subscribedConsumerInformation;
                                          - Map<Consumer,List<DepdendentConsumers> dependentConsumers
                                          - Set<Consumers> SubscribedConsumers

2. AddMessage:
    i. Convert Message to QueueMessage.
   ii. Convert QueueMessage to Json String
  iii. Add Message to Queue
   iv. Start a consumer thread which would consume this message

3. Produce
    i. Produces Message Objects with message names - m1,m2,m3,m4,m5,m1

3. Consume:
    i. Remove JsonMessage from queue
   ii. Convert it to QueueMessage
  iii. If no subscriber present, put the message back to queue by decrementing its retryCounter
   iv. For each Consumer in subscribedConsumerInformation, start a new thread which would consume the QueueMessage
       Each consume thread then in turn would wait, while its dependent consumers are finished consuming QueueMessage.
       It does so by checking if dependent consumers are present in subscribedConsumers
       When a consumer finishes consuming, it removes itself from SubscribedConsumers. So that dependent consumers would
            will let others know that this consumer has finished consuming

