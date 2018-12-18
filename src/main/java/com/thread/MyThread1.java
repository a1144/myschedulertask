package com.thread;

import java.util.List;

public class MyThread1 implements Runnable {
  private List<Integer> list;
  public MyThread1(List<Integer> list){
    this.list = list;
  }
  @Override
  public void run() {
    for(Integer i : list){
      /*try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }*/
      System.out.println("thread1-->" + i);
    }
  }
}
