Subject 抽象主题角色
RealSubject 具体主题角色
Proxy 代理主题角色

```java
public interface Subject {
    public void request();
}

public class RealSubject implements Subject {
    public void request() {
        // do something
        ...
    }
}


public class Proxy implements Subject {
    // 要代理哪个实现类
    private Subject subject = null;
    // 默认被代理者
    public Proxy() {

    }
    // 通过构造函数传递代理者
    public Proxy(Object...objects) {

    }
    // 实现接口定义中的方法
    public void request() {
        this.before();
        this.subject.request();
        this.after();
    }
    // 预处理
    private void before() {

    }
    // 善后处理
    private void after() {

    }
}
```

相关知识点：
[代理模式及Java实现动态代理](https://www.jianshu.com/p/6f6bb2f0ece9)
[Java Dynamic Proxy](https://b1ngz.github.io/java-dynamic-proxy/)
[What is the difference between JDK dynamic proxy and CGLib?](https://stackoverflow.com/questions/10664182/what-is-the-difference-between-jdk-dynamic-proxy-and-cglib/10664208#10664208)
In case of the Proxy Design Pattern, What is the difference between JDK's Dynamic Proxy and third party dynamic code generation API s such as CGLib?

What is the difference between using both the approaches and when should one prefer one over another?

JDK Dynamic proxy can only proxy by interface (so your target class needs to implement an interface, which is then also implemented by the proxy class).

CGLIB (and javassist) can create a proxy by subclassing. In this scenario the proxy becomes a subclass of the target class. No need for interfaces.

So Java Dynamic proxies can proxy: public class Foo implements iFoo where CGLIB can proxy: public class Foo

EDIT:

I should mention that because javassist and CGLIB use proxy by subclassing, that this is the reason you cannot declare final methods or make the class final when using frameworks that rely on this. That would stop these libraries from allowing to subclass your class and override your methods.