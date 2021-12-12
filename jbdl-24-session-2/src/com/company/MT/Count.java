package com.company.MT;
public class Count {
    private Integer max;
    private Integer i;

    public Count(final Integer max, final Integer i) {
        this.max = max;
        this.i = i;
    }

    void odd(){
        synchronized (this) {
            while (i < max) {
                if (i % 2 == 1) {
                    System.out.println(Thread.currentThread().getName() + " " + i);
                    i++;
                    this.notify();
                }else{
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    void even(){
        synchronized (this) {
            while (i < max) {
                if (i % 2 == 0) {
                    System.out.println(Thread.currentThread().getName() + " " + i);
                    i++;
                    this.notify();
                }else{
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
