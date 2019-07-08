package com.example.demo;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Test {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueueByLock queue = new BlockingQueueByLock(5);

        for(int i=0;i<10;i++){
            final int index = i;
            new Thread(()->
                    queue.push(index+"生产"+new Date())
            ).start();
        }
//        Thread.sleep(1000);
        for(int i=0;i<10;i++){
            final int index = i;
            new Thread(()-> {
                Object obj = queue.pop();
                System.out.println("【弹出】"+obj.toString());
            }).start();
        }

        Thread.sleep(1000);
        System.out.println(queue.getQueue().size());
    }


}