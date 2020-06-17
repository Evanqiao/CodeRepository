package io.bio;

import java.net.Socket;
import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author qiaoyihan
 * @date 2020/5/28
 */
public class BioClient {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(20);
        for (int i = 0; i < 20; i++) {
            int finalI = i;
            executor.execute(() -> process(finalI));
        }
    }

    private static void process(int num) {
        try {
            Socket socket = new Socket("127.0.0.1", 3333);
            for (int i = 0; i < 5; i++) {
                socket.getOutputStream()
                        .write(
                                ("客户端 " + num + " -- " + LocalDateTime.now() + ": Hello.")
                                        .getBytes());
                socket.getOutputStream().flush();
                Thread.sleep(1000);
            }
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
