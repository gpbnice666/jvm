# JVM

## 1.jvm的架构

### 	1.1Java虚拟机原理

所谓虚拟机，就是一台虚拟的机器。它是一款软件，用来执行一系列虚拟计算机指令，大体上虚拟机可以分为系统虚拟机和程序虚拟机，大名鼎鼎的Visual Box、VMare就属于系统虚拟机，他们完全是对物理计算机的仿真，提供了一个可运行完整操作系统的软件平台。程序虚拟机典型代表就是Java虚拟机，它专门为执行单个计算机程序而设计，在java虚拟机中执行的指令我们称为java字节码指令。无论是系统虚拟机还是程序虚拟机，在上面运行的软件都被限制于虚拟机提供的资源中。

Java发展至今，出现过很多虚拟机，最初Sun使用的一款叫Classic的Java虚拟机，到现在引用广泛的是HotSpot虚拟机，除了Sun之外，还有BEA的JRockit，U前JRockit和HotSpot都被Oracle收入旗大有整合的趋势

### 1.2两种虚拟机架构的区别？

​    Java编译器输入的指令流基本是基于： **<font color=red>栈的指令及架构，寄存器的指令集架构</font>**

#### 	1.2.1基于栈式架构的特点

1. ​    设计和实现更简单,适用于资源受限的系统
2. ​     避开了寄存器的分配的难题,使用零地址指令方式分配
3. ​	指令流中的大部分是零地址指令,其执行过程依赖与操作栈,指令集更小,编译器容易实现	
4. ​    不需要硬件的支持,可移植性更好,更好实现跨平台

####    1.2.2基于寄存器架构特点

1. ​            典型的应用是X86的二进制指令集,比如传统pc以及andoroid的Dalik虚拟机
2. ​            指令集架构则完全依赖硬件,可以执行差		
3. ​            性能优秀和执行更高效
4. ​	        花费更少的指令去完成一项操作
5. ​			在大部分情况下,基于寄存器架构的指令集往往都以一地址指令,二地址指令和三地址指令为主
   ​			而基于栈式架构的指令集确实一零地址指令为主

### 1.3JVM的模型

​         由于跨平台性的设计,java的指令都是根据栈来设计的,不同平台cpu架构不同,
​		所以不能设计为基于寄存器的。优点是跨平台,指令集小,编译器容易实现,缺点是性能下降
​		实现同样的功能需要更多的指令

优点：<font color=red>**跨平台,指令集小,指令多,执行性能比寄存器差**</font>

### 1.4JVM的生命周期

​         java 虚拟机的启动时通过引导类加载器(bootstrap class loader)创建一个初始化类(initial class)
​		来完成,这个类是由虚拟机的具体实现指定的

####   1.4.1虚拟机的执行

​           一个运行中的Java虚拟机有着一个清晰的任务：执行Java程序
​		           程序开始执行时他才运行,程序结束是他就停止
​		          执行以所谓的Java程序的时候,真真正正在执行的是一个叫做Java虚拟机的进程

### 1.5jvm的位置

![](.\png\jvm的位置.jpg)

**JVM是运行在操作系统之上的,他与硬件没有直接交互**

### 1.6java程序执行的过程

![](.\png\Java程序执行过程.jpg)

### 1.7Java程序执行详细过程

![](.\png\jvm架构图.png)

## 2.类的加载篇

### 2.1类的加载过程如图

![](.\png\类加载器子系统.png)

#### 2.1.1加载（loading）

通过一个类的全限定名获取关于此类的二进制字节流

将这个字节流所代表的静态存储结构转换为方法区的运行时数据结构

在内存中生成一个代表这个类的Java.lang.Class对象,作为方法去这个类的跟中数据访问入口

#### 2.1.2验证(Verify)

1.目的在于确保Class文件的字节流中包含信息符合当前虚拟机的要求,保证被加载类的正确性,不会危害虚拟机自身安全

2.主要包含四种验证：文件格式验证,元数据验证,字节码验证,符号引用验证

#### 2.1.3准备(Prepare)

​        1.为类变量分配内存并且设置该类变量的默认初始值,即零值
​				2.这里不包含用 final修饰的static 因为final在编译的时候会分配了,准备阶段会显示初始化
​				3.这里不会为实例变量分配初始化,类变量会在分配方法区中,而实例变量时随着对象一起分配到堆内存中

#### 2.1.4解析(resolve)

​        1.将常量池内的符号引用转换为直接直接引用的过程
​				2.事实上,解析操作往往会伴随着jvm在执行完初始化之后在执行
​				3.符号引用就是 一组符号来描述所引用的目标,符号引用的字面量形式明确定义在《Java虚拟机规范》
​				   的Class文件格式中。直接引用就是直接指向目标的指针,相对偏移量或一个间接定位到目标的句柄
​				4.解析动作主要针对类或接口,字段,类方法,接口方法,方法类型等 对应常量池中的
​				CONSTANT_Class_info CONSTANT_Fieldref_info,CONSTANT_Methodref

#### 2.1.5初始化(init)

​        1.初始化阶段就是执行类构造方法(clinit)()过程
​				2.此方法不需要定义,是Java编译器自动收集类中的所用类变量的复制动作和静态代码块中语句合并而来
​				3.构造器方法中指令按语句在源文件中出现的顺序执行
​				4.<clinit>()不同于类的构造器. (关联构造器是虚拟机视角下的<init>())
​				5.若该类具有父类,JVM会保证子类的<clinit>执行前,父类的<clinit>已经执行完毕
​				6.虚拟机必须保证一个类的<clinit>方法在多线程下被同步加锁
​				7.clinit 该方式是为静态初始化,静态代码块

### 2.2类的加载器分类

JVM支持两种类型的类加载器,分别为 **引导类加载器(Bootastrap ClassLoader)**,**自定义类加载器(User-Defined ClassLoader)**
	   从概念上来讲,自定义类加载器一般值的是程序中有开发人来自定义的类加载器,但是Java虚拟机
规范却没有这么定义,将所用派生于抽象类ClassLoader的类加载器都划分为自定义加载器



```java
		public static void main(String[] args) {
        // 获取系统类加载器 sun.misc.Launcher$AppClassLoader@18b4aac2
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println(systemClassLoader);

        // 获取扩展加载器 sun.misc.Launcher$ExtClassLoader@39a054a5
        ClassLoader extClassLoader = systemClassLoader.getParent();
        System.out.println(extClassLoader);

        // 获取引导类加载器 null
        ClassLoader bootstrapClassLoader = extClassLoader.getParent();
        System.out.println(bootstrapClassLoader);

        System.out.println("**********************启动类加载器**********************");
        // 获取启动类加载器能加在Java文件的路径
        URLClassPath bootstrapClassPath = Launcher.getBootstrapClassPath();
        URL[] urLs = bootstrapClassPath.getURLs();
        for (URL urL : urLs) {
            System.out.println(urL.toExternalForm());
        }
        System.out.println("**********************扩展类加载器**********************");
        // 扩展类加载器加载Java文件的路径
        String property = System.getProperty("java.ext.dirs");
        System.out.println(property);

        System.out.println("**********************系统加载器**********************");
        // 我们自己编写的Java文件是由系统加载器来进行加载的
        System.out.println(CLassLoaderTest.class.getClassLoader());
    }
```
#### 2.2.1引导类加载器/启动类加载器(Bootastrap ClassLoader)

这个类加载器使用了C/C++语言来实现的,嵌套在JVM内部
	    他用来加载Java的核心类库(JAVA_HOME/jre/lib/rt.jar) resources.jar or sun.boot.class.path路径下的内容,用于提供JVM自身需要的类
	    并不继承自Java.lang.ClassLoader 没有父加载器
		扩展加载器和系统加载器 并指定为他们父类加载器
		处于安全考虑 bootstrap启动类加载器只加载包名java,javax,sun等开头的类

#### 2.2.2扩展类加载器

​	Java语言编写 由sun.misc.launcher$ExtClassLoader实现

​    派生于ClassLoader类

   //父类加载器是引导类加载器

   加载以  java.ext.dirs系统属性所指定的目录中类库,或从JDK的安装路径的 jre/lib/ext子目录 加载类库
	   如果用户创建JAR放在此目录下,也会自动由扩展类加载器加载

#### 2.2.3系统类加载器

   派生于ClassLoader类

 //  父类加载器是引导类加载器

  我们自己编写的Java文件是由系统类加载器加载的

#### 2.2.4用户自定义加载器

在Java的日常应用程序开发中,类的加载器几乎是以上三种加载器互相配合执行的,在必要的时候,我们也可以自定义类加载器

##### 2.2.4.1why

​			隔离加载类
​					修改类加载的方式
​					扩展加载源
​					防止源码泄露

##### 2.2.4.2how

​           开发人员可以通过继承抽象类java.lang.ClassLoader类方式,实现自己的类加载器以满足特殊需求
​					在JDK1.2之前,在自定义类加载器是,总会去继承ClassLoader类并重写loadCLass()方法,从而实现
​					自定义类加载器,但是在JDK1.2之后已不再建议用户覆盖ClassLoader()方法,而是建立吧自定义的类
​					加载逻辑写在findClass()方法中
​					在编写自定义类加载器时,如果没有太过于复杂的需求,可以直接继承URLClassLoader类,这样就可以
​					避免自己去编写findClass()方法及其获取字节码流的方式,是自定义类加载器编写更加简洁

```java
/**
 * @author gpb
 * @date 2021/7/21 21:43
 */
public class CustomClassLoader extends ClassLoader{

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            byte[] result = getClassFromCustomPath(name);
            if(result == null){
                throw new FileNotFoundException();
            }else{
                return defineClass(name,result,0,result.length);
            }
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
        return super.findClass(name);
    }

    private byte[] getClassFromCustomPath(String name) {
        // 从自定义路径中加载指定类 。。。
        // 如果指定路径字节码文件进行加密,而需要在此地方进行解密
        return null;
    }

    public static void main(String[] args) {
        CustomClassLoader userClassLoader = new CustomClassLoader();
        try {
            Class<?> clazz = Class.forName("One", true, userClassLoader);

            Object o = clazz.newInstance();

            System.out.println(o.getClass().getClassLoader());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
```

### 2.3双亲委派机制

![](png\双亲委派机制.jpg)

Java虚拟机对class文件采用的是按需加载的方式,也就是说当需要使用该类时才会将它class文件加载到内存中,生成class对象
			而且加载某个类的class文件时,Java虚拟机采用的是双亲委派机制,即把请求交由父级处理,他是一种任务委派模式

#### 2.3.1工作原理

​         如果一个类加载器收到类加载请求,他并不会自己先去加载,二十把这个请求委托到父类的加载器区执行;
​				如果父类还存在父类加载器,则进一步向上委托,依次递归,请求最终将到达顶层的启动类加载器
​				如果父类加载器可以完成类加载任务,就成功返回,倘若父类加载器无法完成加载任务,子加载器才会
​				尝试自己去加载,这就是双清委派机制

