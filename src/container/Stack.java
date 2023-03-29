package container;

import java.util.NoSuchElementException;

/*
一个Stack类
○ pop方法，用来弹出栈顶元素并返回。
○ push方法，用来在栈顶加入元素。
○ peek方法，用来获取栈顶元素并返回。
○ isEmpty方法，用来判断栈是否为空。
 */
public class Stack<T> {
    private Node<T> top;

    public Stack() {
    }

    public T pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack为空");
        }
        T temp = top.elem;
        top = top.next;
        return temp;
    }

    public void push(T data) {
        Node newNode = new Node(data);
        newNode.next = top;
        top = newNode;
    }

    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack为空");
        }
        return top.elem;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public void print() {
        Node curr = top;
        while (curr != null) {
            System.out.print(curr.elem + "\t");
            curr = curr.next;
        }
    }
}
