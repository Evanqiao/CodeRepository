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

### 运行方法
main函数在view.Starter.main下，运行main函数即可。也可以生成jar包，运行 java -jar stock-stock.jar

运行效果：

0. 回到主菜单. 命令：menu   
1. 创建理财包. e.g. create <理财包名称>  
2. 购买指定名字，指定日期，一定量的股票到指定理财包中. e.g. buy <股票名字> <购买日期(eg.2017-10-03T10:15:30)> <数量> <理财包名称> <手续费>  
3. 获取所有理财包的信息. e.g. getAllPor  
4. 获取指定名字的理财包的信息. e.g. examPor <理财包名字>  
5. 获取指定理财包在指定日期前的投资成本. e.g. cost <理财包名字> <日期(eg. 2017-10-03)>  
6. 获取指定理财包在指定日期的价值. e.g. value <理财包名字> <日期(eg. 2017-10-03)>  
7. 获取指定理财包的所有交易记录. e.g. allTran <理财包名字>   
getAllPor  
[PortfolioDTO(name=QQQ, stockDTOS=[StockDTO(name=GOOG, totalVolume=50.0)]), PortfolioDTO(name=qqq, stockDTOS=null)]  
buy MSFT 2017-11-21T12:23:00 100 qqq 1.2  
购买成功  
getAllPor  
[PortfolioDTO(name=QQQ, stockDTOS=[StockDTO(name=GOOG, totalVolume=50.0)]), PortfolioDTO(name=qqq, stockDTOS=[StockDTO(name=MSFT, totalVolume=100.0)])]
cost qqq 2018-12-12  
-8370.8  
value qqq 2017-12-01  
8426.0  
allTran qqq  
[TransactionDTO(portfolioName=qqq, stockName=MSFT, type=BUY, transactionDate=2017-11-21, price=83.72, volume=100.0, commissionFee=1.2)]  
q  