package spring.di.soundsystem;

import com.google.common.collect.Maps;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author qiaoyihan
 * @date 2020/5/10
 */
@Aspect
public class TrackCounter {

    private Map<Integer, Integer> trackCountMap = Maps.newHashMap();

    @Pointcut("execution(* spring.di.soundsystem.CompactDisc.playTrack(int)) && args(trackNo)")
    public void trackPlayed(int trackNo) {}

    @Before(value = "trackPlayed(trackNo)", argNames = "trackNo")
    public void countTrack(int trackNo) {
        int curCount = getCount(trackNo);
        trackCountMap.put(trackNo, curCount + 1);
    }

    public int getCount(int trackNo) {
        return trackCountMap.getOrDefault(trackNo, 0);
    }

}
