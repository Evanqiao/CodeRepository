工厂方法模式的通用代码：
在工厂方法模式中，抽象产品类Product负责定义产品的共性，实现对事物最抽象的定义；Creator为抽象创建类，也就是抽象工厂，具体如何创建产品类是由具体的实现工厂ConcreteCreator完成的。
```java
public abstract class Product {
    //产品类的公共方法
    public void method1() {
        //业务逻辑处理
    }
    //抽象方法
    public abstract void method2();
}

public class ConcreteProduct1 extends Product {
    @Override
    public void method2() {
        //业务逻辑处理
        }
}

public class ConcreteProduct2 extends Product {
    @Override
    public void method2() {
        //业务逻辑处理
    }
}
```
```java
public abstract class Creator {
    /** 
      * 创建一个产品对象，其输入参数类型可以自行设置
      * 通常为String、Enum、Class等，当然也可以为空
      */
    public abstract <T extends Product> T createProduct(Class<T> c);
}

public class ConcreteCreator extends Creator {
    @Override
    public <T extends Product> T createProduct(Class<T> c) {
        Product product=null;
        try {
            product = (Product)Class.forName(c.getName()).newInstance();
        } catch (Exception e) {
            //异常处理
        }
        return (T)product;
    }
}
```
```java
public class Client {
    public static void main(String[] args) {
        Creator creator = new ConcreteCreator();
        Product product = creator.createProduct(ConcreteProduct1.class);
        /** 继续业务处理*/
    }
}
```