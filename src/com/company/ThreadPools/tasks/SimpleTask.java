package com.company.ThreadPools.tasks;

public class SimpleTask extends ITask {

    public SimpleTask(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Task id : " + String.valueOf(id);
    }

    @Override
    public void work() {
        System.out.format("Task %d Working .. \n", this.id);
    }

    @Override
    public int compareTo(ITask o) {
        return (this.id - o.id);
    }
}
