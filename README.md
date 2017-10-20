# Producer-Consumer
Implemented single producer and multiple consumers in multi-threaded environment

# PROBLEM STATEMENT:
Design an efficient in-memory queueing system with low latency requirements
Functional specification:
-   Queue holds JSON messages
- 	Allow subscription of Consumers to messages that match a particular expression
- 	Consumers register callbacks that will be invoked whenever there is a new message
- 	Queue will have one producer and multiple consumers
- 	Consumers might have dependency relationships between them. For ex, if there are three consumers A, B and C. One dependency relationship can be that C cannot consume a particular message before A and B have consumed it.C -> (A,B) (-> means must process after)
- 	Queue is bounded in size and completely held in-memory. Size is configurable.
- 	Handle concurrent writes and reads consistently between producer and consumers.
- 	Provide retry mechanism to handle failures in message processing.


# PRE-REQUISITES:
Java 1.7   
Maven   

# RUNNING APPLICATION:
Run TestApplication.java

# ASSUMPTIONS and INSTRUCTIONS:
**QUEUE:**
- Queue holds JSON messages but payload contains only one type of object - Message.java
- Queue holds JSON string of objects - JsonMessageQueue.java - which contains information such as  

     - Message Name  - Specific Message name for which Consumer has subscribed to
     - Retry Counter - Counter to keep track of how many times retry of message takes place
     - Payload       - Contains Message Object
 
- Queue capacity can be configured at runtime. If new capacity is less than existing queue size(number of elements in the queue),queue is not resized.
- Queue contains Map<MessageName : String,SubscriberInformation: MessageSubscribersDetails>.


# CONSUMERS
Consumers are of Type Consumer.java(Abstract Class). They implement methods -
- Subscribe    - Consumer subscribes for specific MessageName,list of dependent consumers,callBack method name
- ConsumerName - Each consumer is given a consumerName to identity uniquely in logs.


# PRODUCER
Producer is type of Producer.java(Interface). They implement methods -
-Produce   - Produces Message Objects

# OPERATIONS:
1. Subscribe:

   - If dependent consumers are not subscribed, consumer is not subscribed to the message
   - For subscribing, SubscriberMap is filled:

        Key   : MessageName  
        
        Value : MessageSubscribersDetails:               
                Map(Consumer, CallBackMethod) subscribedConsumerInformation  
                Map(Consumer,List(DepdendentConsumers) dependentConsumers  
                Set(Consumers) SubscribedConsumers
2. AddMessage:

    - Convert Message to QueueMessage.

    - Convert QueueMessage to Json String

    - Add Message to Queue

    - Start a consumer thread which would consume this message

3.	Produce    

    - Produces Message Objects with message names - m1,m2,m3,m4,m5,m1

4. Consume 

    - Remove JsonMessage from queue

    - Convert it to QueueMessage

    - If no subscriber present, put the message back to queue by decrementing its retryCounter

    - For each Consumer in subscribedConsumerInformation, start a new thread which would consume the QueueMessage

       Each consume thread then in turn would wait, while its dependent consumers are finished consuming QueueMessage.  
        It does so by checking if dependent consumers are present in subscribedConsumers  
        When a consumer finishes consuming, it removes itself from SubscribedConsumers.   
        So that dependent consumers would will let others know that this consumer has finished consuming
