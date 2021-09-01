package com.bo.jvm.heap;

/**
 *
 * 逃逸分析
 *      如何快速的判读是否发生了逃逸分析，就需要看new的对象实体是否有可能在方法外被调用
 *
 * @author gpb
 * @date 2021/8/15 22:12
 */
public class EscapeAnalysisTest {

    /* 如果obj引用声明为static的会不会发生逃逸？ 依然会发生逃逸 */
    public EscapeAnalysisTest obj;

    /**
     * 方法返回EscapeAnalysisTest对象,发生了逃逸分析,不会在栈上分配
     * @return
     */
    public EscapeAnalysisTest getInstance(){
        return obj == null ? new EscapeAnalysisTest() : obj;
    }

    /**
     * 为成员变量赋值,发生了逃逸分析，不会在栈上分配
     */
    public void setObj() {
        this.obj = new EscapeAnalysisTest();
    }

    /**
     * 对象的作用域仅在当前方法内，没有发生逃逸，栈上分配
     */
    public void useEscapeAnalysisTest(){
        EscapeAnalysisTest analysisTest = new EscapeAnalysisTest();
    }

    /**
     * 引用成员变量的只,发生了逃逸
     */
    public void useEscapeAnalysisTest1(){
        EscapeAnalysisTest instance = getInstance();
        // getInstance().xxx(); 依然会发生了逃逸
    }

}
