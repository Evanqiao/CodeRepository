package io.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author qiaoyihan
 * @date 2020/5/30
 */
public class BioServer {
    public static void main(String[] args) throws IOException {
        ExecutorService executor = Executors.newFixedThreadPool(1);

        ServerSocket serverSocket = new ServerSocket(3333);
        while (true) {
            Socket socket = serverSocket.accept();
            executor.execute(() -> process(socket));
        }
    }

    private static void process(Socket socket) {
        int len;
        byte[] bytes = new byte[1024];
        try {
            InputStream inputStream = socket.getInputStream();
            while ((len = inputStream.read(bytes)) != -1) {
                System.out.println(new String(bytes, 0, len));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
