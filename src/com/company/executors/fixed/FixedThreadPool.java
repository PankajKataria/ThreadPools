package com.company.executors.fixed;

import com.company.tasks.ITask;
import com.company.Worker;
import com.company.executors.IExecutor;

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

    public void submit (ITask newTask) {
        System.out.println("Received " + newTask);
        taskQueue.add(newTask);
    }

    public ITask getTask() {
        ITask ctask = null;
        try {
            ctask = (ITask) taskQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return ctask;
    }

    public void shutdownNow() {
        this.dead.set(true);
        for (Worker wt: workers) wt.interrupt();
    }
}
