package com.company.pools;

import com.company.Task;
import com.company.Worker;

import java.util.ArrayList;
import java.util.List;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class FixedThreadPool {
    BlockingQueue taskQueue;
    List<Worker> workers;
    List<Thread> workerThreads;
    AtomicBoolean dead;

    public FixedThreadPool(int poolSize) {
        this.taskQueue = new LinkedBlockingQueue<Task>();
        this.workers = new ArrayList<Worker>();
        this.workerThreads = new ArrayList<Thread>();
        this.dead = new AtomicBoolean(false);

        for (int i = 0; i<poolSize; i++) {
            Worker cw = new Worker(i, taskQueue, this);
            workers.add(cw);
            Thread ct = new Thread(cw);
            workerThreads.add(ct); ct.start();
        }
    }

    public boolean isDead() {return this.dead.get();}

    public void submit (Task newTask) {
        System.out.println("Received " + newTask);
        taskQueue.add(newTask);
    }

    public void shutdown() {
        this.dead.set(true);
        for (Thread wt: workerThreads) wt.interrupt();
    }
}
