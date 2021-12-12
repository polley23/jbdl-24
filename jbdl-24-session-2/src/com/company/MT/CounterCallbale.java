package com.company.MT;
import java.util.Random;
import java.util.concurrent.Callable;

public class CounterCallbale implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        Random random = new Random();
        System.out.println(Thread.currentThread().getName());
        if(random.nextBoolean())
            Thread.sleep(200);
        return random.nextInt(1000);
    }
}
