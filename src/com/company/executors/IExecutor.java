package com.company.executors;
import com.company.Worker;
import com.company.tasks.ITask;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class IExecutor {
    public List<Worker> workers;
    public AtomicBoolean dead;
    public BlockingQueue taskQueue;

    public abstract void submit(ITask newTask);
    public abstract void shutdownNow();
    public abstract boolean isDead();
    public abstract ITask getTask();
}
