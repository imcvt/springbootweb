package com.imc.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author luoly
 * @date 2021/3/10 14:56
 * @description
 */
public class BatchHandlerTest {
    //缓存线程池
//    private static ExecutorService fixedThreadPool = new ThreadPoolExecutor(2, 50, 5, TimeUnit.SECONDS, new ArrayBlockingQueue<>(50));
    private static ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 40, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<>(50));


    //主方法测试
    public static void main(String[] args) {
        executor.allowCoreThreadTimeOut(true);
        List<String> list = new ArrayList<>();
        for(int i=0; i<10000000; i++){
            list.add("data-"+i);
        }
        Long mills = System.currentTimeMillis();
        new Thread(() -> {
            new BatchHandlerTest().doHandelListUsingThread(list, 4, 100);
        }).start();
        System.out.println("耗时====>"+(System.currentTimeMillis() - mills));
    }

    //这个方法是真正的处理数据的方法，我写个简单的打印
    private void doHandelList(List list){
        System.out.println(Thread.currentThread().getName()+ "===>" + list.size());
//        try {
//            Random r = new Random();
//            Thread.sleep(r.nextInt(10));
//            Thread.sleep(5000);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    //关键方法，分割数据，然后用多线程同时处理
    public void doHandelListUsingThread(List list, int threadCount, int handelSize){
        if(list.size()<=handelSize || threadCount<=1){
            doHandelList(list);
            return;
        }
        final BlockingQueue<String> queue = new ArrayBlockingQueue<>(threadCount);
        for(int i=0; i<list.size(); i+=handelSize){
            final List subList;
            if(i+handelSize<list.size()){
                subList = list.subList(i, i+handelSize);
            }else{
                subList = list.subList(i, list.size());
            }

            try {
                //**1--关键代码,标记线程数量，超过线程数量会阻塞在此处
                queue.put("");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            executor.execute(() -> {
                doHandelList(subList);
                try {
                    //**2--关键代码。该方法从队列中取出元素**//
                    queue.poll(2, TimeUnit.SECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

    }
}
