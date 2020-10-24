package com.company;

import com.company.pools.FixedThreadPool;

import java.util.Random;

public class Main {

    public static void main(String[] args) throws InterruptedException{
	    FixedThreadPool mypool = new FixedThreadPool(3);
	    for (int i = 0; i < 10; i++) {
            Task nt = new Task(i);
            mypool.submit(nt);
            Random rand = new Random();
            int randSleep = rand.nextInt(10);
            Thread.sleep(randSleep*100);
        }
    }
}
