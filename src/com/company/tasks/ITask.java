package com.company.tasks;

import java.util.concurrent.BlockingQueue;

public abstract class ITask implements Comparable<ITask> {
    public int id;
    public abstract void work();
}
