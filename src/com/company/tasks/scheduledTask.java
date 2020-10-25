package com.company.tasks;

import java.time.Instant;

public class scheduledTask extends ITask {

    long scheduledTime;
    public scheduledTask(int id, int timeout, int unit) {
        this.id = id;
        this.scheduledTime = Instant.now().toEpochMilli() + (timeout * unit);
    }

    @Override
    public void work() {
        System.out.format("Task with id : %d working \n", this.id);
    }

    @Override
    public int compareTo(ITask o) {
        return (int)this.scheduledTime;
    }
}
