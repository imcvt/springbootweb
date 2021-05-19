package com.imc.test;

import com.sun.xml.internal.ws.util.CompletedFuture;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

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
//        testCombine();
//        testAcceptBoth();
        testParell();
    }


    private static void testParell() {
        CompletableFuture<String> priceOfTM = CompletableFuture.supplyAsync(() -> {
            System.out.println("1");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return "1";
        });
        CompletableFuture<String> priceOfJD = CompletableFuture.supplyAsync(() -> {
            System.out.println("2");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "1";
        });
        CompletableFuture<String> priceOfTB = CompletableFuture.supplyAsync(() -> {
            System.out.println("3");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "1";
        });
        CompletableFuture.allOf(priceOfJD,priceOfTB,priceOfTM).join();
        System.out.println("执行完毕");
    }

    public static void testCombine() {
        //循环调用
        CompletableFuture<List<String>> groupDtos = CompletableFuture.supplyAsync(() -> Arrays.asList("1"))
                .thenCombine(CompletableFuture.supplyAsync(() -> Arrays.asList("2")), (l1, l2) -> {
                    l1.addAll(l2);
                    System.out.println("11"+l2);
                    return l1;
                })
                .thenCombine(CompletableFuture.supplyAsync(() -> Arrays.asList("3")), (l1, l2) -> {l1.addAll(l2);return l1;})
                .thenCombine(CompletableFuture.supplyAsync(() -> Arrays.asList("4")), (l1, l2) -> {l1.addAll(l2);return l1;})
                .thenCombine(CompletableFuture.supplyAsync(() -> Arrays.asList("5")), (l1, l2) -> {l1.addAll(l2);return l1;})
                .thenCombine(CompletableFuture.supplyAsync(() -> Arrays.asList("6")), (l1, l2) -> {l1.addAll(l2);return l1;})
                .exceptionally(e -> Collections.emptyList());
        try {
            groupDtos.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        String s1 = CompletableFuture.supplyAsync(() -> "1")
                .thenCombine(CompletableFuture.supplyAsync(() -> "2"), (l1, l2) -> {
                    return l1+l2;
                })
                .thenCombine(CompletableFuture.supplyAsync(() -> "3"), (l1, l2) -> {return l1+l2;})
//                .thenCombine(CompletableFuture.supplyAsync(() -> Arrays.asList("4")), (l1, l2) -> {l1.addAll(l2);return l1;})
//                .thenCombine(CompletableFuture.supplyAsync(() -> Arrays.asList("5")), (l1, l2) -> {l1.addAll(l2);return l1;})
//                .thenCombine(CompletableFuture.supplyAsync(() -> Arrays.asList("6")), (l1, l2) -> {l1.addAll(l2);return l1;})
                .exceptionally(e -> "").join();
            System.out.println(groupDtos);
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
