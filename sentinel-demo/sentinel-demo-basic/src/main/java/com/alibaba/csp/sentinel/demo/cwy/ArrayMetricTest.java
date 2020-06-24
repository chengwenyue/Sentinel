package com.alibaba.csp.sentinel.demo.cwy;

import com.alibaba.csp.sentinel.node.IntervalProperty;
import com.alibaba.csp.sentinel.node.SampleCountProperty;
import com.alibaba.csp.sentinel.slots.statistic.base.LeapArray;
import com.alibaba.csp.sentinel.slots.statistic.base.WindowWrap;
import com.alibaba.csp.sentinel.slots.statistic.data.MetricBucket;
import com.alibaba.csp.sentinel.slots.statistic.metric.ArrayMetric;
import com.alibaba.csp.sentinel.slots.statistic.metric.Metric;
import com.alibaba.csp.sentinel.slots.statistic.metric.occupy.OccupiableBucketLeapArray;

/**
 * @author cwy-pc
 * @date 2020-04-17
 */
public class ArrayMetricTest {
    public static void main(String[] args) throws InterruptedException {
        Metric arrayMetric = new ArrayMetric(2, 1000);
        arrayMetric.addPass(2);
        Thread.sleep(1050);
        arrayMetric.addPass(2);
        System.out.println(arrayMetric.pass() / arrayMetric.getWindowIntervalInSec());

        arrayMetric.pass();
        LeapArray<MetricBucket> data = new OccupiableBucketLeapArray(2, 1000);
        data.currentWindow();
        final WindowWrap<MetricBucket> windowWrap = data.currentWindow(System.currentTimeMillis()-2000);



    }
}
