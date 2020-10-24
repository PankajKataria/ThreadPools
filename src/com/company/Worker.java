package com.company;

import java.util.concurrent.BlockingQueue;

public class Worker implements Runnable {
    BlockingQueue mainPool;
    int id;
    public Worker(int id, BlockingQueue mainPool) {
        this.mainPool = mainPool;
        this.id = id;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Task ctask = (Task) this.mainPool.take();
                System.out.format("Worker %d Processing Task: %d\n", id, ctask.id);
                Thread.sleep(3*1000);
            } catch (Exception e) {
                System.out.format("Exception Worker : %d %s\n", this.id, e);
            }
        }
    }
}