自定义类加载器  ------》系统加载器 -------》扩展加载器 -------》引导类加载器
				小                                                                                                     大 

**优点**
				避免类的重复加载
				保护程序安全,防止核心API被随意改动

### 2.4沙箱安全机制

​        比如我定义了一个类名为String所在包为java.lang，
​				因为这个类本来是属于jdk的，如果没有沙箱安全机制的话，
​				这个类将会污染到我所有的String,但是由于沙箱安全机制，
​				所以就委托顶层的bootstrap加载器查找这个类，
​				如果没有的话就委托extsion,extsion没有就到aapclassloader，
​				但是由于String就是jdk的源代码，所以在bootstrap那里就加载到了，先找到先使用，
​				所以就使用bootstrap里面的String,后面的一概不能使用，这就保证了不被恶意代码污染

#### 2.4.1什么是沙箱

​       Java安全模型的核心就是Java沙箱,沙箱是限制一个程序运行的环境,沙箱机制就是
​				将Java代码限定在虚拟机特定的运行范围中,并且严格限制代码对本地系统资源访问
​				通过这样的措施来保证对代码的有效隔离,防止对本地系统造成破坏.沙箱主要限制系统资源访问
​				系统资源访问包括啥？ cup 内存 文件系统 网络 不同的级别的沙箱对这些的限制也是不一样的

### 2.5jvm中两个class对象是否同一个类存在 两个必备条件

类的完成类名必须一直,包括包名
		加载这类的ClassLoader(指ClassLoader的实例对象)必须相同

### 2.6类的加载过程详细图

​     		![](.\png\类加载过程.png)		

## 3.运行时数据区

### 	3.1运行数据图

​	![](.\png\运行时数据区.png)

#### 3.1.1方法区(Method Area)

**就是存放类信息、常量信息、常量池信息，包括字符串字面量和数字常量****

#### 3.1.2堆(Heap Area)

**在java虚拟机启动的时候建立java堆，它是java程序最主要的内存工作区域，几乎所有的对象实例都存放在java堆中，堆空间是所有线程共享的****

#### 3.1.3栈(Stack Area)

**每个虚拟机线程都有一个私有的栈，一个线程的java栈在线程创建的时候被创建，每一个栈帧就是一个方法 每一个栈帧包含：局部变量表,操作数栈,动态链接,方法返回值,附加信息**

#### 3.1.4PC寄存器(PC Registers)

**PC(Program Counter)寄存器也是每个线程私有的空间，Java虚拟机会为每个线程创建PC寄存器，在任意时刻，一个java线程总是执行一个方法，这个方法被称为当前方法，如果当前方法不是本地方法，PC寄存器就会执行当前正在被执行的指令，如果是本地方法，则PC寄存器值为undefined，寄存器存放如当前执行环境指针、程序计数器、操作栈指针、计算的变量指针等信息**

#### 3.1.5本地方法栈(Native Method Stack)

**本地方法栈和java栈非常类似，最大不同为本地方法栈用于本地方法调用，java虚拟机允许java直接调用本地方法(通常使用C编写)**

### 3.2PC寄存器(PC Registers)

![](.\png\pc寄存器.png)

#### 3.2.1PC Register 程序计数器

JVM中程序计数寄存器(Program Counter Register)中,Register的命名源于CPU的寄存器,

寄存器存储指令相关的现场信息。CPU只有把数据装载到寄存器才能过运行

这里,并非是广义上所致的物理寄存器,或许将其翻译为PC计数器(或指令计数器)会更加贴切(程序钩子)
	并且也不容易引起一些不必要的误会。JVM中的PC寄存器是对应物理CPU寄存器的一种抽象模拟

#### 3.2.2介绍

   它是一块很小的内存空间,几乎可以忽略不计,也是运行速度最快的存储区域;
			在JVM规范中,每个线程都有自己的程序计数器,是每个线程私有的,生命周期与线程的生命周期保持一直;
			任何时间一个线程都只有一个方法执行,也就是所谓的当前方法.程序计数器会存储当前线程正在执行的JAVA方法的JVM指令地址 或者
			如果是正在执行native方法,则是未指定值(undened);
			他是程序控制流的指示器,分支,循环,跳转,异常处理,线程恢复等基础功能都需要以来这个计数器来完成;
			字节码解释器工作时就是通过改变这个计数器的值来选取下一条需要执行的字节码指令;
			他是唯一一个在JAVA 虚拟机规范中规定任何OutOtMemoryError情况的区域;

#### 3.2.3作用

​	PC寄存器用来储存指向下一条指令的地址,也即将要执行的指令代码,由执行引擎读取下一条指令

#### 3.2.3使用PC寄存器存储字节码指令地址有什么好处？ / 为什么使用PC寄存器记录当前线程的执行地址呢？

​    因为CPU需要不停的切换各个线程,这时候切换回来以后,就知道该从哪里开始继续执行;
​			JVM的字节码解释器就需要通过改变PC寄存器的值来明确下一条应该执行什么样的字节码指令;

#### 3.2.4PC寄存器为什么会被设定为线程私有？

我们都知道所谓的多线程在一个特定的时间内只会执行其中某一个线程的方法,CPU会不停的的切换任务
				这样必然导致经常中断或恢复,如何保证分毫不差呢？为了能够准确的记录各个线程正在执行的当前字节码指令地址,
				最后的办法就是自然就是给每个一线程都分配一个PC寄存器,这样一来各个线程之间便可以进行独立计算,从而不回出现相互干扰的情况
				由于CPU时间片轮限制众多线程在并发执行的过程中,任何一个确定的时刻,一个处理器或者多核处理器中的一个内核,只会执行某一个线程中的一条指令

 这样必然会导致经常中断如何保证分毫无错呢？每个线程创建后,都会产生自己的程序计数器和栈帧,程序计数器在各个线程之间互不影响;

#### 3.2.5CPU时间片

  CPU 时间片 即 CPU分配各个程序的时间,每个线程被分配一个时间段,称作为 时间片
			宏观：我们可以同时打开多个应用程序,每个程序并行不波,同时运行
			围观：由于只有一个CPU 一次只能处理程序要求一部分,如果处理公平,一种方法就是引入时间片,每个程序轮流执行

##### 3.2.6字节码图

![](.\png\pc.png)

### 3.3栈

![](.\png\栈.png)

**由于跨平台性的设计,Java的指令都是根据栈设计的,不同平台CPU架构不同,所以不能设计为基于寄存器的**

#### 3.3.1优点

​	**跨平台,指令集小,编译器容易实现,缺点性能下降,实现同样的功能需要更多的指令**

  **栈是运行时的单位,堆是存储的单位**

#### 3.3.2Java栈是什么？

java虚拟机,早期也叫Java栈每个线程在创建是都会创建一个虚拟机栈,其内部保存一个个的栈帧(Stack Frame)
		对应这一次次的Java方法调用
		是线程私有的

生命周期与线程的生命周期保持一致

**帧数据区：方法返回地址，动态链接，一些附件信息**

#### 3.3.3作用

主管Java程序的运行,他保存方法的局部变量,部分结果,并参与方法调用和放回
					局部变量 vs 成员变量
					基本数据变量 引用类型变量(类 接口 数组)

##### 3.3.3.1优点

栈是一种有效的分配存储的方式,访问速度仅次于程序计数器;
				JVM直接对Java栈的操作只有两个
					每个方法执行,伴随着进栈出栈(入栈,压栈)
					执行结束后出栈的操作
				    对于栈来说不存在垃圾回收问题

#### 3.3.4栈中可能出现的异常？

Java栈的大小是动态的或这是固定不变的
		如果栈的内存大小采用的是固定的,如果线程请求的分配的栈的容量超过Java虚拟机栈的允许最大容量,Java虚拟机会抛出StackOverflow Error;
		如果Java虚拟机恶意动态扩展,并且在城市扩展的时候无法申请到足够的内存，或者在创建性的线程是没有足够的内存去创建对应的虚拟机栈,那么Java虚拟机会抛出OutOfMemoryError

#### 3.3.5栈中存储什么？

​      每个线程都有自己的栈,栈中的数据都是以栈帧(Stack Fram)的格式存在
​			 在这个线程上正在执行的每个方法都有各自对应一个栈帧
​			 栈帧是每一内存区块,是一个数据集,维系这方法执行过程中的各种数据信息

OOP的基本概念
					类、对象
					类中的结构：field(属性,字段,域) method 方法

#### 3.3.6栈的运行原理

![](.\png\栈的运行.jpg)

JVM直接对Java栈的操作只有两个 就是出栈和压栈 遵循 **先进后出 后进先出 的原则**
		在一条活动线程中,一个时间点,只会有一个活动的栈帧,即只有当前正在执行的方法的**栈帧(栈顶栈帧)**是有效的
		这个活动的栈帧被称为**当前栈帧(Curren Frame)**,与当前栈帧相对应的方法就是**当前方法(Curren method),**
		定义这个方法的类就是**当前类(Curren Class)**

执行引擎运行的所有字节码指令只针对当前栈帧进行操作

如果在该方法中调用了其他方法,对应的新的栈帧会被创建出来,方法在栈顶中,成为当前栈帧

不同的线程所包含的栈帧是不允许调用存在相互应用的,即不可能一个栈帧之中引用另外一个线程的栈帧

如果当前方法调用了其他方法,方法返回地址之际,当前栈帧会传回此方法执行结果给前一个栈帧,接着 虚拟机会          丢弃当前栈帧会传回此方法执行结果给前一个栈帧

Java方法由两种返回函数的方法 一种是正常的方法返回,使用return指令,另外一种是抛出异常,不管是那种方式都会被弹出栈帧

#### 3.3.7设置栈的大小

我们可以通过使用参数-Xss 选项来设置线程的最大空间,栈的大小直接决定了函数调用的最大可达深度

**设置栈大小  -Xss256k**

![](.\png\设置栈的大小.png)

```java
/**
 * @author gpb
 * @date 2021/7/29 21:14
 * -Xss256k
 */
public class StakTest {
    private static int count = 0;
    public static void main(String[] args) {
        // at com.bo.jvm.stackFarme.StakTest.main(StakTest.java:11)
        System.out.println(count++);
        main(args);
    }
}
```

#### 3.3.8栈帧的内存结构

​        **局部变量表(Local Variables)**
​				**操作数栈(Operand Stack) or 表达式栈**
​				**动态链接(Dynamic Linking) 指向运行时常量池的方法引用**
​				**方法返回地址(Return Address) 方法正常退出 或者 异常退出的定义**
​				**一些附加信息**

![](.\png\栈的内存结构.jpg)

##### 3.3.8.1**局部变量表(Local Variables)**

局部变量表也称为局部变量数组或本地变量表

<font color=red>定义为一个数字数组,主要用于存储方法参数和定义在方法体内的局部变量</font>，这些数据类型包括各类的基本数据类型、引用类型，以及return Address类型

