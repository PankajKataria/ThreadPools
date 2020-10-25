package com.company.executors.scheduled;

import com.company.tasks.ITask;
import com.company.Worker;
import com.company.executors.IExecutor;

import java.util.ArrayList;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class ScheduledThreadPool extends IExecutor {

    public class ITaskNode implements Comparable<ITaskNode> {
        ITask task;
        long timer;

        public ITaskNode(ITask task, long timer) {
            this.task = task;
            this.timer = timer;
        }

        @Override
        public int compareTo(ITaskNode o) {
            long diff = this.timer - o.timer;
            if (diff < 0) return -1;
            else if (diff == 0) return 0;
            return 1;
        }
    }

    public ScheduledThreadPool(int poolSize) {
        this.taskQueue = new PriorityBlockingQueue<ITaskNode>();
        this.workers = new ArrayList<Worker>();
        this.dead = new AtomicBoolean(false);

        for (int i = 0; i < poolSize; i++) {
            Worker cw = new Worker(i, this);
            workers.add(cw); cw.start();
        }
    }

    @Override
    public void submit(ITask newTask) {
        taskQueue.add(new ITaskNode(newTask, 0l));
    }

    public void schedule(ITask task, long delay, int timeunit) {}

    @Override
    public void shutdownNow() {}

    @Override
    public boolean isDead() {return this.dead.get();}

    @Override
    public ITask getTask() {
        ITask returnTask = null;
        try {
            ITaskNode retTask = (ITaskNode) this.taskQueue.take();
            returnTask = retTask.task;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return returnTask;
    }
}
