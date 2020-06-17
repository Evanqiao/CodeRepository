package spring.mvc.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author qiaoyihan
 * @date 2020/5/13
 */
@Configuration
@ComponentScan(
        basePackages = {"spring.mvc"},
        excludeFilters =
                @ComponentScan.Filter(type = FilterType.ANNOTATION, value = EnableWebMvc.class))
public class RootConfig {}
