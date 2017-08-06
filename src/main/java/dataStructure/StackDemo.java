package dataStructure;

import java.util.Iterator;
import java.util.Stack;

/**
 * TODO
 * @author XinyuTian
 * @date 2017年5月14日
**/
public class StackDemo {
    public static void main(String[] args) {
        Stack<String> stack = new Stack<String>();
        stack.push("ele");
        stack.push(null);
        System.out.println("ELEMENTS IN STATCK:");
        Iterator<String> itr = stack.iterator();
        while (itr.hasNext()) {
            System.out.println(itr.next());
        }
        System.out.println("---------------------------------");
        String action = null;
        try {
            action = "stack.pop()";
            System.out.println(action + ": " + stack.pop());
            action = "stack.peek()";
            System.out.println(action + ": " + stack.peek());
        } catch (Exception e) {
            System.out.println(action + ": " + e);
        }
        try {
            action = "stack.pop()";
            System.out.println(action + ": " + stack.pop());
            action = "stack.peek()";
            System.out.println(action + ": " + stack.peek());
        } catch (Exception e) {
            System.out.println(action + ": " + e);
        }
        try {
            action = "stack.pop()";
            System.out.println(action + ": " + stack.pop());
            action = "stack.peek()";
            System.out.println(action + ": " + stack.peek());
        } catch (Exception e) {
            System.out.println(action + ": " + e);
        }
    }
}
