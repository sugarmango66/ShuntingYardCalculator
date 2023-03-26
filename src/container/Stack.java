package container;

import java.util.NoSuchElementException;

/*
一个Stack类
○ pop方法，用来弹出栈顶元素并返回。
○ push方法，用来在栈顶加入元素。
○ peek方法，用来获取栈顶元素并返回。
○ isEmpty方法，用来判断栈是否为空。
 */
public class Stack {
    private Node top;

    public Stack() {
    }

    public char pop() {
        try {
            if (isEmpty()) {
                throw new NoSuchElementException("Stack为空");
            }
            char temp = top.elem;
            top = top.next;
            return temp;
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
            return 'x';
        }
    }

    public void push(char data) {
        Node newNode = new Node(data);
        newNode.next = top;
        top = newNode;
    }

    public char peek() {
        try {
            if (isEmpty()) {
                throw new NoSuchElementException("Stack为空");
            }
            return top.elem;
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
            return 'x';
        }
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
