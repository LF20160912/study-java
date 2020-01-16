package com.mike.zk.lock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/*
  Author:Mike
  创建时间：2019/11/13
  描述：
*/
public class AppTest {
    static int n = 10;
    private static CountDownLatch latch = new CountDownLatch(10);


    public static void secondSkill() {
        System.out.println(--n);
    }

    public static void main(String[] args) throws InterruptedException {
        TimeUnit.SECONDS.sleep(10);

        for (int i = 0; i < 10; i++) {
            new Thread() {
                @Override
                public void run() {
                    DistributedLock lock = null;
                    try {
                        latch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    try {
                        TimeUnit.MILLISECONDS.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    try {
                        lock = new DistributedLock("192.168.3.100:2181,192.168.3.102:2181,192.168.3.103:2181", "test1");
                        lock.lock();
                        secondSkill();
                    } finally {
                        if (lock != null) {
                            lock.unlock();
                        }
                    }
                }
            }.start();//每循环1次，就启动一个线程，具有一定随机性

            latch.countDown();
        }
    }
}
