package com.company.MT;
public class OddCounter implements Runnable {
    private Count count;

    public OddCounter(final Count count) {
        this.count = count;
    }


    @Override
    public void run() {
        count.odd();
    }
}
