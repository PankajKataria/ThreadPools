package com.company.pools;

import com.company.Task;
import com.company.Worker;

import java.util.ArrayList;
import java.util.List;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class FixedThreadPool {
    BlockingQueue taskQueue;
    List<Worker> workers;

    public FixedThreadPool(int poolSize) {
        this.taskQueue = new LinkedBlockingQueue<Task>();
        this.workers = new ArrayList<Worker>(poolSize);

        for (int i = 0; i<poolSize; i++) {
            Worker cw = new Worker(i, taskQueue);
            workers.add(cw);
            Thread ct = new Thread(cw); ct.start();
        }
    }

    public void submit (Task newTask) {
        System.out.println("Received " + newTask);
        taskQueue.add(newTask);
    }
}
