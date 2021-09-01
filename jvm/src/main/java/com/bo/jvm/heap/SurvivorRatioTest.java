package com.bo.jvm.heap;

/**
 * 参数：-Xms600m -Xmx600m -XX:SurvivoRatio=8 -XX:+PrintGCDetails
 *
 * 你会发现，新生代的比例不是 8:1:1，是因为有一个自适应的机制，默认是有的
 * 如果你想关掉他，那么你需要添加该参数
 * -XX:-UserAdaptiveSizePolicy  关闭自适应的内存分配策略
 *
 * 你会发现用该参数不顶用，要是8：1；1 那么你需要 通过 -XX:SurvivoRatio=8 该参数指定
 * -XX:SurvivoRatio=8  是 Eden 与 Survivor 的比例
 *
 * @author gpb
 * @date 2021/8/15 14:38
 */
public class SurvivorRatioTest {

    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(100000000);
    }

}
