package com.example.demo;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class BlockingQueueByLock {
    public LinkedList queue = new LinkedList();
    public int limit;
    public ReentrantLock lock = new ReentrantLock();
    public Condition condition = lock.newCondition();

    public BlockingQueueByLock(int limit) {
        this.limit = limit;
    }

    public LinkedList getQueue() {
        return queue;
    }

    public  void push(Object obj) {
        System.out.println("压入"+obj.toString());
        lock.lock();
        try {
            while (queue.size() == limit) {
                System.out.println("压入时队列已满");
                condition.await();
            }
            if (queue.size() == 0) {
                System.out.println("压入时队列已空");
                condition.signalAll();
            }
            queue.add(obj);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public Object pop(){
        lock.lock();
        try {
            while(queue.size() == 0){
                System.out.println("弹出时队列已空");
                condition.await();
            }
            if (queue.size() == limit){
                System.out.println("弹出时队列已满");
                condition.signalAll();
            }
            return queue.remove();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return  null;
    }

}