由于局部变量表是建立在线程的栈上的,是线程私有数据,因此不存在数据安全问题

<font color=red>局部变量表所需要的容量大小实在编译器确定下来的</font>,并保存在方法的Code属性的中maxmun local variables数据项中。在方法运行期间是不会改变局部变量表的大小

参数值的存放总是在局部变量数组的index0开始，到数组长度-1的索引结束

**局部变量表，最基本的存储单元是Slot(变量槽)**

局部变量表中存放编译器可知的各种基本数据类型(8种),应用类型(reference)，return Address类型

在局部变量表里,<font color=red>32位以内的数据类型只占用一个slot(包括returnAddress类型),64位的类型(long 和 double)占用两个slot</font>

byte、short、char，boolean 在存储之前被转换为int

long 和 double 则占据俩个slot

**局部变量表的大小**

![](.\png\局部变量表的大小.png)

**局部变量表中的数据**

![](.\png\局部变量表中的数据.png)

###### 3.3.8.1.1slot的理解

![](.\png\slot.jpg)

JVM会认为局部变量表中的每一个slot都分配一个访问索引,通过这个索引即可成功访问到局部变量中指定的局部变量值

当一个实例方法被调用的时候,<font color=red>他的方法参数和方法体内部定义的局部变量将会按照顺序复制到局部变量表中的每个slot上</font>

<font color=red>如果需要访问局部变量表中一个64bit的局部变量值时,只需要使用一个索引即可</font>(比如 long or double的变量)

<font color=red>如果当前栈帧是由构造方法或者实例方法创建的(非静态方法),那么该对象引用this将会存放在index0的slot处</font>,其余的参数按照参数表的顺序继续排序

###### 3.3.8.1.2局部变量和静态变量的区别

参数表分配完毕之后,再根据方法体内的定义的变量的顺序和作用域分配

我们知道类变量表有两次初始化的机会,第一在"<font color=red>准备阶段</font>",执行系统初始化,对类变量设置0值，另一次则是”<font color=red>初始化“</font>阶段,赋予程序在代码中的定义的初始值

和类变量的初始化不同的是,局部变量表不存在系统初始化的过程,这意味一旦定义了局部变量测必须认为初始化，否则无法使用

变量分类：

​			基本数据类型

​			引用数据类型

也可以在类中声明的位置分

​				成员变量

​				局部变量：在使用前必须显示赋值，否则编译不通过

成员变量分为：

​		在使用前都有初始化赋值

​			类变量：linking(链接)的准备阶段给类变量默认赋值,initial(初始化)阶段：给类的变量赋值即静态代码块<clinit>

​			实例变量: 随着对象创建而创建，会在堆空分配实例变量空间，并进行默认赋值

###### 3.3.8.1.3补充说明

​	在栈帧中，与性能调优关系最为密切的部分就是局部变量表，在方法执行时，虚拟机使用局部变量表完成方法的传递

<font color=red>局部变量表中的变量也是重要的垃圾回收的根节点，只要被局部变量表中直接或间接引用的对象就不会回收掉</font>

#### 3.3.9操作数栈(Operand Stack)

每一个独立的栈帧中除了包含布局变量表以外，还包含一个后进先出（last-in-first-out）,的操作数栈，也可以成为表达式栈

操作数栈，在方法执行过程中，根据字节码指令，往栈中写入数据或提前数据，既入栈（push），出栈（pop）

某些字节码指令将值压入操作数栈，其余的字节指令将操作数取出栈。使用它们后在把结果压入栈

比如：执行赋值，交换，求和等计算的操作

![](.\png\操作数栈.png)

如果被调用的方法带有返回值的话，其返回值将会被压入当前栈帧的操作数栈中，并更新PC寄存器中下一条需要执行的字节码指令

操作数栈中元素的数据类型必须与字节码指令的序列严格匹配，这又编译器在编译器期间执行验证，同时在类加载过程中的类检查阶段的数据流分析阶段要再次验证

另外，我们说Java虚拟机的解释引擎是基于栈的执行引擎，其中的栈值得就是操作数栈

**操作数栈：主要用于保存计算过程的中间结果，同时作为计算过程中变量临时的存储空间**

操作数栈就是JVM执行引擎的一个工作区，当一个方法刚刚开始执行的时候，一个新的栈帧也会随之被创建出来，这个方法的操作数栈是空的

每个一操作栈都会拥有一个明确的栈深度用于存储数据值，其所需的最大深度在编译期就定义好了，保存在方法的Code属性中，为max_stack的值

栈中任何一个元素都是可以任意的Java数据类型

​			32bit的类型占用一个栈单位深度

​            64bit的类型占用两个栈单位深度

**操作数炸并非次采用访问索引的方式来进行数据访问的，而只能通过标准的入栈（push）和出栈（pop）来完成一个数据访问**

代码跟踪：

​	![](.\png\操作数栈.jpg)

由于操作数是存储的过程在内存中的，因此频繁的执行内存读\写操作必然会影响执行速度，为解决这个问题，提出栈顶缓存技术，将栈顶元素全部缓存到物理CPU的寄存器中，以此降低对内存的读|写次数，提升执行引擎的执行效率

#### 3.3.10动态链接（Dynamic Linking)/指向运行时常量池的方法引用

每一个栈帧内部都包含一个指向运行时常量池中该栈帧所属方法的引用，包含这个引用的目的就是为例支持当前方法的代码能过动态链接，比如 invokedynamic指令 虚方法

在Java原文中，被编译到字节码文件中，所以的变量和方法作用为符号引用(Symbolic Reference)，保存在class文件中常量池中，比如：描述一个方法调用了另外的其他方法时，就是通过常量池中指向方法的符号引用来表示，**那么动态链接的作用就是为了将这些符号引用转换为调用方法的直接引用**

![](.\png\动态链接.jpg)

**为什么需要常量池？**

​				常量池的作用，就是为了提供一些符号和常量，便于指令的识别，复用

#### 3.3.11.方法的调用

在jvm中，将符号引用转换为调用方法的直接引用与方法的绑定机制有关，绑定时一个字段，方法或类在符号引用被替换成直接引用的过程，这仅仅一次

静态链接/早期绑定：

​		**当一个字节码文件被装载进jvm内部中，如果被调用的目标方法在编译期可知，且运行期保持运行期间不变时，这中情况下将嗲用方法的符号引用转换为直接引用的过程为静态链接**

动态链接/晚期绑定：

​		**如果被调用的方法在编译期无法被确定下来，也就是说，只能够在程序运行期将调用方法的符号引用转换成直接引用，由于这种引用转换过程具备动态性，因此就成为：动态链接**

Java中任何一个普通方法都具有虚函数的特征，它们相对于C++语言中的虚函数（C++中则需要使用关键字virtual来显示定义），如果在Java程序中不希望某个方法用于虚函数的特征，则可以使用关键字final来标志这个方法

非虚方法：

​			如果方法在编译期来确定了具体的调用版本，这个版本在运行时不可变的这样的方法成为非虚方法

​           静态方法，私有方法，final方法，实例构造器，父类方法都时非虚方法

​          其他方法成为虚方法

​		  子类对象的多态性在使用前提，类的继承关系，方法重写

虚拟机中的指令

​				**invokestatic：调用静态方法，在解析阶段确定唯一方法的版本**

​				**invokespecial：调用<init>方法，私有及父类方法，解析阶段确定唯一方法版本**

  			  **invokevirtual：调用所以的虚方法**

 			   **invokeinterface：调用接口方法**

**、            invokedynamic：动态解析出需要调用方法，然后执行**

前四条指令固定化在虚拟机内部，方法的调用执行不可人为干涉，而invokedynamic指令则支持用用户确定方法版本，**其中invokestatic指令和invokespecial指令调用方法成为非虚方法，其余的(final修饰的除外)称为虚方法**

**invokedynamic指令在jdk7增加一个，这是Java为了实现动态类型语言支持而做的一中改进**

**在Java7中并没有提供直接生成nvokedynamic指令的方法，需要借助ASM这种底层字节码工具来生成invokedynamic指令，直到Java8的lambda表达式的出现，invokedynamic指令的生成，在Java中才用了直接生成的方法**

Java语言中方法重写的本质？

​	找到操作数栈顶的第一个元素所执行的对象的实际类型，标记为 C

​	如果在类型 C 中找到与常量池中描述符号简单名称都相符的方法，则运行访问权限校验，如果通过则返回这方法的直接引用，查找过程结束，如果不通过，则返回Java.lang.IllegalAccessError 异常

否则，按照继承关系从下往上一次对 C 的各个父类进行第二部的搜索和验证的过程

如果始终没有找打合适的方法，则抛出Java.lang.AbstractMethodError 异常

虚方法表

![](.\png\虚方法表.jpg)

为了提高性能，JVM采用在类的方法区，建立一个虚方法表（virtual method table）（非虚方法不会出现在这个表中），使用索引表代替查找

每个类中都有一个虚方法表，表中存着各个方法的实际入口

虚方法表啥时候创建？

虚方法表会在类加载的链接阶段被创建，并开始初始化，类的变量初始化准备完成之后，JVM会把该类的方法表也初始化完毕

#### 3.3.12.方法返回地址（Return Address）

用于存放调用该方法的pc寄存器的值

一个方法的结束，有个方式：

​		正常执行完成

​		出现未处理的异常，非正常退出，异常退出

**无论通过那种方法退出后，在方法退出后都返回到该方法被调用的位置，方法正常退出时，调用者的pc计数器的值作为返回地址，即调用该方法的指令的下一条指令的地址。而通过异常退出，返回地址时要通过异常表来确定，栈帧中一般不会保存这些信息**

**本质上就是，方法的退出就是当前栈帧出栈的过程，此时，要恢复上层方法的局部变量表，操作数栈，将返回地址压入调用者栈帧的操作数栈，设置pc寄存器值等，让调用者方法继续执行下去**

**正常完成出口和异常完成出口的区别在与：通过异常完成出口退出的不会给他的上层调用者产生任何的返回值信息**

执行引擎遇到任何一个方法返回指令的字节码指令(return) 会有返回值传递给上层的方法调用者，简称正常完成出口

一个方法在正常调用完成之后究竟需要使用哪一个返回指令，还需要根据方法返回值的实际数据类型而定

在字节码指令中返回指令包含 ireturn（当返回值为 boolean,byte，char，short，int ）,lreturn,freturn、dreturn、以及areturn（引用类型返回指令）另外还有一个return指令供void方法，实例初始化的方法，类和接口的初始化方法调用

异常退出：

​	在方法执行的过程中遇到了异常，并且这个异常没有在方法内进行处理，也就说，只有在本方法的异常表中并没有搜索到匹配的异常处理器，就会导致方法退出，为异常处理

方法执行过程中，抛出异常时异常处理，存储在一个异常处理表中，方便在发生异常的时候找打处理异常的信息

异常表

​		![](.\png\异常表.png)

