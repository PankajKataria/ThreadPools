package com.company.ThreadPools.executors;
import com.company.ThreadPools.Worker;
import com.company.ThreadPools.tasks.ITask;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class IExecutor {
    public List<Worker> workers;
    public AtomicBoolean dead;
    public BlockingQueue taskQueue;

    public abstract void submit(ITask newTask) throws InterruptedException;
    public abstract void shutdownNow();
    public abstract boolean isDead();
    public abstract ITask getTask() throws InterruptedException;
}
