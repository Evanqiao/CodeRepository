package spring.di.knight;

import java.io.PrintStream;

/**
 * @author qiaoyihan
 * @date 2020/5/6
 */
public class SlayDragonQuest implements Quest {

    private PrintStream stream;

    public SlayDragonQuest(PrintStream stream) {
        this.stream = stream;
    }


    @Override
    public void embark() {
        stream.println("Embarking on quest to slay the dragon!");
    }
}
