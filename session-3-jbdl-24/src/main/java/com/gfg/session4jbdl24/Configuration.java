package com.gfg.session4jbdl24;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Bean
    IDependncy dependncy(){
        return new Dependency();
    }
    //Constructor based DI
//    @Bean
//    SampleClass sampleClass(){
//        return new SampleClass(dependncy());
//    }

    //Setter based DI
//    @Bean
//    SampleClass sampleClass2(){
//        SampleClass sampleClass = new SampleClass();
//        sampleClass.setDependncy(dependncy());
//        return sampleClass;
//    }
}
