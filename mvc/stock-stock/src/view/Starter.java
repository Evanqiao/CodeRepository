package view;

import command.Buy;
import command.Create;
import command.MainMenu;
import utils.InvestUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;


public class Starter {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        IView m = new ConsoleViewImpl();

        Map<String, Function<Scanner, ConsoleCommand>> knownCommands = new HashMap<>();
        knownCommands.put("menu", s -> new MainMenu());
        knownCommands.put("buy",s -> new Buy(s.next(), s.next(), s.nextDouble(), s.next(), s.nextDouble()));
        knownCommands.put("create", s -> new Create(s.next()));


        // 显示说明文字
        System.out.println(InvestUtil.getIllustrativeInfo());
        while(scan.hasNext()) {
            ConsoleCommand c;
            String in = scan.next();
            if (in.equalsIgnoreCase("q") || in.equalsIgnoreCase("quit")) {
                return;
            }
            Function<Scanner, ConsoleCommand> cmd = knownCommands.getOrDefault(in, null);
            if (cmd == null) {
                throw new IllegalArgumentException();
            } else {
                c = cmd.apply(scan);
                c.go(m);
            }
        }
    }

}
