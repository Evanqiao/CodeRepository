package concurrent.threadlocal;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

/**
 * @author qiaoyihan
 * @date 2020/6/10
 */
public class SoftReferenceHouse {
    public static void main(String[] args) {
        List<SoftReference<House>> houses = new ArrayList<>();

        for (int i = 0; ; ) {
            SoftReference<House> buyer2 = new SoftReference<>(new House());
            houses.add(buyer2);
            System.out.println("i = " + (++i));
        }
    }
}

class House {
    private static final Integer DOOR_NUMBER = 2000;
    public Door[] doors = new Door[DOOR_NUMBER];

    class Door {}
}
