package spring.di.soundsystem;

import com.google.common.collect.Maps;
import java.util.Map;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

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
