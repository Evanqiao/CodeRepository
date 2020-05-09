package spring.di.soundsystem;

import org.springframework.stereotype.Component;

/**
 * @author qiaoyihan
 * @date 2020/5/6
 */
@Component
public class SgtPeppers implements CompactDisc {

    private String title = "Sgt. Pepper's Lonely Hearts Club Band";
    private String artist = "The Beatles";

    @Override
    public void play() {
        System.out.println("Playing " + title + " by" + artist);
    }
}
