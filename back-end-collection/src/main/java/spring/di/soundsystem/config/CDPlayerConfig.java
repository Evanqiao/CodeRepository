package spring.di.soundsystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.di.soundsystem.CDPlayer;
import spring.di.soundsystem.CompactDisc;

/**
 * @author qiaoyihan
 * @date 2020/5/6
 */
@Configuration
public class CDPlayerConfig {
    @Bean
    public CDPlayer cdPlayer(CompactDisc cd) {
        return new CDPlayer(cd);
    }

}
