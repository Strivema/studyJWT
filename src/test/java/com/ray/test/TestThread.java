package com.ray.test;

/**
 * @author Ray.Ma
 * @date 2019/6/1 11:31
 */
public class TestThread implements Runnable {

    private Thread thread;
    private String name;

    public TestThread(String name) {
        this.name = name;
    }

    public Thread getThread() {
        return thread;
    }

    public void setThread(Thread thread) {
        this.thread = thread;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println("Run" + getName());
        try {
            for (int i = 0; i < 10; i++) {
                System.out.println("Thread:" + name + i);
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    private void start(){
        if (thread==null){
            thread = new Thread(this,name);
            thread.start();
        }
    }

    public static void main(String[] args) {
        TestThread t = new TestThread("thread1:");
//        t.setName("thread-1");
        t.start();
        TestThread t2 = new TestThread("thread2:");
        t2.start();
    }
}
