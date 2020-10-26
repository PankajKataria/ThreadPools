package com.company.ThreadPools.tasks;

public abstract class ITask implements Comparable<ITask> {
    public int id;
    public abstract void work();
}
