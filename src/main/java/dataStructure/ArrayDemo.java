package dataStructure;

import java.util.Arrays;

/**
 * 1. Dynamic Array
 * 2. Sorted Array
 *
 * @Author: XinyuTian
 * @Date: 2019/6/5
 */
public class ArrayDemo {
    public static void main(String[] args) {
        dynamicArrayDemo();
        sortedArrayDemo();
    }

    static void sortedArrayDemo() {
        final int size = 20;
        SortedArray sa = new SortedArray(size);
        sa.print("init");
        for (int i=0; i<10; i+=1) {
            sa.add((int) Math.round(20 * Math.random()));
        }
        sa.print("after ADD(e)");
        sa.remove(sa.get(3));
        sa.print("after remove(e)");
    }

    static void dynamicArrayDemo() {
        DynamicArray da = new DynamicArray();
        da.print("init");
        for (int i = 0; i < 10; i++) {
            da.add(i);
        }
        da.print("after ADD(e)");
        for (int i = 2; i < 14; i += 2) {
            da.add(i, 100);
        }
        da.print("after ADD(pos, e)");
        for (int i = 3; i < 10; i += 3) {
            da.remove(i);
        }
        da.print("after REMOVE(e)");
        for (int i = 5; i < da.size(); i += 5) {
            da.set(i, da.get(i) * 1000);
        }
        da.print("after SET(pos,e)");
    }


    /**
     * 有序数组
     * <p>
     * 支持操作: add(e); remove(e); get(i)
     */
    static class SortedArray extends AbstractArray {
        public SortedArray(int size) {
            values = new int[size];
            tail = 0;
        }

        public int findInsertPos(int num, int startPos, int endPos) {
            if (num < values[startPos]) {
                return startPos;
            }
            if (num >= values[endPos]) {
                return endPos + 1;
            }
            if (endPos - startPos == 1 || endPos == startPos) {
                return startPos + 1;
            }

            int medianPos = (startPos + endPos) / 2;
            if (num >= values[medianPos]) {
                return findInsertPos(num, medianPos, endPos);
            } else {
                return findInsertPos(num, startPos, medianPos);
            }
        }

        public int findPos(int num, int startPos, int endPos) {
            if (startPos == endPos) {
                if (values[startPos] == num) {
                    return startPos;
                } else {
                    return -1;
                }
            }
            int medianPos = (startPos + endPos) / 2;
            if (num == values[medianPos]) {
                return medianPos;
            } else if (num > values[medianPos]) {
                return findPos(num, medianPos + 1, endPos);
            } else {
                return findPos(num, startPos, medianPos - 1);
            }
        }

        public void add(int num) {
            int pos = 0;
            if (tail > 0) {
                pos = findInsertPos(num, 0, tail - 1);
            }
            if (pos == tail) {
                values[tail++] = num;
            } else {
                shift(pos, true);
                values[pos] = num;
            }
        }

        public void remove(int num) {
            int pos = findPos(num, 0, tail - 1);
            if (pos != -1) {
                shift(pos, false);
            }
        }
    }

    /**
     * 动态数组
     * <p>
     * 支持操作：add(e), add(pos,e); remove(e); get(i)
     */
    static class DynamicArray extends AbstractArray {
        private final static int INITAL_SIZE = 5;
        private final static int INCREASE_STEP = 5;

        public DynamicArray() {
            this(INITAL_SIZE);
        }

        public DynamicArray(int size) {
            values = new int[size];
            for (int i = 0; i < size; i++) {
                values[i] = -1;
            }
            tail = 0;
        }

        public void add(int num) {
            values[tail++] = num;
            if (tail == values.length) {
                grow();
            }
        }

        private void grow() {
            values = Arrays.copyOf(values, values.length + INCREASE_STEP);
        }

        private void shrink() {
            values = Arrays.copyOf(values, values.length - INCREASE_STEP);
        }

        public void add(int idx, int num) {
            if (idx > tail) {
                throw new IndexOutOfBoundsException("index:" + idx + ", size:" + tail);
            }
            if (idx == tail) {
                add(num);
                return;
            }
            shift(idx, true);
            values[idx] = num;
            if (tail == values.length) {
                grow();
            }
        }

        public void remove(int num) {
            for (int i = 0; i < tail; i++) {
                if (values[i] == num) {
                    shift(i + 1, false);
                    break;
                }
            }
            if (values.length - tail > INCREASE_STEP) {
                shrink();
            }
        }
    }

    static abstract class AbstractArray {
        protected int[] values;
        protected int tail;

        protected void shift(int startIdx, boolean right) {
            if (right) {
                System.arraycopy(values, startIdx, values, startIdx + 1,
                        tail - startIdx);
                tail++;
            } else {
                System.arraycopy(values, startIdx, values, startIdx - 1,
                        tail - startIdx);
                tail--;
            }
        }

        protected int get(int idx) {
            return values[idx];
        }

        protected void set(int idx, int val) {
            values[idx] = val;
        }

        protected int size() {
            return tail;
        }

        protected void print(String msg) {
            System.out.printf("---%s %s: size=%s, capacity:%s\n", this.getClass().getSimpleName(), msg, tail, values
                    .length);
            for (int i = 0; i < tail; i++) {
                System.out.print(values[i] + " ");
            }
            System.out.println();
        }
    }
}
