package com.company.ThreadPools;

import com.company.ThreadPools.executors.IExecutor;
import com.company.ThreadPools.tasks.SimpleTask;

public class Worker implements Runnable {
    IExecutor executor;
    Thread thread;
    public int id;

    public Worker(int id, IExecutor executor) {
        this.id = id;
        this.executor = executor;
        this.thread = new Thread(this);
    }

    public void start(){ this.thread.start();}
    public void interrupt() { this.thread.interrupt();}

    @Override
    public void run() {
        while (!executor.isDead()) {
            try {
                SimpleTask ctask = (SimpleTask) this.executor.getTask();
                System.out.format("Worker %d Processing Task: %d\n", id, ctask.id);
                Thread.sleep(3*1000);
            } catch (Exception e) {
                System.out.format("Exception Worker : %d %s\n", this.id, e);
            }
        }
    }
}
