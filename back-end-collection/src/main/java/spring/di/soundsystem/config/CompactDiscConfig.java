package spring.di.soundsystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.di.soundsystem.CompactDisc;
import spring.di.soundsystem.SgtPeppers;

/**
 * @author qiaoyihan
 * @date 2020/5/6
 */
@Configuration
public class CompactDiscConfig {
    @Bean(name = "lonelyHeartsClubBand")
    public CompactDisc sgtPeppers() {
        return new SgtPeppers();
    }
}
