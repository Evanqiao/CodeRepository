Subject 抽象主题角色
RealSubject 具体主题角色
Proxy 代理主题角色


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