package spring.di.soundsystem.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author qiaoyihan
 * @date 2020/5/7
 */
@Configuration
@Import({CDPlayerConfig.class, CompactDiscConfig.class})
public class SoundSystemConfig {
}
