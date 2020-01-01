package concurrent.visiblity;

/**
 * @author qiaoyihan
 * @date 2019/12/31
 */
public class DCL {
    private static volatile DCL instance = null;

    private DCL() {}

    public static DCL getInstance() {
        if (instance == null) {
            synchronized (DCL.class) {
                if (instance == null) {
                    return new DCL();
                }
            }
        }
        return instance;
    }
}
