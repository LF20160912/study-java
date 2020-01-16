package com.mike.zk.lock;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/*
  Author:Mike
  创建时间：2019/11/13
  描述：
*/
public class DistributedLock implements Lock, Watcher {
    private ZooKeeper zk = null;

    //根节点
    private String ROOT_LOCK = "/locks";
    //竞争的资源
    private String lockName;
    //等待的前一个锁
    private String WAIT_LOCK;
    //当前锁
    private String CURRENT_LOCK;
    //计数器
    private CountDownLatch countDownLatch;
    private int sessionTimeout = 30000;

    /**
     * 配置分布式锁
     *
     * @param config   连接的rul
     * @param lockName 竞争的资源
     */
    public DistributedLock(String config, String lockName) {
        this.lockName = lockName;
        try {
            //连接zk
            zk = new ZooKeeper(config, sessionTimeout, this);
            Stat stat = zk.exists(ROOT_LOCK, false);
            if (stat == null) {
                //如果根节点不存在，则创建根几点
                zk.create(ROOT_LOCK, new byte[0], ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void lock() {
        if (this.tryLock()) {
            System.out.println(Thread.currentThread().getName() + " " + lockName + "获得了锁");
            return;
        } else {
            //等待锁
            try {
                waitForLock(WAIT_LOCK, sessionTimeout);
            } catch (KeeperException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        String splitStr = "_lock_";
        try {
            //创建临时有序节点 /locks/test1_lock_000000021
            CURRENT_LOCK = zk.create(ROOT_LOCK + "/" + lockName + splitStr, new byte[0], ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
            System.out.println(CURRENT_LOCK + " 已经创建");
            // 去所有子节点
            List<String> subNodes = zk.getChildren(ROOT_LOCK, false);
            //取出所有lockName的锁
            List<String> lockObject = new ArrayList<String>();

            for (String node : subNodes) {
                String _node = node.split(splitStr)[0];
                if (_node.equals(lockName)) {
                    lockObject.add(node);
                }
            }
            Collections.sort(lockObject);
            System.out.println("====" + lockObject);
            System.out.println(Thread.currentThread().getName() + " 的锁是 " + CURRENT_LOCK);

            //若当前节点为最小节点，则成功获取锁
            if (CURRENT_LOCK.equals(ROOT_LOCK + "/" + lockObject.get(0))) {
                return true;
            }

            //若不是最小节点，则找到自己的前一个节点
            String prevNode = CURRENT_LOCK.substring(CURRENT_LOCK.lastIndexOf("/") + 1);
            WAIT_LOCK = lockObject.get(Collections.binarySearch(lockObject, prevNode) - 1);
            System.out.println("当前锁"+CURRENT_LOCK+"等待前一个锁"+WAIT_LOCK);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        if (this.tryLock()) {
            return true;
        }
        try {
            return waitForLock(WAIT_LOCK, time);
        } catch (KeeperException e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean waitForLock(String privNode, long waitTime) throws KeeperException, InterruptedException {
        Stat stat = zk.exists(ROOT_LOCK + "/" + privNode, true);

        if (stat != null) {
            System.out.println(Thread.currentThread().getName() + "等待锁" + ROOT_LOCK + "/" + privNode);
            this.countDownLatch = new CountDownLatch(1);

            //计数等待，若等到前一个节点消失，则press中进行countDown，停止等待，获取锁
            this.countDownLatch.await(waitTime, TimeUnit.MILLISECONDS);
            this.countDownLatch = null;
            System.out.println(Thread.currentThread().getName() + " 获得了锁");
        }
        return true;
    }

    @Override
    public void unlock() {
        System.out.println("释放锁" + CURRENT_LOCK);
        try {
            zk.delete(CURRENT_LOCK, -1);
            CURRENT_LOCK = null;
            zk.close();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Condition newCondition() {
        return null;
    }

    /**
     * 节点监控
     *
     * @param watchedEvent
     */
    @Override
    public void process(WatchedEvent watchedEvent) {
        if (this.countDownLatch != null) {
            this.countDownLatch.countDown();
        }

    }
}
