package netty.socket;

import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * @author qiaoyihan
 * @date 2020/6/9
 */
public class WorldClockService implements WorldClock {
    @Override
    public LocalDateTime getLocalDateTime(String zoneId) throws RemoteException {
        return LocalDateTime.now(ZoneId.of(zoneId)).withNano(0);
    }
}