#### 3.3.13.一些附件信息

**栈帧中还允许携带与Java虚拟机实现相关的一些附件信息，比如 对程序调用提供支持信息**

#### 3.3.14.栈的问题？

**举例栈溢出出现情况？**

​	StackOverflowError

   通过-Xss设置栈的大小，如果没有设置栈自动扩展内存，超出你设置的栈的内存大小，就包栈溢出，如果你设置栈内存自动扩展，当栈的内存超过最大内存也会栈溢出

**调整栈大小，就能保证不会出现异常？**

不能，如果出现死递归，也会查询栈溢出，设置了栈的大小会栈溢出会包会晚一些

**分配的栈内存越大越好？**

分配的内存越大，出现栈溢出的会晚一些，不是，如果栈内存大了，那么其他的内存就会少，就那么一亩三分地，分配内存

**垃圾回收是否会涉及到虚拟机栈？**

​	不会，垃圾回收不能设计到栈的回收，因为栈，就俩个操作出栈和入栈的操作，所以不会涉及到垃圾回收

**方法中定义的局部变量是否安全？**

具体问题具体分析

```
public static void method1(){
    // StringBuilder 线程不安全  stringBuilder变量在方法内局部变量时线程安全的
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("a");
    stringBuilder.append("b");
}

public static void method2(StringBuilder stringBuilder){
    // stringBuilder时线程不安全的
    stringBuilder.append("a");
    stringBuilder.append("b");
}
/**
 *  stringBuilder 不安全 如果,返回的时String类型,
 *  那么stringBuilder是安全的,返回的String 那个字符时不安全的
 * @return
 */
public static StringBuilder method3(){
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("a");
    stringBuilder.append("b");
    return stringBuilder;
}
```

### 3.4.堆 heap

​	一个JVM实例只存在一个堆内存，堆也是Java内存管理的的核心区域

​	Java堆区在jvm启动的时候即被创建，其空间大小也就确定了，是jvm管理的最大一块内存空间

​		堆内存的大小可以调节

《java虚拟机规范》规定，堆可以处于物理上不连续的内存空间中，但在逻辑上他应该被视为连续的

所用的线程共享Java堆，在这里还可以划分线程私有的缓冲区（Thread Local Allocation Buffer，TLAB）

**设置堆的初始大小：-Xms10m**

**设置堆的最大内存：-Xmx10m**

**打印内存GC的细节：-XX:+PrintGCDetails**

![](.\png\堆.png)

《Java虚拟机规范》中堆Java堆的描述是：所有的对象实例以及数组都应该运行时分配在堆上，**也可以理解为 几乎所用的对象实例都这里分配内存，从实际使用的角度上看**

数组和对象可能永远不会存储在栈上，因为栈帧中保存引用，这个引用指向对象或者数组在堆中的位置

在方法结束后，堆中的对象不hi马上被移除，仅仅在垃圾收集的时候才会被移除

堆，是GC (Garbage Collection, 垃圾收集器) 执行垃圾回收的重点区域

![](.\png\栈,堆,方法区.jpg)

new 对象的直接指令  new

![](.\png\new字节指令.png)



#### 3.4.1堆的内存细分

​		**现代垃圾收集器大部分都基于分代收集理论设计，堆空间细分为**

​	**Java7 及以前堆内存逻辑上为三部分，新生区，养老区，永久区**

​			**Young Generation Space 新生区 Young\New**

​			**Tenure Generation Space 养老区**

​			**Permanent Space             永久区  Perm**

**Java8 及以后堆内存逻辑上分为三部分，新生区，养老区，元空间**

​			**Young Generation Space 新手区  Young\New**

​							**又被化为为 Eden区和Survivor区**

​			**Tenure  Generation       养老区   old\Tenure**

​			**Mate Space					元空间  Mate**

**新手区 == 新生代 == 年轻代    养老区 == 老年区 == 老年代  永久区 == 永久代**

![](.\png\堆空间内部结构.jpg)

#### 3.4.2.堆空间大小的设置

​	Java堆区用于存储Java对象实例，那么堆的大小在JVM启动时就已经设定好了，大家也可以通过选项-Xms,-Xmx来进行设置

**-Xms 用于表示堆区的起始内存，等价于 -XX:InitiaLHeapSize**

**-Xmx 用于表示堆区的最大内存，等价与 -XX:MaxHeapSize**

一定堆区中的内存大小超过：-Xmx 所指定的最大内存时，就会抛出OutOfMemoryError异常

**通常会将 -Xms 和 -Xmx 两个参数配置相同的值，其目的时为了能够在Java垃圾回收机制清理完堆区后不需要重新分割计算堆区的大小，从而提高性能**

默认情况下：

​						**初始化内存大小：物理电脑内存大小 六十四分之一**

​						**堆最大内存大小：物理电脑内存大小 四分之一** 

```java
/**
 * -Xms 用来设置堆空间(年轻代+老年代)的初始值大小
 *      -X 时JVM的运行参数
 *      mx 时memory start
 * -Xmx 用来设置堆空间(年轻代+老年代)最大内存
 *
 * 当你自己设置堆的内存大小的时候，你会发现，你设置堆的最大内存，通过Java代码查看堆的内存会少一些
 *  是因为，系统没有计算，S0 或者 S1 其中的一个区域
 *
 *  查看设置的参数的方式：
 *                  1. jps  jstat -gc 进程Id
 *                  2. -XX:+PrintGCDetails
 * @author gpb
 * @date 2021/8/14 19:24
 */
public class HeapSpaceInitial {

    public static void main(String[] args) {
        // 返回Java虚拟机中的堆内存总量
        long initalMemory = Runtime.getRuntime().totalMemory() / 1024 /1024;
        // 返回Java虚拟机试图使用最大内存的大小
        long maxMemory = Runtime.getRuntime().maxMemory() / 1024 / 1024;

        System.out.println("-Xms:初始值大小：" + initalMemory + "M");
        System.out.println("-Xmx:堆最大小：" + maxMemory + "M");

        System.out.println("系统内存大小为：" + initalMemory * 64.0 / 1024 + "G");
        System.out.println("系统内存大小为：" + maxMemory * 4.0 / 1024 + "G");

        try {
            Thread.sleep(100000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
```

jps命令 可以查看JVM的进程

jstat命令 可以查看GC内存使用的信息

OOM:

​	

```java
/**
 * 设置参数
 *          -Xms600m -Xmx600m -XX:+PrintGCDetails
 * @author gpb
 * @date 2021/8/14 19:49
 */
public class OOMTest {

    public static void main(String[] args) {
        ArrayList<Object> list = new ArrayList<>();
        while (true){
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            list.add(new Picture(new Random().nextInt(1024 * 1024)));
        }
    }

}
class Picture{
    private byte[] pixls;

    public Picture(int length) {
        this.pixls = new byte[length];
    }
}
异常
    Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
	at com.bo.jvm.heap.Picture.<init>(OOMTest.java:31)
	at com.bo.jvm.heap.OOMTest.main(OOMTest.java:22)

```

#### 3.4.3.年轻代与老年代

存储在JVM中的Java对象可以被划分为两类：

​	一类是生命周期较短的瞬间对象，这类对象的创建和消亡都非常迅速

​	另外一类对象的生命周期非常长，在某些极端的情况下还能够与jvm的生命周期保持一致

**Java堆区进一步细分的话，可以划分为年轻代(YoungGen)，和老年代(OldGen)**

**其中年轻代又可以划分为Eden空间，Survivor0空间和Survivor1空间，（又是也叫做from区，to区）**

![](.\png\YoungGenAndOldGen.png)

配置新生代与老年代在堆结构的占比

​			默认-XX:NewRatio=2,表示新生代占1，老年代占2，新生代占整个堆的1/3

​			可以修改-XX:NewRatio=4,表示新生代占1，老年代占4，新手代占整个堆的1/5

![](.\png\堆的比例.jpg)

jinfo -flag 参数 线程Id

**在HotSpot中，Eden空间和另外两个Survivor空间缺省所占的比例是8：1：1**

**可以使用-XX:SurvivorRatio 调整 Eden区和Survivor区的比例，-XX:SurvivoRatio=8**

几乎所用的Java对象都是在Eden区new出来

绝大部分的Java对象的销毁都在新生代，进行了

​		IBM公司的专门研究表明，新手代中80%的对象都是 朝生夕死的

**可以使用选项 -Xmn 设置新生代最大内存大小**

​	这个参数一般使用默认值就可以了

```java
-XX:-UserAdaptiveSizePolicy  关闭自适应的内存分配策略
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
```

![](.\png\对象的晋升.jpg)



如果你同设置了

​		 **-Xmn 设置新生代最大内存大小，和 -XX:NewRatio=2 新生代的比例，则会以-Xmn为主**

#### 3.4.4.对象分配过程

**1.new的对象先放在Eden区，此区有大小限制**

**2.当Eden区的空间填满时，程序又需要创建对象，jvm的垃圾回收器将Eden区进行垃圾回收(Minor Gc)，将Eden区中不被其他对象所引用的进行销毁，在加载新的对象发在Eden区**

**3.然后将Eden中的剩余的对象移动Survivor1区或者Survivor0区中其中一个区域，幸存者区**

**4.如果在次触发垃圾回收，此时上次幸存下来的放到Survivor0区的，如果没有回收，就会方法Survivor1区**

**5.如果再次经历垃圾回收，此时会从新放到Survivor0区，接着再次把幸存的对象放在Survivor1区**

**6.啥时候能放进老年区，可以设置次数，默认时15此**

​			<font color=red>**可以通过设置参数：-XX:MaxTenuringThreshold=<N>进行设置**</font>

**7.在养老区，相对悠闲，当养老区内存不足的时，再次触发GC,Major Gc,进行养老区的内存清理**

**8.若养老区执行了Major Gc之后发现发现依然无法进行对象的保存，就会产生oom异常**

​		**Java.lang.OutOfMemoryError：Java heap space**

![](.\png\对象晋升的过程.png)

总结：

​			**针对幸存者是s0,s1区的总结：复制之后有交换，谁空谁是to**

​			**关于垃圾回收：频繁在新生区收集，很少在养老区收集，几乎不在永久区/元空间收集**

#### 3.4.5.对象分配特殊过程

![](.\png\对象分配特殊情况.jpg)

#### 3.4.6.Minor GC/YGC、MajorGC/OldGc、FullGC

JVM在进行GC时，并非每次都对三个内存（新生代，老年代，方法区（永久代，元空间））区域一起回收的，大部分时候回收的都是指新生代回收：minor GC

针对hotSpot VM实现，它里面的GC按照回收区域又分为两大种类型：一种是部分收集(Partial GC),一种是整堆收集（Full GC）

部分收集：不是完整收集整个Java堆的垃圾收集，其中又划分为：

​						新生代收集（Minor GC / Young GC）只是新生代( Eden\S0,S1)的垃圾收集

​						老年代收集（Major GC / Old GC）只是老年代的垃圾收集

