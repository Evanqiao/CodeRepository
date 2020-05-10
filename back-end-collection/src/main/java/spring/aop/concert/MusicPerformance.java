package spring.aop.concert;

import org.springframework.stereotype.Component;

/**
 * @author qiaoyihan
 * @date 2020/5/9
 */
@Component
public class MusicPerformance implements Performance {
    @Override
    public void perform() {
        System.out.println("music performance ......");
    }
}
