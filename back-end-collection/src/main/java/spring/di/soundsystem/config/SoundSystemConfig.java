package spring.di.soundsystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import spring.di.soundsystem.TrackCounter;

/**
 * @author qiaoyihan
 * @date 2020/5/7
 */
@Configuration
@EnableAspectJAutoProxy
@Import({CDPlayerConfig.class, CompactDiscConfig.class})
public class SoundSystemConfig {
    @Bean
    public TrackCounter trackCounter() {
        return new TrackCounter();
    }
}
