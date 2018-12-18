package com.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class ThreadForReadList {

  /**
  * @Description: run方法实际是普通方法，如果调用，会在主线程里执行完，start方法是启动一个线程
                    不是调用run()方法启动线程，run方法中只是定义需要执行的任务，如果调用run方法，即相当于在主线程中执行run方法，
                    跟普通的方法调用没有任何区别，此时并不会创建一个新的线程来执行定义的任务。
  * @param: args 
  * @return: void 
  * @Date: 2018/12/18 
  */ 
  
  public static void main(String[] args) {
    List<Integer> list = new ArrayList<>();
    for (int i = 0; i < 12000; i++) {
      list.add(i);
    }
    MyThread1 myThread1 = new MyThread1(list.subList(0,4000));
    //this.myThread2(list.subList(40,80));
    Thread myThread2 = new Thread(()->{
        /*try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }*/
        for(Integer i : list.subList(4000,8000)){
          System.out.println( "thread2-->" + i);
        }
      });
      //myThread2.start();
    FutureTask<Integer> futureTask = new FutureTask<>((Callable<Integer>)()->{
      Integer i = null;
      for(Integer j : list.subList(8000,12000)){
        //Thread.sleep(1000);
        i = j;
        System.out.println("thread3-->" + i);
      }
      return i;
    });
    //myThread1.run();
    Thread thread1 = new Thread(myThread1);
    thread1.start();
    new Thread(futureTask).start();
    myThread2.start();
    try {
      System.out.println("输出返回值：" + futureTask.get());
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    }

  }

}

