package queue;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * 顺序队列 (非线程安全)
 *
 * @Author: XinyuTian
 * @Date: 2019/5/5
 */
public class ArrayQueue<E> {

    private static final int DEFAULT_INIT_CAPACITY = 5;
    private Object[] members;
    private int head;
    private int tail;
    private int capacity;

    public ArrayQueue() {
        members = new Object[DEFAULT_INIT_CAPACITY];
        this.capacity = DEFAULT_INIT_CAPACITY;
        head = 0;
        tail = 0;
    }

    public ArrayQueue(int size) {
        members = new Object[size];
        this.capacity = size;
        head = 0;
        tail = 0;
    }

    public static void main(String[] args) throws InterruptedException {
        ArrayQueue<Integer> demo = new ArrayQueue<>();
        Thread producer = new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                try {
                    TimeUnit.SECONDS.sleep(Math.round(Math.random()));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                demo.enqueue(i);
                System.out.println(Thread.currentThread().getName() + " | put:" + i + ", " + demo);
            }
        }, "producer");
        producer.start();
        Thread consumer = new Thread(() -> {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(Math.round(Math.random()));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " | take:" + demo.dequeue() + ", " +
                        demo);
            }
        }, "consumer");
        consumer.start();
        producer.join();
        consumer.join();
    }

    /**
     * 操作：
     * 1. 扩容
     * 2. 搬移
     *
     * @param member
     * @return
     */
    public boolean enqueue(E member) {
        members[tail] = member;
        tail++;
        if (tail == capacity) {
            if (head == 0) {
                // 满了, 扩容
                members = Arrays.copyOf(members, capacity + DEFAULT_INIT_CAPACITY);
                capacity += DEFAULT_INIT_CAPACITY;
            } else {
                // 搬移
                for (int i = head; i < tail; i++) {
                    members[i - head] = members[i - head + 1];
                }
                tail -= head;
                head = 0;
            }
        }
        return true;
    }

    /**
     * 操作：
     * 1. 缩容
     *
     * @return
     */
    public E dequeue() {
        if (head == tail) {
            return null;
        }
        E out = (E) members[head];
        head++;
        if (freeCapacity() >= 2 * DEFAULT_INIT_CAPACITY) {
            // 缩容
            for (int i = head; i < tail; i++) {
                members[i - head] = members[i - head + 1];
            }
            tail -= head;
            head = 0;
            capacity = tail - head + DEFAULT_INIT_CAPACITY;
            members = Arrays.copyOf(members, capacity);
        }
        return out;
    }

    private int freeCapacity() {
        return capacity - (tail - head);
    }

    @Override
    public String toString() {
        return "queue:{head=" + head +
                ", tail=" + tail +
                ", capacity=" + capacity +
                '}';
    }
}