​						  	目前，只有CMS GC会有单独收集老年代的行为

​						  	注意，很多时候Major GC会和Full GC混淆使用，需要具体分辨是老年代，还是整个堆回收

​						混淆收集（Mixed GC）收集整个新生代以及部分老年代的垃圾收集

​												目前，只有G1，GC会有这种行为

整个堆收集（Full GC）收集整个Java堆和方法区(元空间)的垃圾收集

##### 3.4.6.1.年轻代GC(Minor GC)触发机制

​	**当年轻代空间不足时，就会触发Minor GC,这里的年轻代满纸指的是Eden代满，Survivor满不会引起GC(每次 Minor GC 会清理年轻代的内存)**

因为Java 对象 <font color=red>大多都具备朝生夕死的特征</font>，所以 Minor GC 非常频繁，一般回收速度也比较快，这一定义即清晰又抑郁理解

Minor GC会引发STW(用户线程停止)，暂停其他用户的线程，等垃圾回收结束，用户线程才能恢复运行，为啥要STW?,是因为避免一般在回收垃圾，一边制造垃圾

![](.\png\年轻代.jpg)

##### 3.4.6.2.老年代GC( Major GC)触发机制

指发生在老年代的GC，对象从老年代消失时，我们说 Major GC 或 Full GC 发生了

**出现了Major GC，经常会伴随至少一次的Minor GC（但非绝对的，在Parallel Scavenge收集器的收集策略里就有直接进行Major GC的策略选择过程）**

​             **也就是在老年代空间不足时，会先尝试触发Minor GC，如果之后空间还不足，测触发Major GC**

Major GC的速度一般挥笔Minor GC慢上10倍以上，STW时间更长

如果Major GC后，内存还不足，就报OOM

##### 3.4.6.3.Full GC触发机制

触发Full GC 执行的情况又如下五种情况：

​			调用System.gc()；时 系统建议执行Full GC,但是必然执行

​			老年代空间不足

​			方法去空间不足

​			通过Minor GC 后进去老年代的平局大小大于老年代的可用内存

​			由Eden区，survivor space0( from space)区向 survivor space1（to space）区复制时，对象大小大于To Space可用内存，则把该对象转存到老年代，且老年代的可用内存小于该对象大小

​			full gc时开发或调优中尽量要避免，这样暂时时间或短一些

```java
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
```



#### 3.4.7.堆空间分代思想

**<font color=red>为什么需要把Java堆分代？不分代就不能正常工作了吗？</font>**

​		经过研究，不同对象的生命周期不同。70%-99%的对象时临时的对象

​		新生代：由Eden、两块大小相同的Survivor（又称为from/to s0/s1）构成

​		老年代：存放新生代经历多次GC仍然存活的对象

![](.\png\jdk7堆分代.jpg)

​		不分代完全也可以，分代的唯一理由就是**优化GC性能**，如果没有分代，那所以的对象在一块，就如同把所以部门的同时，发到一个房间（如同把一个学校的人都关在，一个教室）。GC的时候要找到那些对象没用，这样就会对堆的所以的空间进行扫描，而很多的对象都是朝生夕死的，如果分代的话，把新创建的对象放到某一地方，当GC 的时候先把这块内存 朝生夕死 对象的区域进行回收，这样就会腾出很大的空间出来

![](.\png\jdk8堆分代.jpg)

#### 3.4.8.内存分配策略

如果对象在Eden 出生经过第一次MinorGC 后仍然存活，并且能被Survivor容纳的话，将被移动到Survivor空间中，并将对象的年龄设为1，对象在Survivor区中每熬过一次MinorGC，年龄就增加1岁，当他的年龄增加到一定程度（默认为15岁，其实每个JVM、每个GC都有所不同）时，就会被晋升到老年代中。

对象晋升老年代的年龄阙值，可以通过选项 -XX:MaxTenuringThreshold来设置

针对不同年龄段的对象分配原则如下所示：

​			优先分配到eden

​			大对象直接分配到老年代

​					尽量避免程序中出现过多的大对象

​			长期存活的对象分配到老年代

​			动态对象年龄判断

​					如果Survivor 区中相同年龄的所以对象大小的总和大于Survivor空间的一半，年龄大于或等于该年龄的对象可以直接进入老年代，无需等到MaxTenuringThreshpld中要求的年龄

​			空间分配担保

​				-XX:HandlePromoitionFailure,意思为 在经过GC,还有大量的对象存活，而Survivor中没有了空间，在老年代由足够的内存，可以担保，Survivor区中的对象可以晋升到老年代

```java
/**
 * 测试 大对象直接晋升到老年代
 * 参数
 *      -Xms60m -Xmx60m -XX:NewRatio=2 -XX:SurvivorRatio=8 -XX:+PrintGCDetails
 * @author gpb
 * @date 2021/8/15 20:22
 */
public class YoungOldAreaTest {

    public static void main(String[] args) {
        byte[] buffer = new byte[1024 * 1024 * 20]; //20m
    }

}
```

#### 3.4.9.对象分配内存：TLAB

![](.\png\TLAB.jpg)

**为什么有TLAB（Thread  Local Allocation  Buffer）？**

​	堆区是线程共享的区域，任何线程都可以访问堆区中的共享数据

​	由于对象实例的创建在JVM中非常频繁，因此在并发环境下从堆区中划分内存空间是线程不安全的

​	为了避免多个线程操作同一个地址，需要使用加锁的机制，进而影响分配速度

**什么是TLAB?**

​		从内存模型而不是垃圾收集的角度，堆Eden区进行进行划分，JVM为每个线程分配了一个私有缓存区域，他包含在Eden区 

​		多线程同时分配内存时，使用TLAB可以避免一系列的非线程安全问题，同时还能过提升内存分配的吞吐量，因此我们可以将这种内存分配方式称为 **快速分配策略**

​		所以OpenJDK衍生出来的JVM都提供了TLAB的设计

**TLAB的再次说明：**

​				尽管不是所有的对象实例都能够在TLAB中成功分配内存，但JVM确实时将TLAB作为内存分配的首选

​				在程序中，开发人员可以同，**-XX:UseTLAB** 设置是否开启TLAB空间

​				默认情况下，TLAB空间的内存非常小，仅占整个Eden空间的1%，    当然我们可以通过选项：     **-XX:TLABWasteTargePercent**,设置TLAB空间所占用Eden空间的百分比

​				一旦对象在TLAB空间分配内存失败时，JVM就尝试在通过使用加锁的机制确保数据操作的原子性，从而直接在Eden空间中分配内存

```java
/**
 * 测试 -XX:UseTLAB 参数是否开启的情况
 *  
 *  查看步骤：
 *          jsp 查看该线程ID
 *          jinfo -flag UseTLAB
 * @author gpb
 * @date 2021/8/15 20:48
 */
public class TLABArgsTest {

    public static void main(String[] args) {
        System.out.println("嗨喽，TLAB");
        try {
            Thread.sleep(10000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
```



![](.\png\TLAB对象分配过程.jpg)



#### 3.4.10.堆空间参数设置

​		官方说明：

​					https://docs.oracle.com.javase/8/docs/technotes/tools/unix.java.html

​		

| 参数                         | 意思                                                   |
| ---------------------------- | ------------------------------------------------------ |
| -XX:PrintFlagsInitial        | 查看所以的参数的默认值                                 |
| -XX:PrintFlagsFinal          | 查看所以的参数的最终值（可能会存在修改，不再时初始值） |
| -Xms                         | 初始堆空间内存(默认为物理内存的1/64)                   |
| -Xmx                         | 最大堆的内存（默认为物理内存的1/4）                    |
| -Xmn                         | 设置新生代的大小（初始值和最大值）                     |
| -XX:NewRatio                 | 配置新生代与老年代在堆结构的比例（默认时2）            |
| -XX:SurvivorRatio            | 设置新生代中Eden和s0/s1空间的比例 默认 8：1：1         |
| -XX:MaxTenruingThreshold     | 设置新生代垃圾最大年龄                                 |
| -XX:+PrintGCDetails          | 输出详细的GC处理日志                                   |
| -XX:+PrintGC  或 -verbose:gc | 打印gc简要信息                                         |
| -XX:HandlePromotionFailure   | 是否设置空间内存担保                                   |

具体查看某个参数的指令：

​				jps     查看当前运行的进程

​				jinfo  -flag 参数  进程Id

**-XX:HandlePromotionFailure 解释：**

​		在发生Minor GC之前，虚拟机会<font color=red>检查老年代最大可用的连续空间是否大于新生代所以对象总空间</font>

​		如果大于，则此次Minor GC是安全的

​		如果小于，则虚拟机会查看-XX:HandlePromotionFailure设置值是否运行担保失败

​					如果HandlePromotionFailure=true,那么会<font color=red>继续检查老年代最大可用连续空间是否大于历次晋升到老年代的对象平均大小</font>

​							如果大于，测尝试进行一次Minor GC，但这次Minor GC依然是有风险的

​							如果小于，则改成进行一次Full GC

​		          如果HandlePromotionFailure=false,则进行一次full GC

在JDK6 UPdate24之后，HandlePromotionFailure参数不会在影响到虚拟机的空间分配担保策略，观察OpenJDK中的源码变化，虽然源码还定义了

24之后的规程变为：<font color=red>只要老年代的连续空间大于新生代对总大小 或者 历次晋升到平局大小就会进行一次Minor GC</font>,否则将进行Full GC

#### 3.4.11.堆是分配对象存储的唯一选择吗?

​		在《深入理解Java虚拟机》中关于Java堆内存有这样的一句话：

​		随着JIT编译器的发展与<font color=red>逃逸分析技术</font>逐渐成熟，<font color=red>栈上分配、标量替换优化技术</font>将会导致一些微妙的变化，所以的对象都分配到堆上也渐渐变得不那么 绝对了

​		在Java虚拟机中，对象是在Java堆中分配内存的，这是一个普遍的常识，但是，有一种特殊情况，那就是<font color=red>如果经过逃逸分析（Escape Analysis）后发现，一个对象并没有逃逸出方法，那么就可能被优化成栈上分配。</font>这样就无需在堆上分配内存，也无须进行垃圾回收了，这也是最常见的堆外存储技术

​		此外， 前面提到的基于OpenJDK深度定制的TaoBaoVM,其中创新的GCIH( GC invisible heap)技术实现off-和安排，将生命周期较长的对象从heap中移至heap外，并且GC不能管理GCIH内部的Java对象，以此达到降低GC的回收频率和提升GC的回收效率的目的

**逃逸分析概述:**

​		如何将堆上的对象分配到栈，需要使用逃逸分析手段

​		这是一种可以有效减少Java程序中同步负载和内存堆分配压力的跨函数全局数据流分析算法

​		通过逃逸分析，Java Hotspot编译器能过，分析出一个新的对象引用的使用范围从而决定是否要将这个对象分配到堆上

