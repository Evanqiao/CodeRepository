package netty.socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * @author qiaoyihan
 * @date 2020/6/9
 */
public class Client {
    public static void main(String[] args) throws IOException {
        // 连接指定服务器和端口
        Socket sock = new Socket("localhost", 6666);
        try (InputStream input = sock.getInputStream();
                OutputStream output = sock.getOutputStream()) {
            handle(input, output);
        }
        sock.close();
        System.out.println("disconnected.");
    }

    private static void handle(InputStream input, OutputStream output) throws IOException {
        BufferedWriter writer =
                new BufferedWriter(new OutputStreamWriter(output, StandardCharsets.UTF_8));
        BufferedReader reader =
                new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8));
        Scanner scanner = new Scanner(System.in);
        System.out.println("[server] " + reader.readLine());
        for (; ; ) {
            // 打印提示
            System.out.print(">>> ");
            // 读取一行输入
            String s = scanner.nextLine();
            writer.write(s);
            writer.newLine();
            writer.flush();
            String resp = reader.readLine();
            System.out.println("<<< " + resp);
            if ("bye".equals(resp)) {
                break;
            }
        }
    }
}
