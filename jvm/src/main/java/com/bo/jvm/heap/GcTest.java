package com.bo.jvm.heap;

import java.util.ArrayList;

/**
 * 测试 MinorGc,majorGc,full Gc
 * 参数
 *        -Xms10m -Xmx10m -XX:+PrintGCDetails
 * @author gpb
 * @date 2021/8/15 19:16
 */
public class GcTest {

    public static void main(String[] args) {
        int i = 0;
        try{
            ArrayList<String> list = new ArrayList<>();
            String a = "oom.com";
            while (true) {
                list.add(a);
                a += a;
                i++;
            }
        }catch (Throwable throwable){
            throwable.printStackTrace();
            System.out.println("遍历的次数为：" + i);
        }
    }
    /*[GC (Allocation Failure) [PSYoungGen: 2040K[新生代回收之前的大小]->496K[新生代回收之后的大小](2560K)[新生代总大小]] 2040K[堆空间的回收之前的大小]->748K[堆空间回收之后的大小](9728K)[堆空间的总体大小], 0.0017368 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
            [GC (Allocation Failure) [PSYoungGen: 2229K->496K(2560K)] 2482K->1647K(9728K), 0.0012297 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
            [GC (Allocation Failure) [PSYoungGen: 2328K->496K(2560K)] 3479K->3003K(9728K), 0.0025653 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
            [Full GC (Ergonomics) [PSYoungGen: 2328K->0K(2560K)] [ParOldGen: 6091K->4176K(7168K)] 8419K->4176K(9728K), [Metaspace: 3068K->3068K(1056768K)], 0.0061719 secs] [Times: user=0.00 sys=0.00, real=0.01 secs]
            [GC (Allocation Failure) --[PSYoungGen: 1853K->1853K(2560K)] 6029K->7821K(9728K), 0.0006685 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
            [Full GC (Ergonomics) [PSYoungGen: 1853K->0K(2560K)] [ParOldGen: 5968K->5968K(7168K)] 7821K->5968K(9728K), [Metaspace: 3068K->3068K(1056768K)], 0.0024588 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
            [GC (Allocation Failure) [PSYoungGen: 0K->0K(1536K)] 5968K->5968K(8704K), 0.0003298 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
            [Full GC (Allocation Failure) [PSYoungGen: 0K->0K(1536K)] [ParOldGen: 5968K->5952K(7168K)] 5968K->5952K(8704K), [Metaspace: 3068K->3068K(1056768K)], 0.0091139 secs] [Times: user=0.02 sys=0.00, real=0.01 secs]
    遍历的次数为：17
    Heap
    PSYoungGen      total 1536K, used 60K [0x00000000ffd00000, 0x0000000100000000, 0x0000000100000000)
    eden space 1024K, 5% used [0x00000000ffd00000,0x00000000ffd0f2c8,0x00000000ffe00000)
    from space 512K, 0% used [0x00000000fff80000,0x00000000fff80000,0x0000000100000000)
    to   space 1024K, 0% used [0x00000000ffe00000,0x00000000ffe00000,0x00000000fff00000)
    ParOldGen       total 7168K, used 5952K [0x00000000ff600000, 0x00000000ffd00000, 0x00000000ffd00000)
    object space 7168K, 83% used [0x00000000ff600000,0x00000000ffbd02f8,0x00000000ffd00000)
    Metaspace       used 3101K, capacity 4556K, committed 4864K, reserved 1056768K
    class space    used 327K, capacity 392K, committed 512K, reserved 1048576K*/

}
