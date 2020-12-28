package spring.aop.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import spring.aop.concert.Performance;

/**
 * @author qiaoyihan
 * @date 2020/5/9
 */
@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackageClasses = {Performance.class})
public class ConcertConfig {}
