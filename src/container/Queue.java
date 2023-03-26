package container;

import java.util.NoSuchElementException;

/*
一个Queue类
○ dequeue方法，用来弹出队首元素并返回。
○ enqueue方法，用来在队尾加入元素。
○ peek方法，用来获取队首元素并返回。
○ isEmpty方法，用来判断队列是否为空。
 */
public class Queue {
    private Node first;
    private Node last;

    public Queue() {
    }
    public boolean isEmpty() {
        return first==null;
    }
    public char peek() {
        try {
            if (isEmpty())
                throw new NoSuchElementException("Queue为空");
            return first.elem;
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
            return 'x';
        }
    }
    public void enqueue(char data) {//队尾入
        Node node = new Node(data);
        if (isEmpty()) {
            first = node;
            last = node;
        }
        else {
            last.next = node;
            last = node;
        }
    }
    public char dequeue() {//队首出
        try {
            if (isEmpty())
                throw new NoSuchElementException("Queue为空");
            char temp = first.elem;
            first = first.next;
            return temp;
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
            return 'x';
        }
    }
    public void print() {
        Node curr = first;
        while (curr != null) {
            System.out.print(curr.elem + "\t");
            curr = curr.next;
        }
    }
}