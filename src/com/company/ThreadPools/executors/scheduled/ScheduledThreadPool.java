package com.company.ThreadPools.executors.scheduled;

import com.company.ThreadPools.tasks.ITask;
import com.company.ThreadPools.Worker;
import com.company.ThreadPools.executors.IExecutor;

import java.util.ArrayList;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;
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
    public void submit(ITask newTask) {taskQueue.add(new ITaskNode(newTask, 0l));}

    public void schedule(ITask task, long delay, TimeUnit unit) throws InterruptedException {
        taskQueue.put(new ITaskNode(task, unit.toNanos(delay)));
    }

    @Override
    public void shutdownNow() {
        this.dead.set(true);
        for (Worker wt: workers) wt.interrupt();
    }

    @Override
    public boolean isDead() {return this.dead.get();}

    @Override
    public ITask getTask() throws InterruptedException {
        ITaskNode retTask = (ITaskNode) this.taskQueue.take();
        return retTask.task;
    }
}
