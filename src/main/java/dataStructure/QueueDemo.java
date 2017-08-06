package dataStructure;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * TODO
 * @author XinyuTian
 * @date 2017年5月14日
**/
public class QueueDemo {
    public static void main(String[] args) {
        Queue<String> queue = new LinkedList<String>();
        queue.offer(null);
        queue.add("ele");
        System.out.println("ELEMENTS IN THE QUEUE: ");
        Iterator<String> itr = queue.iterator();
        while (itr.hasNext()) {
            System.out.println(itr.next() + " ");
        }
        System.out.println("-----------------------------");
        String action = null;
        try {
            action = "queue.poll()";
            System.out.println(action + ": " + queue.poll());
            action = "queue.remove()";
            System.out.println(action + ": " + queue.remove());
            action = "queue.peek()";
            System.out.println(action + ": " + queue.peek());
            action = "queue.element()";
            System.out.println(action + ": " + queue.element());
        } catch (Exception e) {
            System.out.println(action + ": " + e);
        }
    }
}
