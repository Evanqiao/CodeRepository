package io.nio;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author qiaoyihan
 * @date 2020/5/30
 */
public class NioServer extends Thread {
    @Override
    public void run() {
        // 创建Selector和Channel
        try (Selector selector = Selector.open();
                ServerSocketChannel serverSocket =
                        ServerSocketChannel.open()) {
            serverSocket.bind(new InetSocketAddress(InetAddress.getLocalHost(), 3333));
            serverSocket.configureBlocking(false);
            // 注册到Selector，并说明关注点
            serverSocket.register(selector, SelectionKey.OP_ACCEPT);
            while (true) {
                selector.select(); // 阻塞等待就绪的Channel，这是关键点之一
                Set<SelectionKey> selectedKeys = selector.selectedKeys();
                Iterator<SelectionKey> iter = selectedKeys.iterator();
                while (iter.hasNext()) {
                    SelectionKey key = iter.next();
                    // 生产系统中一般会额外进行就绪状态检查
                    handleRead((ServerSocketChannel) key.channel());
                    iter.remove();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleRead(ServerSocketChannel server) throws IOException {
        try (SocketChannel client = server.accept()) {
            ByteBuffer buffer = ByteBuffer.allocate(1000);
            int n = client.read(buffer);
            while (n != -1) {
                System.out.println(new String(buffer.array(), 0, n));
                n = client.read(buffer);
            }
            buffer.clear();
        }
    }

    public static void main(String[] args) {
        NioServer server = new NioServer();
        server.start();
    }
}
