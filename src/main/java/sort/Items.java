package sort;

import java.util.Random;

/**
 * 数组元素, 用于演示排序算法
 */
public class Items {

    static Random rd = new Random();

    public static Item[] random(int size, int bound) {
        Item[] items = new Item[size];
        System.out.print("---generate RANDOM items:");
        for (int i = 0; i < size; i++) {
            items[i] = new Item(i, rd.nextInt(bound));
            System.out.print(" " + items[i]);
        }
        System.out.println();
        return items;
    }

    public static Item[] ordered(int size) {
        Item[] items = new Item[size];
        System.out.print("---generate ORDERED items:");
        items[0] = new Item(0, 0);
        System.out.print(" " + items[0]);
        for (int i = 1; i < size; i++) {
            items[i] = new Item(i, items[i - 1].value + i);
            System.out.print(" " + items[i]);
        }
        System.out.println();
        return items;
    }

    public static void print(String msg, Item[] items) {
        System.out.print(msg);
        for (Item item : items) {
            System.out.print(" " + item);
        }
        System.out.println();
    }

    public static class Item {
        public int idx;
        public int value;

        public Item(int idx, int value) {
            this.idx = idx;
            this.value = value;
        }

        @Override
        public String toString() {
            return String.format("%s[%s]", value, idx);
        }
    }


}
