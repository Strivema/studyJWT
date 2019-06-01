package com.ray.test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author Ray.Ma
 * @date 2019/6/1 14:56
 */
public class ImplCallable implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        int i =0;
        for (;i<10;i++){
            System.out.println(Thread.currentThread().getName()+""+i);
        }
        return i;
    }

    public static void main(String[] args) {
        ImplCallable ctt = new ImplCallable();
        FutureTask<Integer> ft = new FutureTask<>(ctt);
        for(int i = 0;i < 100;i++)
        {
            System.out.println(Thread.currentThread().getName()+" 的循环变量i的值"+i);
            if(i==2)
            {
                new Thread(ft,"有返回值的线程").start();
            }
        }
        try
        {
            System.out.println("子线程的返回值："+ft.get());
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        } catch (ExecutionException e)
        {
            e.printStackTrace();
        }

    }
}
