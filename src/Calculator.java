/*
一个Calculator类，定义为public
○ convert方法，获取一个代表表达式的字符串作为参数，将这个字符串转换成
相应的后缀表达式，存储在一个队中，并返回。
○ getPrecedence方法，获取一个字符作为参数，如果字符为[+]或[-]，返回0;如
果字符为[*]或[/]，返回1。这个方法让我们可以对比运算的优先级。
○ evaluate方法，获取一个代表后缀表达式的队列作为参数，返回表达式计算
结果。
○ isNumeric方法，获取一个字符串作为参数，判断该字符串是否为数字。
○ main方法:
■ 获取输入:获取一个input.txt文件，文件内容是几行中缀表达式。
■ 生成输出:生成一个output.txt文件，存储表达式计算结果，每行是一个
输出+空格+结果。
 */

import container.Queue;
import container.Stack;

public class Calculator {
    public static void main(String[] args) {
        String ex = "12+3*4";
        Queue res = convert(ex);
        res.print();
    }

    //getPrecedence方法 判断运算优先级
    public static int getPrecedence(char c) {
        if (c == '+' || c == '-')
            return 0;
        if (c == '*' || c == '/')
            return 1;
        System.out.println("字符非法");
        return -1;
    }

    //isNumeric方法 判断字符串是否为数字
    public static boolean isNumeric(String str) {
        //逐位判断ascii范围
        for (char c : str.toCharArray()) {
            if (!(c >= '0' && c <= '9'))
                return false;
        }
        return true;
    }

    //isOperator方法
    public static boolean isOperator(char c) {
        return (c == '+' || c == '-' || c == '*' || c == '/');
    }

    //convert方法 返回后缀表达式in队
    public static Queue convert(String expression) {
        //新建容器
        Stack stack = new Stack();
        Queue queue = new Queue();
        //逐个取出字符
        //应用调度算法
        for (char c : expression.toCharArray()) {
            if (Character.isDigit(c)) {
                queue.enqueue(c);
            }
            if (isOperator(c)) {
                while (!stack.isEmpty() && isOperator(stack.peek()) && getPrecedence(stack.peek()) >= getPrecedence(c))
                    queue.enqueue(stack.pop());
                stack.push(c);
            }
            if (c == '(') {
                stack.push(c);
            }
            if (c == ')') {
                while (stack.peek() != '(') {
                    queue.enqueue(stack.pop());
                }
                stack.pop();//丢弃'('
            }
        }

        while (!stack.isEmpty()) {
            queue.enqueue(stack.pop());
        }
        return queue;
    }
}
