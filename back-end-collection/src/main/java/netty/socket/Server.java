package netty.socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * @author qiaoyihan
 * @date 2020/6/9
 */
public class Server {
    public static void main(String[] args) throws IOException {
        // 监听指定端口
        ServerSocket ss = new ServerSocket(6666);
        System.out.println("server is running...");
        for (; ; ) {
            Socket sock = ss.accept();
            System.out.println("connected from " + sock.getRemoteSocketAddress());
            Thread t = new Handler(sock);
            t.start();
        }
    }

    static class Handler extends Thread {
        Socket sock;

        public Handler(Socket sock) {
            this.sock = sock;
        }

        @Override
        public void run() {
            try (InputStream input = this.sock.getInputStream();
                    OutputStream output = this.sock.getOutputStream()) {
                handle(input, output);
            } catch (Exception e) {
                System.out.println("client disconnected.");
            } finally {
                try {
                    this.sock.close();
                } catch (IOException ignored) {
                }
            }
        }

        private void handle(InputStream input, OutputStream output) throws IOException {
            BufferedWriter writer =
                    new BufferedWriter(new OutputStreamWriter(output, StandardCharsets.UTF_8));
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8));
            writer.write("hello\n");
            writer.flush();
            for (; ; ) {
                String s = reader.readLine();
                if ("bye".equals(s)) {
                    writer.write("bye\n");
                    writer.flush();
                    break;
                }
                writer.write("ok: " + s + "\n");
                writer.flush();
            }
        }
    }
}
