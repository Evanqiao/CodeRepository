package utils;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author qiaoyihan
 * @date 2018-12-06
 */
public class InvestUtil {

    public boolean validTimeForTransaction(LocalDateTime timeStamp) {
        return timeStamp.getHour() >= 9 && timeStamp.getHour() < 16;
    }

    public boolean isBeforeDate(String d1, LocalDate d2) {
        LocalDate date = LocalDate.parse(d1);
        return date.compareTo(d2) <= 0;
    }

    private boolean isBeforeDate(LocalDate d1, LocalDate d2) {
        return d1.compareTo(d2) <= 0;
    }
}
