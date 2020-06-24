package com.alibaba.csp.sentinel.demo.cwy;

import com.alibaba.csp.sentinel.metric.extension.MetricExtension;
import com.alibaba.csp.sentinel.slots.block.BlockException;

/**
 * @author cwy-pc
 * @date 2020-04-22
 */
public class MyMetricExtension implements MetricExtension {
    @Override
    public void addPass(String resource, int n, Object... args) {

    }

    @Override
    public void addBlock(String resource, int n, String origin, BlockException blockException, Object... args) {

    }

    @Override
    public void addSuccess(String resource, int n, Object... args) {

    }

    @Override
    public void addException(String resource, int n, Throwable throwable) {
        System.out.println(resource + "异常");
        throwable.printStackTrace();
    }

    @Override
    public void addRt(String resource, long rt, Object... args) {

    }

    @Override
    public void increaseThreadNum(String resource, Object... args) {

    }

    @Override
    public void decreaseThreadNum(String resource, Object... args) {

    }
}
