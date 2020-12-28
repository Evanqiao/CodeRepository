package algorithm.lru;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author qiaoyihan
 * @date 2020/6/25
 */
public class LRUCache {
    int capacity;
    private Map<Integer, Node> map;
    private Node head;
    private Node tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>(16);
    }

    public int get(int key) {
        if (Objects.isNull(map.get(key))) {
            return -1;
        }
        Node node = map.get(key);
        afterNodeAccess(node);
        return node.v;
    }

    public void put(int key, int value) {
        Node node = new Node();
        node.k = key;
        node.v = value;
        Node oldNode = map.put(key, node);
        if (oldNode != null) {
            remove(oldNode);
        }
        afterNodeInsertion(node);
    }

    private void afterNodeInsertion(Node node) {
        if (tail == head && tail == null) {
            head = node;
            tail = node;
            return;
        }
        node.prev = tail;
        tail.next = node;
        tail = node;
        if (map.size() > capacity) {
            map.remove(head.k);
            remove(head);
        }
    }

    private void afterNodeAccess(Node node) {
        if (node == tail) {
            return;
        }
        if (node == head) {
            head = node.next;
            head.prev = null;
        } else {
            // 链接node除去后产生的中断
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }
        // 移动node到tail
        Node oldTail = tail;
        tail = node;
        oldTail.next = tail;
        tail.prev = oldTail;
        tail.next = null;
    }

    public void print() {
        System.out.println("-------------------");
        System.out.println(map);
        Node p = head;
        while (p != null) {
            System.out.println(
                    p.k
                            + ":"
                            + p.v
                            + " "
                            + (p.prev == null ? "null" : p.prev.k)
                            + " "
                            + (p.next == null ? "null" : p.next.k));
            p = p.next;
        }
    }

    private void remove(Node node) {
        if (head == tail) {
            head = null;
            tail = null;
        } else if (head == node) {
            head = node.next;
            node.next.prev = null;
        } else if (tail == node) {
            tail = node.prev;
            node.prev.next = null;
        } else {
            // 链接node除去后产生的中断
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }
        node = null;
    }

    /** 默认头结点和尾结点都是实际的结点。如果链表为空，头尾都是null */
    private static class Node {
        int k;
        int v;
        Node prev;
        Node next;
    }
}
