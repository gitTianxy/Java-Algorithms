package dataStructure;

import java.util.Iterator;
import java.util.Random;
import java.util.Stack;

/**
 * Stack:
 * 1. First In, Last Out
 * 2. push(e)
 * 3. pop()
 * 4. peek()
 * <p>
 * Implementations:
 * 1. ArrayStack
 * 2. LinkedStack(略)
 *
 * @author XinyuTian
 * @date 2017年5月14日
 **/
public class StackDemo {
    public static void main(String[] args) {
        // java 原生Stack实现
        nativeStackDemo();
        // 顺序栈
        arrayStackDemo();
    }

    static void arrayStackDemo() {
        int size = 10;
        ArrayStack stack = new ArrayStack(size);
        Random rd = new Random();
        for (int i=0; i<size; i++) {
            stack.push(rd.nextInt(100));
            stack.print();
        }
        while (!stack.empty()) {
            System.out.printf("---pop %s\n", stack.pop());
            stack.print();
        }
    }

    static void nativeStackDemo() {
        Stack<String> nativeStatck = new Stack<String>();
        nativeStatck.push("ele");
        nativeStatck.push(null);
        System.out.println("ELEMENTS IN STATCK:");
        Iterator<String> itr = nativeStatck.iterator();
        while (itr.hasNext()) {
            System.out.println(itr.next());
        }
        System.out.println("---------------------------------");
        String action = null;
        try {
            action = "stack.pop()";
            System.out.println(action + ": " + nativeStatck.pop());
            action = "stack.peek()";
            System.out.println(action + ": " + nativeStatck.peek());
        } catch (Exception e) {
            System.out.println(action + ": " + e);
        }
        try {
            action = "stack.pop()";
            System.out.println(action + ": " + nativeStatck.pop());
            action = "stack.peek()";
            System.out.println(action + ": " + nativeStatck.peek());
        } catch (Exception e) {
            System.out.println(action + ": " + e);
        }
        try {
            action = "stack.pop()";
            System.out.println(action + ": " + nativeStatck.pop());
            action = "stack.peek()";
            System.out.println(action + ": " + nativeStatck.peek());
        } catch (Exception e) {
            System.out.println(action + ": " + e);
        }
    }

    static class ArrayStack {
        int[] values;
        int top;

        public ArrayStack(int capacity) {
            values = new int[capacity];
            top = 0;
        }

        public void push(int n) {
            if (top == values.length) {
                throw new RuntimeException("stack is full!");
            }
            System.out.printf("---push %s\n", n);
            values[top++] = n;
        }

        public int pop() {
            if (top == 0) {
                throw new RuntimeException("statck is empty!");
            }
            return values[--top];
        }

        public int peek() {
            if (top == 0) {
                throw new RuntimeException("statck is empty!");
            }
            return values[top - 1];
        }

        public boolean empty() {
            return top <= 0;
        }

        public void print() {
            int c = top;
            while (c-- > 0) {
                System.out.print(values[c] + " ");
            }
            System.out.println();
        }
    }

}
