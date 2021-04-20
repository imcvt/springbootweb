package com.imc.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author luoly
 * @date 2021/4/20 14:53
 * @description comletableFuture用法
 * 可以实现如下等功能
 *
 * 转换（thenCompose）
 * 组合（thenCombine）
 * 消费（thenAccept）
 * 运行（thenRun）。
 * 带返回的消费（thenApply）
 * 消费和运行的区别：
 * 消费使用执行结果。运行则只是运行特定任务。具体其他功能大家可以根据需求自行查看。
 */
public class TestCompletableFu {
    public static void main(String[] args) {
//        thenApply();
        testCombine();
//        testAcceptBoth();
    }

    public static void testCombine() {
            String result = CompletableFuture.supplyAsync(() -> {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return "hello";
            }).thenCombine(CompletableFuture.supplyAsync(() -> {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                int a = 10/0;
                return "world";
            }), (s1, s2) -> s1 + " " + s2)
            .exceptionally(e -> {
                System.out.println(e.getMessage());
                return "异常走这儿";
            }).join();
            System.out.println(result);
    }

    public static void testAcceptBoth() {
        CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello";
        }).thenAcceptBoth(CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "world";
        }), (s1, s2) -> System.out.println(s1 + " " + s2));
        while (true){}
    }


    /**
     * thenapply，串行执行，下一个线程依赖上一个线程的结果
     */
    public static void thenApply() {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        CompletableFuture cf = CompletableFuture.supplyAsync(() -> {
            try {
                  Thread.sleep(3000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("supplyAsync " + Thread.currentThread().getName());
            return "hello";
        }, executorService).thenApplyAsync(s -> {
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(s + "world");
            return "hhh";
        }, executorService);
        cf.thenRunAsync(() -> {
            System.out.println(Thread.currentThread()+"ddddd thenRunAsync");
        });
        cf.thenRun(() -> {
            System.out.println(Thread.currentThread()+"ddddsd thenRun1");
        });
        cf.thenRun(() -> {
            System.out.println(Thread.currentThread()+"dddaewdd thenRun2");
        });
    }

}
