package com.bo.jvm.gc;

/**
 * 测试 finalize  测试 对象的finalization机制
 * @author gpb
 * @date 2021/8/28 14:48
 */
public class CanReliveObj {

    /**
     * 类变量
     */
    private static CanReliveObj obj;


    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("调用当前类重写的finalize()方法");
        obj = this;
    }

    public static void main(String[] args) {
        try{
            obj = new CanReliveObj();
            // 对象第一次成功拯救自己
            obj = null;
            System.gc();
            System.out.println("第一次 gc");
            Thread.sleep(2000);
            if(obj ==  null){
                System.out.println("obj 已经死亡");
            }else{
                System.out.println("obj 存活中");
            }
            System.out.println("第二次 gc");
            obj = null;
            System.gc();
            Thread.sleep(2000);
            if(obj ==  null){
                System.out.println("obj 已经死亡");
            }else{
                System.out.println("obj 存活中");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    /*  输出
    第一次 gc
    调用当前类重写的finalize()方法
    obj 存活中
    第二次 gc
    obj 已经死亡*/
}
