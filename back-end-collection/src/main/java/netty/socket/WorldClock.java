package netty.socket;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDateTime;

/**
 * @author qiaoyihan
 * @date 2020/6/9
 */
public interface WorldClock extends Remote {
    LocalDateTime getLocalDateTime(String zoneId) throws RemoteException;
}
