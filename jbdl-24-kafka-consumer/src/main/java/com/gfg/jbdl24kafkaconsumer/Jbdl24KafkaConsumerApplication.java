package com.gfg.jbdl24kafkaconsumer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Component;

@SpringBootApplication
@Slf4j
@Component
@ComponentScan("com.gfg")
public class Jbdl24KafkaConsumerApplication {
    private final static String TOPIC ="my-first-topic";


//
//    @KafkaListener(topics =  TOPIC ,groupId = "test")
//    public void consume(String value){
//        log.info("Value consumed consumer 1: {}",value);
//    }
    @KafkaListener(topics = TOPIC,groupId = "test2")
    public void consume2(String value){
        log.info("Value consumed by consumer 2: {}",value);
    }

    @KafkaListener(topics = TOPIC,groupId = "test3" ,
    topicPartitions = @TopicPartition(topic = TOPIC,partitions = {"1"}))
    public void consume3(String value){
        log.info("Value consumed by consumer 3: {}",value);
    }
    public static void main(String[] args) {
        SpringApplication.run(Jbdl24KafkaConsumerApplication.class, args);
    }
}
