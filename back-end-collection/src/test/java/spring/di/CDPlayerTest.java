package spring.di;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import spring.di.soundsystem.CompactDisc;
import spring.di.soundsystem.MediaPlayer;
import spring.di.soundsystem.config.SoundSystemConfig;

import static org.junit.Assert.assertNotNull;

/**
 * @author qiaoyihan
 * @date 2020/5/6
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SoundSystemConfig.class)
public class CDPlayerTest {
    @Autowired private CompactDisc cd;
    @Autowired private MediaPlayer mediaPlayer;


    @Test
    public void cdShouldNotBeNull() {
        assertNotNull(cd);
    }

    @Test
    public void testPlay() {
        mediaPlayer.play();
    }
}
