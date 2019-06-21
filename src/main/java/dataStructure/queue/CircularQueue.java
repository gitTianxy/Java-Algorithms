package dataStructure.queue;

/**
 * @Description: 循环队列
 *
 * @Author: XinyuTian
 * @Date: 2019/5/5
 */
public class CircularQueue<E> {

    private Object[] members;

    private volatile int head;
    private volatile int tail;
    private final int size;

    public CircularQueue(int size) {
        this.size = size;
        members = new Object[size];
        this.head = 0;
        this.tail = 0;
    }

    public boolean enqueue(E member) {
        // 如果队列满了
        if ((tail + 1) % size == head) {
            return false;
        }
        members[tail] = member;
        tail = (tail + 1) % size;
        return true;
    }

    public E dequeue() {
        // 队列空了
        if (head == tail) {
            return null;
        }
        E out = (E) members[head];
        head = (head + 1) % size;
        return out;
    }

    @Override
    public String toString() {
        return "CircularQueue{" +
                "head=" + head +
                ", tail=" + tail +
                ", size=" + size +
                '}';
    }
}
