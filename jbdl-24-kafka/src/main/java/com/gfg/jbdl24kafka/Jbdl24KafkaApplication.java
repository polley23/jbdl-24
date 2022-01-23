package com.gfg.jbdl24kafka;
import org.apache.catalina.core.ApplicationContext;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationContextFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@SpringBootApplication
@Component
@Qualifier("app")
public class Jbdl24KafkaApplication {
    @Autowired
    KafkaTemplate<String,String> kafkaTemplate;
    int count =0;
    public void publish(){
        while(true){
            kafkaTemplate.send("my-first-topic","hello world "+String.valueOf(count));

            count++;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
        Jbdl24KafkaApplication jbdl24KafkaApplication =  SpringApplication.run(Jbdl24KafkaApplication.class, args).getBean(Jbdl24KafkaApplication.class);
        jbdl24KafkaApplication.publish();

    }
}
