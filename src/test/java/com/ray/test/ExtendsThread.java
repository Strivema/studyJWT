package com.ray.test;

/**
 * @author Ray.Ma
 * @date 2019/6/1 14:29
 */
public class ExtendsThread extends Thread {
    private Thread thread;
    private String threadName;

    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    public void run(){
        System.out.println("Running " +  threadName );
        try {
            for(int i = 10; i > 0; i--) {
                System.out.println("Thread: " + threadName + ", " + i);
                Thread.sleep(1000);
            }
        }catch (InterruptedException e) {
            System.out.println("Thread " +  threadName + " interrupted.");
        }
        System.out.println("Thread " +  threadName + " exiting.");
    }
    public void start () {
        System.out.println("Starting " +  threadName );
        if (thread == null) {
            thread = new Thread (this, threadName);
            thread.start ();
        }
    }

    public static void main(String[] args) {
        ExtendsThread t = new ExtendsThread();
        t.setThreadName("thread");
//        t.start();
        t.run();
        System.out.println(t.isDaemon());
    }
}
