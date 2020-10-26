package com.company.ThreadPools.executors.fixed;

import com.company.ThreadPools.tasks.ITask;
import com.company.ThreadPools.Worker;
import com.company.ThreadPools.executors.IExecutor;

import java.util.ArrayList;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class FixedThreadPool extends IExecutor {

    public FixedThreadPool(int poolSize) {
        this.taskQueue = new LinkedBlockingQueue<ITask>();
        this.workers = new ArrayList<Worker>();
        this.dead = new AtomicBoolean(false);

        for (int i = 0; i<poolSize; i++) {
            Worker cw = new Worker(i, this);
            workers.add(cw); cw.start();
        }
    }

    public boolean isDead() {return this.dead.get();}

    public void submit (ITask newTask) throws InterruptedException {
        System.out.println("Received " + newTask);
        taskQueue.put(newTask);
    }

    public ITask getTask() throws InterruptedException {
        return (ITask) taskQueue.take();
    }

    public void shutdownNow() {
        this.dead.set(true);
        for (Worker wt: workers) wt.interrupt();
    }
}
