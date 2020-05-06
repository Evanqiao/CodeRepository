package spring.di.knight;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author qiaoyihan
 * @date 2020/5/6
 */
public class KnightMain {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("knight.xml");
        Knight knight = context.getBean(Knight.class);
        knight.embarkOnQuest();
        context.close();
    }
}
