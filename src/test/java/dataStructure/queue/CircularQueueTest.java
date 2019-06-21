package dataStructure.queue;

import java.util.concurrent.TimeUnit;

/**
 * @Author: kevin
 * @Date: 2019/5/5
 */
public class CircularQueueTest {
    public static void main(String[] args) throws InterruptedException {
        CircularQueue<Integer> circular = new CircularQueue<>(5);
        Thread producer = new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                try {
                    TimeUnit.SECONDS.sleep(Math.round(Math.random()));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " | put:" + i + ", result:" + circular.enqueue
                        (i) + ", " + circular);
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
                System.out.println(Thread.currentThread().getName() + " | take:" + circular.dequeue() + ", " +
                        circular);
            }
        }, "consumer");
        consumer.start();
        producer.join();
        consumer.join();
    }
}
