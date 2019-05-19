package queue;

import java.util.concurrent.TimeUnit;

/**
 * @Author: kevin
 * @Date: 2019/5/5
 */
public class ArrayQueueTest {
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
}
