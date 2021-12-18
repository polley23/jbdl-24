package com.gfg.session4jbdl24;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Dependency2 implements IDependncy{
    @Override
    public void calculate() {
        Integer calulate = 10*10;
        log.info("Calculated value {}",calulate );
    }
}