​		逃逸分析的基本行为就是分析对象动态作用域：

​						当一个对象在方法中被定义后，对象只在方法内部使用，则认为没有发生逃逸分析

​						当一个对象在方法中被定义后，他被外部方法所引用，则认为发生逃逸，例如作为调用参数传递到其他地方中

   没有发生逃逸的对象，则可以分配到栈上，随着方法执行的结束，栈空间就被移除

**参数设置：**

​		在jdk6 update23（JDK7）版本之后，HotSpot中默认就已经开启了逃逸分析

​		如果使用的是较早的版本，开发人员可以通过：

​				**-XX:+DoEscapeAnalysis 显示开启逃逸分析**

​				**-XX:+PrintEscapeAnalysis  查看逃逸分析的筛选结果**

代码：

```java
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
```

结论：

​		**开发中能使用局部变量的，就不要使用在方法外定义**

##### 3.4.11.1.优化代码—栈式分配

​	**栈上分配，将堆分配转换为栈上分配，如果一个对象在子程序中被分配，要使指向该对象的指针永远不会逃逸，对象可能是栈分配的候选，而不是谁分配**

​		JIT编译器在编译期间根据逃逸分析的结果，发现如果有个对象并没有逃逸出方法的话，就肯能被优化成栈上分配，分配完成后，继续在调用栈内执行，最后线程结束，栈空间被收回，局部变量对象也被回收，这样就无需进行垃圾回收

**常见的栈式分配常见：**

​		逃逸分析中，已经说明了。分别是给成员变量赋值，方法返回值、实例引用传递发生了逃逸，不在栈上分配

```java
/**
 * 测试栈式分配
 * 参数
 *      -Xmx1G -Xms1G -XX:+DoEscapeAnalysis -XX:+PrintGCDetails
 *         -XX:+DoEscapeAnalysis 显示开启逃逸分析
 *          -XX:+PrintEscapeAnalysis  查看逃逸分析的筛选结果
 * @author gpb
 * @date 2021/8/16 21:13
 */
public class StackAllocation {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000000; i++){
            alloc();
        }
        long end = System.currentTimeMillis();
        System.out.println("花费的时间为：" + (end - start) + "ms");
        try {
            Thread.sleep(100000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    /**
     * 没有发生逃逸
     */
    private static void alloc() {
        User user = new User();
    }
    static class User{

    }
}
```

##### 3.4.11.2.优化代码—同步省略

**同步省略 如果一个对象被发现只能从一个线程被访问到，那么对于这对象的操作考虑同步的问题**

线程同步的代价是相当高的，同步的后果是降低并发性和性能

在动态编译同步块的时候，JIT编译器可以借助逃逸分析来<font color=red>判断同步块所使用的锁对象是否只能被一个线程访问而没有被发布到其他线程。</font>如果没有，那么JIT编译器这个同步块的时候就会取消这部分代码的同步，这样就能大大提高并发性和性能，这个取消同步的过程就叫**<font color=red>同步省略，也叫做锁消除</font>**

```java
/**
 * 同步省略 或 锁消除
 * 同步省略 如果一个对象被发现只能从一个线程被访问到，那么对于这对象的操作考虑同步的问题**
 * @author gpb
 * @date 2021/8/16 21:42
 */
public class SynchronizedTest {

    /**
     * 1.无法启动到 加锁的效果，因为 没进来一个线程 都会创建一个对象，锁不是一把锁
     * 2.如果该对象没有发生逃逸，可以不使用 synchronized
     * 3.当你Javap class文件的时候， 也会看到 monitorenter 和  monitorexit 指令，但是在编译器执行的时候会自动消除锁
     */
    public void method(){
       Object obj = new Object();
       synchronized (obj){
           System.out.println(obj);
       }
   }

}
```

##### 3.4.11.3.优化代码—标量替换|分离对象

**分离对象或变量替换  有的对象可能不需要作为一个连续的内存结构存在也可以被访问到，那么对象的部分（或全部）可以不存储在内存，而是存储在CPU寄存器中。**

**<font color=red>标量(Scalar)</font>**,是指一个无法再分解成更小的数据的类型，Java中的原始数据类型就是标量

相对的，**那些还可以分解的数据叫做<font color=red>聚合量(Aggregate)</font>**,Java中的对象是聚合量，因为它可以分解成其他聚合量和标量

在JIT阶段，如果经过逃逸分析，发现一个对象不会被外界访问的话，那么经过JIT优化，就会把这个对象拆分成若干个其中成员变量来代替，这个过程就是**标量替换**

**Java对象==<font color=red>聚合量(Aggregate)</font>,**

**基本数据类型==<font color=red>标量(Scalar)</font>**

```java
class Point{
    private int x;
    private int y;
}
public static void main(String[] args){
    alloc();
}
public static void alloc(){
    Point point = new Point(1,2);
    System.out.println("point.x=" + point.x +"; point.y=" + point.y);
}
/*
	该代码经过标量替换，就会变成
*/
public static void alloc(){
    int x=1;
    int y=2;
    System.out.println("point.x=" + point.x +"; point.y=" + point.y);
}

```

**可以看出，Point这个聚合量经过逃逸分析后，发现它并没有逃逸，就被标量替换成两个标量，**

那么标量替换有什么好处？

​	就是可以大大减少堆内存的占用，因为一旦不需要创建对象了，那么不再需要分配内存

**标量替换为栈上分配提供了很好的基础**

标量替换参数设置：

​				**-XX:+EliminateAllocations  开启标量替换(默认打开)，允许将对象打散分配在栈上**

```java
/**
 * 标量替换测试
 * 参数
 *      -Xms100m -Xmx100m -XX:+DoEscapeAnalysis -XX:+PrintGCDetails -XX:+EliminateAllocations
 *       -XX:+DoEscapeAnalysis 显示开启逃逸分析
 *       -XX:+EliminateAllocations 开启标量替换(默认打开)，允许将对象打散分配在栈上
 * @author gpb
 * @date 2021/8/16 22:09
 */
public class ScalarReplace {
    public static class User{
        public int id;
        public String name;
    }

    /**
     * 未发生逃逸
     */
    public static void alloc(){
        User user = new User();
        user.id = 5;
        user.name = "标量替换";
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000000; i++){
            alloc();
        }
        long end = System.currentTimeMillis();
        System.out.println("花费的时间为：" + (end - start) + "ms");
    }
}
```

##### 3.4.11.4.总结

上述优化，必须在Server模式才有标量标量替换，逃逸分析

​	**参数 ：  -server  启动Server模式，因为只有在Server模式下才有逃逸分析**

​	关于逃逸分析的论文在1999年就已经发表了，但直到jdk1.6采用实现，而且这项技术到如今并不是十分成熟

​	其根本原因就是<font color=red>无法保证逃逸分析的性能消耗一定能高于他的消耗，虽然经过逃逸分析可以做标量替换、栈上分配，锁消除。但是逃逸分析自身也是需要进行一系列复杂的分析，这其实是一个相对耗时的过程</font>

​	一个极端的例子，就是经过逃逸分析之后，发现没有一个对象不逃逸的，那这个逃逸分析的过程就是浪费了

​	虽然这项技术并不十分成熟，但是他也<font color=red>是即时编译器优化技术中一个十分的手段</font>

​	注意到有些观点，认为通过逃逸分析，JVM会在栈上分配那些不会逃逸的对象，这个理论上是可行的，但是取决于JVM的设计者的选择，据我所知，Oracle Hotshot JVM中并没有这么做，这一点在逃逸分析相关的文档里已经说明，所以可以明确所以的对象实例都是创建堆上

​	目前很多书籍还是基于JDK7以前版本，JDK已经发生了很多的变化，intern字符串的缓存和静态变量曾经都被分配在永久代上，而永久代被元数据取代，但是，intern字符串缓存和静态变量并不是移到元数据区，而是直接在堆山分配，所以这一点同意符号前面的结论：对象实例都分配堆上

**<font color=red>Oracle Hotshot JVM没有应用栈上分配，有标量替换 标量替换为栈上分配提供了很好的基础</font>**



### 3.5.方法区/元空间 Method Area

#### 3.5.1.栈、堆、方法区的交互关系

从线程共享的角度来看

​		![](.\png\线程角度上空间.jpg)



**交互关系：**

```java
User user = new User();

User 方法区 .class
user java栈
new User(); java堆
```

![](.\png\堆栈方法区交互关系.png)



#### 3.5.2.方法的理解

《java虚拟机规范》中明确说明：尽管所以的方法区在逻辑上是属于堆的一部分，但是一些简单的实现可能不会选择区进行垃圾收集或者进行压缩，但对于HotSpotJVM而言，方法区还一个别名叫做Non-Heap(非堆)，目的就是要和堆分开

**所以 方法区看做是一块独立与Java堆内存的空间**

方法区（Method Area）与Java堆一样，是各个线程共享的内存区域

方法区在JVM启动的时候被创建，并且它的实际的物理内存空间中和Java堆区一样都可以不连续的

方法区的大小，很堆空间一样，可以选择固定大小或者可扩展

方法区的大小决定了系统可以保存多少个类，如果系统定义了太多的类，导致方法区移除，虚拟机同意会抛出内存溢出错误：Java.lang.OutOfMemoryError: PermGen space 或者 Java.lang.OutOfMemoryError：Metaspace

​			加载大量的第三方jar包，Tomcat部署太多的工程

​			大量使用反射

关闭jvm就会释放这个区域的内存

#### 3.5.3.方法区演进

​	在jdk7及以前，习惯上把方法区，称为永久代，jdk8开始，使用元空间取代永久代

​	本质上，方法区和永久代并不等价，仅是对hotshot而言的《Java虚拟机规范》对如何实现的方法区，不做统一要求：比如：BEA JRockit / IBM j9中不存在永久代的观念

​			现在来看，当年使用永久代，不是好的idea,导致Java程序更容易OOM(超出 -XX:MaxPermSize上限)

jdk7与jdk8的区别

​		![](.\png\jdk7与jdk8方法区中的区别.jpg)

而到了JDK1.8，终于完全废弃了永久代的概念，与用与JRockit、J9一样在本地内存中实现的元空间(Metaspace)用来代替

![](.\png\jdk8元空间.jpg)

元空间的本质和永久代类似，都是对JVM规范中方法区的实现，不过元空间与永久代最大的区别就是：**<font color=red>元空间不再虚拟机设置的内存中，而是使用本地内存</font>**

 永久代、元空间两者并不只是名字变了，内存结构也调整了

根据《Java虚拟机规范》的规定，如果方法区无法满足新的内存分配需求时，将抛出OOM

#### 3.5.4.设置方法区大小与OOM

​	方法区的大小不必是固定的，jvm可以根据应用的需要动态调整

**JDK7及以前：**

​		**-XX:PermSize     设置永久代初始值分配内存，默认是 20.7M**

​		**-XX:MaxPermSize 设置永久代最大可分配内存，32为机器默认是64M,64位机器82M**

