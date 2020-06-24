package com.alibaba.csp.sentinel.demo.cwy;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.context.Context;
import com.alibaba.csp.sentinel.context.ContextUtil;
import com.alibaba.csp.sentinel.node.Node;
import com.alibaba.csp.sentinel.slots.block.BlockException;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author cwy-pc
 * @date 2020-04-17
 */
public class Test {
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(6);
        CompletableFuture[] completableFutures = new CompletableFuture[5];
        Context test = null;
        ContextUtil.enter("Test");
        for (int i = 0; i < 5; i++) {

            CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                ContextUtil.enter("Test");
                try (Entry entry = SphU.entry("entry")){
                    System.out.println("123");
                } catch (BlockException e) {
                    e.printStackTrace();
                }finally {
                    ContextUtil.exit();
                }
            },service);
            completableFutures[i] = future;
        }
        try {
            CompletableFuture.allOf(completableFutures).get();
            final Set<Node> list = ContextUtil.getContext().getEntranceNode().getChildList();

            System.out.println(list.iterator().next().passQps());
        } catch (Exception e) {
            e.printStackTrace();
        }
        service.shutdown();
    }
}
