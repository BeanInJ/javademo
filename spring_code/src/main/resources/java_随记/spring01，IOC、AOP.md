1、MVC设计模式中的MVC分别指什么？
M ：model（模型）
V ：view（视图）
C ：controller（控制器）

2、mybatis的两级缓存分别指的什么？
一级缓存：SQLSession，默认开启一级缓存
二级缓存：是namespace级别的,在SqlSessionFactory层面给各个SqlSession 对象共享。默认不开启

3、scope="singleton" 和 scope="prototype"有何区别？
scope="singleton"是单例模式，Spring IOC容器中只会存在一个共享的bean实例。
scope="protoype"是原型模式，每次请求都会产生一个新的实例。

4、控制反转？
将对象初始化的控制权交给spring，由spring来控制，就叫控制反转。
控制反转常见的两种方式：依赖注入（DI）、依赖查找（DL，现在基本被弃用了）
控制反转的核心思想：简化开发

5、IOC容器
用来存放java bean的
IOC是Inversion of Control的缩写，翻译成“控制反转”。
具体如何反转了？获得依赖对象的过程被反转了。
获得依赖对象的过程由自身管理变为了由IOC容器主动注入。



6、web容器
用来存放servlet的

7、spring依赖注入bean的两个注解
@autowrite A a;           // 自动把接口A的 实现类、或类A 注入进来
@Resource("aaa") B b;     // 把IOC类中的id为aaa的对象自动注入进来

8、spring的注入方式
1、通过setter
2、通过构造方法
3、通过强制赋值

9、面向切面（AOP）
面向切面编程是一种编程思想，它将影响多个类的行为封装到可重用的模块中。
AOP核心思想：解耦
AOP和IOC是补充性技术。
aop的功能完全集成到了：spring事物管理、日志、其他各种特性的上下文中
权限认证、日志、事物、懒加载、上下文处理、错误跟踪（异常捕捉）、缓存处理
