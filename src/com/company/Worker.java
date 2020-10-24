package com.company;

import com.company.pools.FixedThreadPool;

import java.util.concurrent.BlockingQueue;

public class Worker implements Runnable {
    BlockingQueue mainPool;
    FixedThreadPool executor;
    int id;

    public Worker(int id, BlockingQueue mainPool, FixedThreadPool executor) {
        this.mainPool = mainPool;
        this.id = id;
        this.executor = executor;
    }

    @Override
    public void run() {
        while (!executor.isDead()) {
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