​	当jvm加载类信息容量超过这个值，会宝异常OutOfMemoryError:PermGenspace

**JDK8及以后：**

​		 **-XX:MetaspaceSize   设置元空间的初始值大小** 默认是 21m

​		 **-XX:MaxMetaspaceSize  设置元空间最大内存** 默认是-1 没有限制

与永久代不同，如果不指定大小，默认情况下，虚拟机会耗尽所以的可以系统内存，如果元空间发生溢出，虚拟机抛出OutOfMemoryErro：Metaspace

-XX:MetaspaceSize   设置初始的元空间大小，对于一个64位的服务器端JVM来说，某默认的-XX:MetaspaceSize值位21M,这就是初始的高水位线，一旦触及这个水位线，Full GC将会被触发并卸载没用的类（即这些类对应的类加载器不再存活），然后这个高水位线将重置，新的高水位线的值取决与GC释放了多少元空间，如果释放空间不足，那么在不超MaxMetaspaceSize是，适应提供该值，如果释放空间过多，则适当的降低该值

如果初始值的高水位先设置低，上述的高水位线调整情况会发生很多次，通过垃圾会失去的日志可以观察到Full GC多次调用，为了避免频繁的GC 建议将 	 -XX:MetaspaceSize  设置相对较高的值

```java
/**
 * 设置方法区或元空间的大小
 *  参数
 *      jdk7方法区
 *         -XX:PermSize=50m -XX:MaxPermSize=60m
 *          -XX:PermSize     设置永久代初始值分配内存，默认是 20.7M
 *          -XX:MaxPermSize 设置永久代最大可分配内存，32为机器默认是64M,64位机器82M
 *      jdk8元空间
 *            -XX:MetaspaceSize=50m -XX:MaxMetaspaceSize=60m
 *            -XX:MetaspaceSize   设置元空间的初始值大小 默认是 21m
 *            -XX:MaxMetaspaceSize  设置元空间最大内存 默认是-1 没有限制
 *       查看是否设置成功
 *              jps
 *              jinfo -flag 参数 进程ID
 * @author gpb
 * @date 2021/8/17 21:58
 */
public class MethodAreaDemo {

    public static void main(String[] args) {
        System.out.println("start...........");
        try {
            Thread.sleep(1000000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end......");
    }

}
```

**元空间OOM**

```java
/**
 * 元空间OOM测试
 * 参数
 *    -XX:MetaspaceSize=10m -XX:MaxMetaspaceSize=10m
 * @author gpb
 * @date 2021/8/17 22:10
 */
public class MethodAreaOOM extends ClassLoader{

    public static void main(String[] args) {
        int i = 0;
        try {
            MethodAreaOOM methodAreaOOM = new MethodAreaOOM();
            while (true) {
                // 创建ClassWriter对象，用于生成类的二进制字节码
                ClassWriter classWriter = new ClassWriter(0);
                classWriter.visit(Opcodes.V1_8, // 指定版本号
                        Opcodes.ACC_PUBLIC, // 权限修饰符
                        "Class" + i,    // 类名
                        null,           // 包名
                        "java/lang/Object" // 父类
                        ,null);   // 接口
                // 返回byte[]
                byte[] bytes = classWriter.toByteArray();
                // 类加载
                methodAreaOOM.defineClass("Class" + i,bytes,0,bytes.length);// Class对象
                i++;
            }

        }finally {
            System.out.println(i);
        }
    }
    /*
    * 3331
    Exception in thread "main" java.lang.OutOfMemoryError: Compressed class space
        at java.lang.ClassLoader.defineClass1(Native Method)
        at java.lang.ClassLoader.defineClass(ClassLoader.java:756)
        at java.lang.ClassLoader.defineClass(ClassLoader.java:635)
        at com.bo.jvm.methodArea.MethodAreaOOM.main(MethodAreaOOM.java:31)
    * 
    * */
}
```

**如何解决这些OOM?**

​	1.要解决OOM异常或heap space的异常，一般的手段是首先通过内存映像分析工具，(如 Eclipse Memory Analyzer)对dump 出来的堆转储快照进行分析，重点是确认内存中的对象是否是必要的，也就是要先分清楚到底是出现了内存泄漏（Memory Leak) 还是内存溢出（Memory Overflow）

​	2.如果是内存泄漏，可进一步通过工具查看泄漏对象的GC Roots的引用链，于是就能过找到泄漏对象是通过怎样的路径与GC Roots 相关联并导致垃圾收集器无法自动回收它们的，掌握了泄漏对象的类型信息，以及GC Roots 引用链的信息，就可以比较准确的定位出泄漏代码的位置

​	3.如果不存在内存泄漏，换句话就是内存中的对象确实都还必须存活着，那就应当简称虚拟机的堆参数（-Xms 和 -Xmx），与机器物理内存对比看是否还可以调大，比代码上检查是否存在某些对象生命周期过长，持有状态时间过长的情况，尝试减少程序运行期间的内存消耗

#### 3.5.5.元空间||方法区的结构

![](.\png\方法区结构.jpg)

《深入理解Java虚拟机》书中对方法区(Method Area)存储结构描述如下

​		它用于存储已被虚拟机加载的**类型信息,常量，静态变量，JIT编译器编译后的代码缓存等**

![](.\png\方法区存储的东西.jpg)

##### **3.5.5.1类型信息：**

​		 	对每个加载的类型（class类，接口interface，枚举enum，注解annotation），JVM必须在方法区中存储一下类型信息：

​			1.这个类型的完整有效的名称（全名=包名.类名）

​			2.这个类型直接父类的完整有效名称（对于interface或是Java.lang.Object都没有父类）

​			3.这个类的修饰符（public abstract，final的某个子集）

​			4.这个类型直接接口的一个有序列表

##### **3.5.5.2域（field）信息（属性）**

​			jvm必须在方法区中保存类型的所以域的相关信息以及域的声明顺序

​			域的相关信息包括：域名称，域类型，域修饰符（private public protected，static，final，volatile，transient）

##### **3.5.5.3方法信息（method）**

​			jvm必须保存所以方法的信息，同域信息一样包括声明顺序

​			方法名

​			方法的返回值类型或（void）

​			方法参数的数量和类型（按照顺序）

​			方法的修饰符（public private protected static final synchronized native abstract）

​			方法的字节码（bytecodes）、操作数栈】局部变量表及大小（abstract和native修饰外）

​			异常表（abstract和native修饰外）

​						每个异常处理的开始位置、结束位置】代码处理在程序计数器中的偏移地址、被捕获的异常类的常量池索引

##### **3.5.5.4non-final的类变量**

  静态变量和类关联在一起，随着类的加载而加载，它们成为类数据在逻辑上的一部分

  类变量被父类的所有实例共享，即使没有类实例时你也可以访问到他

```java
/**
 * non-final的类变量
 * @author gpb
 * @date 2021/8/18 21:17
 */
public class MethodAreaTest {

    public static void main(String[] args) {
        Order order = null;
        order.hello();
        System.out.println(order.count);
        /*
        输出
        * hello!
          1
        * */
    }

}
class Order{
    public static int count = 1;
    public static final int number = 2;

    public static void hello(){
        System.out.println("hello!");
    }
}
```

##### **3.5.5.5全局常量：static final**

**被声明为final的类变量的处理方法则不同，每个全局常量在编译的时候就会被分配了**

![](.\png\属性信息.png)

##### 3.5.5.6运行时常量池  vs 常量池

​	方法区，内部包含了运行时常量池

​	字节码，内部包含了常量池

​	要弄清楚方法区，需要理解清楚Class File，因为加载类的信息都在方法区

​	要弄清楚方法区，需要理解清楚ClassFile中的常量池

**字节码常量池：**

​				![](.\png\字节码常量池.png)



![](.\png\class常量池.jpg)



一个有效的字节码文件中除了包含类的版本信息、字段、方法以及接口等描述信息外，还包含一项信息那就是常量池表（Constant Pool Table），包括各种字面量和对类型、域、方法的符号引用

一个Java源文件中的类、接口，编译后产生一个字节码文件，而Java中的字节吗需要数据支持，通过这种数据会很大以至于不能直接存到字节码里，换另一种方式，可以存到常量池这个字节码包含了指向常量池的引用，在动态链接的时候会用到运行时常量池

```java
public class SimpeClass{
    public void sayHello(){
        System.out.println("hello!");
    }
}
```

这里使用了String，System，PrintStream及Object等结构，这里的代码其实已经小了，如果代码多，引用的结构会更多  这里就需要常量池了

**常量池中有什么？**

​		几种在常量池内存存储的数值类型包含：

​					数量值

​					字符串值

​					类引用

​					字段引用

​					方法引用

```java
public class Pool{
    public static void main(String[] args){
        Object obj = new Object();
    }
}
```

​        Object obj = new Object();

字节指令

0:		new #2			// Class java/lang/Object

1:		dup

3:		invokespecial #3	// Method  java/  lang/Object "<init>"() V



小结：

​	常量池，可以看作时一张表，虚拟机指令根据这张常量表找到要执行的类名、方法名、参数类型、字面量等

**运行时常量池**

​		1.运行时常量池（Runtime  Constant Pool）是方法区一部分

​		2.常量池（Constant Pool Table）是class文件的一部分，**用于存放编译期生成的各种字面量域符号引用，这部分内容将在类加载后存放到运行时常量池**

​		3.运行时常量池，在加载类和接口到虚拟机后，就会创建对应的运行时常量池

​		4.JVM为每个已加载的类型（类和接口）都维护一个常量池，池中的数据项像数组项一样，是通过索引访问的

​		5.运行时常量池中包含不同的常量，包括编译期就已经明确的数值字面量，也包括到运行期后才能获得方法或者字段引用，此时不再时常量池中的符号地址了，者里换位真实地址

​				运行时常量池，相对与Class文件常量池的另一重要的特征时：具备动态性

​		6.运行时常量池类似与传统编程语言中的符号表（symbol table）但是她所包含的数据却比符号表更加丰富

​		7.当创建类或接口的运行常量池时，如果构造运行时常量池所需的内存空间超过了方法区所能提供的最大值，就会包OOM

##### 3.5.5.7方法区的演进

首先明确: 只有HotSpot才有永久代

HotSpot中方法区的变化

| jdk1.6**及以前** | 有永久代（permanent generation）,静态变量存放在永久代上      |
| ---------------- | ------------------------------------------------------------ |
| **jdk1.7**       | **有永久代，但是已经有想 去除 永久代，字符串常量池，静态变量移除，保存到堆上** |
| **jdk1.8及以后** | **无永久代，类型信息，字段，方法，常量保存在本地内存的元空间中，但是字符串常量池，静态变量还是在堆上** |

jdk6:

​	![](.\png\jdk6方法区.jpg)

jdk7:

​	![](.\png\jdk7方法区.jpg)

jdk8:

​	![](.\png\jdk8方法区.jpg)



