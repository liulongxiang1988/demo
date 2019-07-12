package com.example.demo;

import java.util.LinkedList;

public class BlockingQueue {
    public LinkedList queue = new LinkedList();
    public int limit = 10;

    public BlockingQueue(int limit) {
        this.limit = limit;
    }

    public synchronized void push(Object obj) throws InterruptedException {
        while (queue.size() == limit){
            wait();
        }
        if(queue.size() == 0){
            notifyAll();
        }
        queue.add(obj);
    }

    public synchronized Object pop() throws InterruptedException {
        while(queue.size() == 0){
            wait();
        }
        if (queue.size() == limit){
            notifyAll();
        }
        return queue.remove();
    }

}
