package utils;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author qiaoyihan
 * @date 2018-12-06
 */
public class InvestUtil {

    public boolean validTimeForTransaction(LocalDateTime timeStamp) {
        return timeStamp.getHour() >= 9 && timeStamp.getHour() < 16;
    }

    public boolean isBeforeDate(String d1, LocalDate d2) {
        LocalDate date = LocalDate.parse(d1);
        return date.compareTo(d2) <= 0;
    }

    private boolean isBeforeDate(LocalDate d1, LocalDate d2) {
        return d1.compareTo(d2) <= 0;
    }

    public static String getIllustrativeInfo() {
        return  "0. 回到主菜单. 命令：menu\n" +
                "1. 创建理财包. e.g. create <理财包名称>\n" +
                "2. 购买指定名字，指定日期，一定量的股票到指定理财包中. e.g. buy <股票名字> <购买日期(eg.2017-10-03T10:15:30)> <数量> <理财包名称> <手续费>\n" +
                "3. 获取所有理财包的信息. e.g. getAllPor\n" +
                "4. 获取指定名字的理财包的信息. e.g. examPor <理财包名字>\n" +
                "5. 获取指定理财包在指定日期前的投资成本. e.g. cost <理财包名字> <日期(eg. 2017-10-03)>\n" +
                "6. 获取指定理财包在指定日期的价值. e.g. value <理财包名字> <日期(eg. 2017-10-03)>\n" +
                "7. 获取指定理财包的所有交易记录. e.g. allTran <理财包名字>";
    }
}
