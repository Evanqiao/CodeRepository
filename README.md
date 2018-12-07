CodeRepository这个项目主要用来建立常见的数据结构代码、算法代码和并行、网络等代码库，方便后面开发的重复使用。

## mvc demo

stock-stock是一个mvc的例子，主要分为以下几层：

- model层：处理业务逻辑。与dal层交互进行数据的读写，与controller层交互处理用户的请求；
- controller层：接受来自view层的请求，并返回相应的数据；
- view层：用户交互。分为两种方式，GUI 和 命令行模式。
- dal层：处理数据的读写，对model层提供接口，实现方式可扩展（文件存储或数据库存储），对外层隐蔽具体实现方式。

stock-stock依赖的jar包：
- gson-2.8.5.jar
- guava-25.1-jre.jar
- hamcrest-core-1.3.jar
- junit-4.12.jar
- lombok-1.18.2.jar
- spring-core-5.1.0.RELEASE.jar

### lombok 使用
除了依赖jar包外，使用lombok需要安装插件。

![lombok插件](https://github.com/Evanqiao/CodeRepository/blob/master/res/lombok.png)

如果使用中，报错找不到符号，勾选下面的按钮：

![lombok错误解决](https://github.com/Evanqiao/CodeRepository/blob/master/res/anno.png)
