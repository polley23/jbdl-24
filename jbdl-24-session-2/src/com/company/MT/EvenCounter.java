package com.company.MT;
public class EvenCounter implements Runnable {
    private Count count;

    public EvenCounter(final Count count) {
        this.count = count;
    }


    @Override
    public void run() {
        count.even();
    }
}
