package com.gfg.session4jbdl24;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SampleClass {
    //field based DI
    @Autowired
    private IDependncy dependncy;
    public void calculate() {
        dependncy.calculate();
    }
}
