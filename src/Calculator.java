/*
一个Calculator类，定义为public
○ convert方法，获取一个代表表达式的字符串作为参数，将这个字符串转换成
相应的后缀表达式，存储在一个队中，并返回。
○ getPrecedence方法，获取一个字符作为参数，如果字符为[+]或[-]，返回0;如
果字符为[*]或[/]，返回1。这个方法让我们可以对比运算的优先级。
○ evaluate方法，获取一个代表后缀表达式的队列作为参数，返回表达式计算
结果。(暂时仅考虑整数 包括整除)
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
        String ex = "(8-5)*4+8/3";
        Queue res = convert(ex);
        res.print();
        System.out.println(evaluate(res));

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
    public static Queue convert(String e) {
        //新建容器
        Stack stack = new Stack();
        Queue queue = new Queue();
        //逐个取出字符(如果是连续数字则取出整串直到遇到非数字)
        //应用调度算法
        for (int i = 0; i < e.length(); i++) {
            char c = e.charAt(i);
            if (Character.isDigit(c)) {//当前位是数字 则连续判断字符 将操作数作为整体入队
                int begin = i;
                while (i < e.length() - 1 && e.charAt(i + 1) >= '0' && e.charAt(i + 1) <= '9') {
                    i++;
                }
                String opStr = e.substring(begin, i + 1);
                Integer op = Integer.parseInt(opStr);
                queue.enqueue(op);//入队
            } else {//当前位不是数字
                if (isOperator(c)) {//运算符o1
                    //只要 栈非空 且 栈顶是运算符 且 该运算符是更高优先级或同级左联型
                    while (!stack.isEmpty() && isOperator((char) stack.peek()) && getPrecedence((char) stack.peek()) >= getPrecedence(c))
                        queue.enqueue(stack.pop());//栈顶入队
                    stack.push(c);//o1入栈
                }
                if (c == '(') {//左括号 入栈
                    stack.push(c);
                }
                if (c == ')') {//右括号
                    while ((char) stack.peek() != '(') {//依次出栈入队直到遇到左括号
                        queue.enqueue(stack.pop());
                    }
                    stack.pop();//丢弃'('
                }
            }
        }

        while (!stack.isEmpty()) {
            queue.enqueue(stack.pop());
        }
        return queue;
    }

    //evaluate方法 返回后缀表达式计算结果
    public static int evaluate(Queue q) {
        /*
        1. 从队列头部取出标记。
        2. 如果当前标记是一个数字，把它入栈。
        3. 如果当前标记是一个操作符，从栈中弹出相应数量的元素(例如，‘*’代表乘法，需要两个操作数)，
        根据操作符对弹出的数字进行操作，并将结果入栈。
        重复以上三步操作直到队列为空
         */
        //新建栈
        Stack stack = new Stack();
        while (!q.isEmpty()) {
            Object obj = q.dequeue();
            if (obj instanceof Integer) {//数字
                stack.push(obj);
            } else {//操作符
                //出栈2个操作数
                Integer right = (Integer) stack.pop();
                Integer left = (Integer) stack.pop();

                int res = 0;
                switch ((char) obj) {
                    case '+' -> res = left + right;
                    case '-' -> res = left - right;
                    case '*' -> res = left * right;
                    case '/' -> res = left / right;
                    default -> System.out.println("运算符有误");
                }
                stack.push(res);
            }
        }
        return (Integer) stack.pop();
    }
}