**jdk7以前方法区都是用的虚拟机的内存，jdk8以后是用的物理内存**



永久代为什么要被元空间替换？

​		随着Java的到来，HotSpot VM中在也到永久代了，但是这并不意味者类的元数据信息也消失了**，这些数据被移到一个与堆不相续的本地内存区域，这个区域叫做 元空间**

用于类的元数据分配在本地内存中，元空间的最大可分配空间就是系统可用内存空间

**这项改动很有必要的，原因有：**

​			为永久代设置空间大小是很难确定的

​			某些场景下，如果动态加载类过多，容易产生Perm区的OOM,比如某个实际Web工程中，因为功能点比较多，在运行过程中，要不断的动态加载很多个类，经常出现致命的错误 OOM

​		**而元空间和永久代之间最大区别在于：元空间并在虚拟机中，而是用的是本地内存，因此，默认情况下，元空间的大小仅受本地内存限制**

​		**对于永久代调优很困难**

##### **<font color=red>3.5.5.8.StringTable为什么要调整？</font>**

​		jdk7中将StringTable调整到堆空间中，因为永久代的回收效率很低，在full gc的时候才被触发，而full  gc是老年代的空间不足，永久代不足的时候才会触发的

​	这就导致StringTable回收效率不高。而我们开发中经常会有大量的字符串被创建，回收效率低，导致永久代内存不足，放到堆里，能及时的回收内存

##### 3.5.5.9.静态常量的位置

​		public static  Object  o  = new Object();

​      new Object();  在堆中

​	   o   jdk6及以前在方法区中

​	   o   jdk，8 以后位置代堆中





##### 3.5.5.10.方法区的垃圾回收

**方法区的垃圾收集主要回收的两部分内容：常量池中废弃的常量，不再使用的类型**

方法区内常量池支中主要存放的两个类常量：字面量和符号引用，字面量比较接近Java语言层次的常量概念，如文本字符串，被声明为final的常量值等，而符号引用则属于编译原理方法的概念，包括下三类常量：

​						类和接口的全限定名

​						字段的名称和描述

​						方法的名称和描述符

hotspot虚拟机堆常量池的回收策略很明确的，只要常量池中的常量没有被任何地方引用，就可以回收

回收废弃常量与回收Java堆中的对象非常类似



判定一个常量是否 废弃 还是相对简单，但是判断一个类是否属于 不再使用得类，条件非常的苛刻需要满足一下几点：

​			1.该类的所有的实例都已经被回收，也就是Java堆中不存在该类及其任何的派生子类的实例

​			2.加载该类的类加载器已经被回收，这个条件除非是经过精心设计的可替换类加载器的场景，如OSGI JSP的重加载等，否则通常是很难达成的

​			3.该类对的java.lang.class对象没有任何定法被引用，无法在任何地方通过反射访问该类的方法

Java虚拟机被允许堆满足上述三个条件的无用类进行回收，这里说的仅仅是，被允许，而并不是和对象一样，没有了引用就可以进行回收，关于是否要堆类型进行回收，Hotspot虚拟机提供了 **-Xnoclassgc进行控制，还可以使用 -verbose:class  以及 -XX:+TraceClass-loading    -XX:+TraceClassUnLoading 查看类加载和卸载的信息**

在大量使用反射，动态代理，CGLib等字节码框架，动态生成JSP以及OSGi这类频繁自定义类加载完的场景中，通常都需要Java虚拟机具备类型卸载的能力，以保证不会对方法区造成大的内存压力

##### 3.5.5.11.总结

​	![](.\png\栈堆方法区运行关系.jpg)



## 4.对象的内存布局

### 4.1直接内存

​	不是虚拟机运行时数据区的一部分，也不是《Java虚拟机规范》中定义的内存区域

​	直接内存实在Java堆外的、直接向系统申请的内存区间

​	来源与NIO,通过存在堆中的DirectByteBuffer操作Native内存

​	通常，访问直接内存的速度会优于Java堆，即读写性能高

​				因此处于性能考虑，读写频繁的场合可能会考虑使用直接内存

​				Java的NIO库允许Java程序使用直接内存，用与数据缓冲区



非直接缓冲区

​		读写文件，需要与磁盘交互，需要又用户态切换到内核态，在内核态时，需要内存如图

![](G:\study\jvm\png\非直接缓存区.jpg)



这里需要两份内存存储重复数据，效率低



直接缓冲区

​	使用NIO时，如图

![](G:\study\jvm\png\直接缓冲区.jpg)



操作系统划出的直接缓存区可以被Java代码直接访问只有一份，NIO适合堆大文件的读写操作

也可能导致OutOfMemoryError异常

由于直接内存在Java堆外，一i那次他的大小不会直接所限于 -Xmx指定的最大堆大小，但是系统内存时有限的，Java堆和直接内存的综合依然受限与操做系统能给出的最大内存

缺点：

​			分配回收成较高

​			不受JVM内存回收管理

直接内存大小可以通过MaxDirectMemorySize设置

如果不指定，默认与对的最大值-Xmx参数值一致

**XX:MaxDirectMemorySize=20m 设置直接内存最大值**

直接内存代码

```java
/**
 * 直接内存测试 nio效率
 * @author gpb
 * @date 2021/8/22 19:57
 */
public class NioBuffer {

    private static final String TO = "文件地址";
    private static final int _100Mb = 1024 * 1024 * 100;

    public static void main(String[] args) {
        long sum = 0;
        String src = "文件地址";
        for (int i = 0; i < 3; i++) {
            String dest = "文件地址" + i +"文件格式";
            sum += io(src,dest);
            sum += directBuffer(src,dest);
        }
    }

    private static long directBuffer(String src, String dest) {
        long start = System.currentTimeMillis();

        FileChannel inChannel = null;
        FileChannel outChannel = null;
        try {
            inChannel = new FileInputStream(src).getChannel();
            outChannel = new FileOutputStream(dest).getChannel();

            ByteBuffer byteBuffer = ByteBuffer.allocateDirect(_100Mb);
            while (inChannel.read(byteBuffer) != -1) {
                // 修改为读数据模式
                byteBuffer.flip();
                outChannel.write(byteBuffer);
                // 清空
                byteBuffer.clear();
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(inChannel !=null){
                try {
                    inChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(outChannel != null){
                try {
                    outChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return start - System.currentTimeMillis();
    }

    private static long io(String src, String dest) {
        long start = System.currentTimeMillis();

        FileInputStream fis = null;
        FileOutputStream fos = null;
        try{
            fis = new FileInputStream(src);
            fos = new FileOutputStream(dest);
            byte[] buffer = new byte[_100Mb];
            while (true) {
                int len = fis.read(buffer);
                if (len == -1){
                    break;
                }
                fos.write(buffer,0,len);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(fis !=null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(fos != null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return start - System.currentTimeMillis();
    }

}
```

直接内存OOM

```java
/**
*
 *  直接内存OOM测试
 * Exception in thread "main" java.lang.OutOfMemoryError: Direct buffer memory
*  @author gpb
* @date 2021/8/22 20:30
*/
public class BufferOOM {
    private static final int BUFFER = 1024 * 1024 * 20; // 20MB

    public static void main(String[] args) {
        ArrayList<ByteBuffer> byteBuffers = new ArrayList<>();
        int count = 0;
        try{
            while (true) {
                ByteBuffer byteBuffer = ByteBuffer.allocateDirect(BUFFER);
                byteBuffers.add(byteBuffer);
                count ++;
                try{
                    Thread.sleep(100);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            System.out.println(count);
        }
    }

}
```

![](.\png\java_process_memory.jpg)

**java process memory = java heap + natice memory**

### 4.2.对象的实例化

https://www.processon.com/mindmap/611e677e7d9c0834aa5f03f2

## 5.执行引擎

执行引擎是Java虚拟机核心组件部分之一

虚拟机时一个相对于物理机的概念，这两种机器都有代码执行能力，其区别时物理机的执行引擎是直接建立在处理器、缓存、指令集和操作系统层面上的，而<font color=red>虚拟机的执行引擎是由软件自行实现的，</font>因为可以不受物理条件制约的定制指令集与执行引擎的结构体系，<font color=red>能够执行那些不被硬件直接支持的指令集格式</font>

JVM的主要任务是负责装载字节码到其内部，但字节码并不能够直接运行在操作系统之上，因为字节码指令并非等价与本地机器指令，它内部包含的仅仅是一些能够被JVM所识别的字节码指令，符号表，以及他辅助信息

那么，如果想要让一个Java程序运行起来，执行引擎的任务就是<font color=red>将字节码指令解释、编译为对应平台上的本地机器指令才可以</font>。简单来说，JVM中的执行引擎充当了将高级语言翻译为机器语言的译者



![](.\png\执行引擎工作.png)



执行引擎在执行过程中究竟需要执行什么样子的自家吗指令完全依赖于PC寄存器

每当执行完一项指令操作后，pc寄存器就会更新下一条需要被执行的指令地址

当然方法在执行的过程中，执行引擎由可能会通过存储在局部变量表中的对象引用准确定为到存储在Java堆区中对象实例信息，以及通过对象头中的元数据指针定位到目标对象的类型信息

从外观上来看，所以的Java虚拟机的执行引擎输入、输出都是一致的：输入的是字节码二进制流，处理过程是字节码解析执行的等效过程，输出的是执行结果

### 5.1.java代码编译和执行的过程

![](.\png\java代码编译执行过程.jpg)

大部分的程序代码转换成物理机的目标代码或虚拟机能执行的之指令集之前，都需要经过上图中的步骤

**什么是解释器(Interpreter)?  什么是JIT编译器？**

​	解释器：当Java虚拟机启动时会根据预定义的规范<font color=red>对字节码采用逐行解释的方式执行</font>，将每条字节码文件中的内容 翻译 为对应平台的本地机器指令执行

​	JIT(Just In Time Compiler)编译器：就是虚拟机将源代码直接编译成和本地机器平台相关的机器语言

**为啥说Java时半编译半解释性语言？**

​		JDK1.0时代，将Java语言定位 解释执行，还是比较准确的，再后来，Java也发展出可以直接生成本地代码的编译器

​		现在JVM执行Java代码的时候，通常都会将解释执行与编译执行二者结合起来进行

## .本地方法

简单的来说，一个被native method就是Java调用非Java代码写的接口，一个native method是这样一个Java方法；该方法的实现由非Java语言实现的，比如C,这个特征并非Java所特有，很多其他的编程语言都有这一机制，比如在C++ 中，你可以用extern "C" 告知C++编译器去调用C的函数

定义一个native method时,并不需要提供实现体，因为实现体是由非Java语言实现的

本地接口的作用是融合不同的编程语言为Java所用，他的初衷是融合 C C++语言

## .常用调优工具

​		JDK命令行

​		Eclipse：Memory Analyzer Tool

​		Jconsole

​		VisualVM

​		Jprofiler

​		Java Flight Recorder

​		GCViewer

​		GC Easy