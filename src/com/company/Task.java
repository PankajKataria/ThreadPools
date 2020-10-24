package com.company;

public class Task {
    int id;

    public Task(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Task id : " + String.valueOf(id);
    }
}
