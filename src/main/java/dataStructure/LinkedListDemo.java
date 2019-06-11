package dataStructure;

import java.util.Iterator;

/**
 * 链表
 * 1. single linked list
 * 2. double linked list
 * 3. circular linked list
 *
 * @Author: XinyuTian
 * @Date: 2019/6/11
 */
public class LinkedListDemo {

    public static void main(String[] args) {
        singleLinkedListDemo();
        doubleLinkedListDemo();
        circularLinkedListDemo();
    }

    static void singleLinkedListDemo() {
        SingleLinkedList sll = new SingleLinkedList();
        sll.print("init");
        for (int i = 0; i < 10; i++) {
            sll.add(i);
        }
        sll.print("after ADD(v)");
        for (int i = 0; i < 10; i += 3) {
            sll.remove(i);
        }
        sll.print("after REMOVE(v)");
    }

    static void doubleLinkedListDemo() {
        DoubleLinkedList dll = new DoubleLinkedList();
        dll.print("init");
        for (int i = 0; i < 10; i++) {
            dll.add(i);
        }
        dll.print("after ADD(v)");
        for (int i = 0; i < 10; i += 3) {
            dll.remove(i);
        }
        dll.print("after REMOVE(v)");
    }

    static void circularLinkedListDemo() {
        CircularLinkedList cll  = new CircularLinkedList();
        cll.print("init");
        for (int i = 0; i < 10; i++) {
            cll.add(i);
        }
        cll.print("after ADD(v)");
        for (int i = 0; i < 10; i += 3) {
            cll.remove(i);
        }
        cll.print("after REMOVE(v)");
    }

    /**
     * 单链表
     */
    static class SingleLinkedList extends AbstractLinkedList<SingleLinkedList.SNode> {

        @Override
        void add(int v) {
            SNode n = new SNode(v);
            if (head == null) {
                head = n;
                tail = n;
            } else {
                tail.next = n;
                tail = n;
            }
            size++;
        }

        @Override
        boolean remove(int v) {
            if (head.value == v) {
                head = head.next;
                size--;
                return true;
            }
            SNode pre = head;
            SNode tmp = pre.next;
            while (tmp != null) {
                if (tmp.value == v) {
                    pre.next = tmp.next;
                    size--;
                    return true;
                }
                pre = tmp;
                tmp = pre.next;
            }
            return false;
        }

        public int size() {
            return size;
        }

        static class SNode extends AbstractNode {
            private int value;
            private SNode next;

            public SNode(int value) {
                this.value = value;
            }

            @Override
            int value() {
                return value;
            }

            @Override
            AbstractNode next() {
                return next;
            }

            @Override
            String print() {
                return String.format("%s(%s)", value, next == null ? "null" : next.value);
            }
        }
    }

    /**
     * 循环链表
     */
    static class CircularLinkedList extends AbstractLinkedList<CircularLinkedList.CNode> {

        @Override
        void add(int v) {
            CNode n = new CNode(v);
            if (head == null) {
                n.next = n;
                head = n;
                tail = n;
            } else {
                n.next = head;
                tail.next = n;
                tail = n;
            }
            size++;
        }

        @Override
        boolean remove(int v) {
            // 删头
            if (head.value == v) {
                if (tail == head) {
                    tail = null;
                    head = null;
                    size--;
                    return true;
                }
                head = head.next;
                tail.next = head;
                size--;
                return true;
            }
            int c = 1;
            CNode pre = head;
            CNode cur = head.next;
            while (c < size) {
                if (cur.value == v) {
                    pre.next = cur.next;
                    size--;
                    return true;
                }
                pre = cur;
                cur = cur.next;
                c++;
            }
            return false;
        }


        static class CNode extends AbstractNode {
            private int value;
            private CNode next;

            public CNode(int value) {
                this.value = value;
            }

            @Override
            int value() {
                return value;
            }

            @Override
            AbstractNode next() {
                return next;
            }

            @Override
            String print() {
                return String.format("%s(%s)", value, next == null ? "null" : next.value);
            }
        }
    }

    /**
     * 双向链表
     */
    static class DoubleLinkedList extends AbstractLinkedList<DoubleLinkedList.DNode> {

        @Override
        void add(int v) {
            DNode n = new DNode(v);
            if (head == null) {
                head = n;
                tail = n;
            } else {
                n.pre = tail;
                tail.next = n;
                tail = n;
            }
            size++;
        }

        @Override
        boolean remove(int v) {
            // 删头
            if (head.value == v) {
                head.next.pre = null;
                if (tail == head) {
                    tail = head.next;
                }
                head = head.next;
                size--;
                return true;
            }
            // 删尾
            if (tail.value == v) {
                tail.pre.next = null;
                tail = tail.pre;
                size--;
                return true;
            }
            DNode n = head.next;
            while (n != null) {
                if (n.value == v) {
                    n.pre.next = n.next;
                    n.next.pre = n.pre;
                    size--;
                    return true;
                }
                n = n.next;
            }
            return false;
        }

        static class DNode extends AbstractNode {
            private int value;
            private DNode pre;
            private DNode next;

            public DNode(int v) {
                value = v;
            }

            @Override
            int value() {
                return value;
            }

            @Override
            AbstractNode next() {
                return next;
            }

            @Override
            String print() {
                return String.format("(%s)%s(%s)", pre == null ? "null" : pre.value, value, next == null ? "null" :
                        next.value);
            }
        }
    }

    /**
     * NOTE: 注意此处泛型写法
     *
     * @param <E>
     */
    static abstract class AbstractLinkedList<E extends AbstractNode> implements Iterable<E> {
        protected E head;
        protected E tail;
        protected int size;

        abstract void add(int v);

        abstract boolean remove(int v);

        public void print(String msg) {
            System.out.printf("---%s %s:\n", this.getClass().getSimpleName(), msg);
            for (AbstractNode n : this) {
                System.out.print(n.print() + " ");
            }
            System.out.println();
        }

        @Override
        public Iterator<E> iterator() {

            return new Iterator<E>() {
                E cur = head;
                int c = 0;

                @Override
                public boolean hasNext() {
                    return c < size;
                }

                @Override
                public E next() {
                    E ret = cur;
                    cur = (E) cur.next();
                    c++;
                    return ret;
                }
            };
        }
    }

    static abstract class AbstractNode {
        abstract int value();

        abstract AbstractNode next();

        abstract String print();
    }

}
