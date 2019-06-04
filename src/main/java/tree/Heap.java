package tree;

/**
 * 堆
 *
 * @Author: kevin
 * @Date: 2019/5/9
 */
public abstract class Heap<E> {
    protected Node[] nodes;
    protected int size;
    /**
     * 指向末尾节点的下一位置
     */
    protected int tailIdx;
    protected final int rootIdx = 1;

    public Heap(int size) {
        this.size = size;
        nodes = new Node[size];
    }



    public void print() {
        int height = level(tailIdx - 1);
        int idx = rootIdx;
        int beforeLevel = 0;
        while (idx < size && nodes[idx] != null) {
            int l = level(idx);
            if (beforeLevel < l) {
                System.out.print("\n");
                beforeLevel = l;
            }
            for (int i = 0; i < (height - l); i++) {
                System.out.print("          ");
            }
            System.out.print(" " + nodes[idx]);
            idx++;
        }
    }

    public void prettyPrint() {
        int treeHeight = level(tailIdx - 1);
        int leafLen = nodes[tailIdx - 1].toString().length();
        int idx = rootIdx;
        int beforeHeight = treeHeight + 1;
        while (idx < size && nodes[idx] != null) {
            int height = treeHeight - level(idx) + 1;
            int leftSpace;
            if (beforeHeight > height) {
                if (idx != rootIdx) {
                    System.out.print("\n\n");
                }
                leftSpace = ((int) Math.pow(2, height - 1) - 1) * leafLen;
            } else {
                leftSpace = ((int) Math.pow(2, height) - 1) * leafLen;
            }
            // 根据当前节点长度左右微调位置
            leftSpace += leafLen - nodes[idx].toString().length();
            for (int i=0; i<leftSpace; i++) {
                System.out.print(" ");
            }
            System.out.print(nodes[idx]);
            beforeHeight = height;
            idx++;
        }
    }

    public void prettyPrint2() {
        int treeHeight = level(tailIdx - 1);
        int leafLen = nodes[tailIdx - 1].toString().length();
        int idx = rootIdx;
        int beforeHeight = treeHeight + 1;
        while (idx < size && nodes[idx] != null) {
            int height = treeHeight - level(idx) + 1;
            int leftSpace;
            if (beforeHeight > height) {
                if (idx != rootIdx) {
                    System.out.print("\n\n");
                }
                leftSpace = (int) ((Math.pow(2, height - 2) - 0.5) * leafLen);
            } else {
                leftSpace = (int) ((Math.pow(2, height - 1) - 1) * leafLen);
            }
            // 根据当前节点长度左右微调位置
            leftSpace += leafLen - nodes[idx].toString().length();
            for (int i=0; i<leftSpace; i++) {
                System.out.print(" ");
            }
            System.out.print(nodes[idx]);
            beforeHeight = height;
            idx++;
        }
        System.out.println();
    }

    public void insert(Node node) {
        nodes[tailIdx++] = node;
        bottomUpHeapify();
    }

    public void dropTop() {
        if (tailIdx == rootIdx) {
            nodes[rootIdx] = null;
            return;
        }
        nodes[rootIdx] = nodes[tailIdx-1];
        nodes[tailIdx-1] = null;
        tailIdx--;
        topDownHeapify();
    }

    abstract protected void bottomUpHeapify();

    abstract protected void topDownHeapify();

    /**
     * 第l层的节点, 其下标范围是[2^l-2^(l-1), 2^l-1], 所以:
     * 2^l-nodeIdx 的范围是(0, 2^(l-1))
     *
     * @param nodeIdx
     * @return
     */
    public int level(int nodeIdx) {
        int l = 1;
        int tmp;
        int top;

        while (true) {
            tmp = (int) Math.pow(2, l) - nodeIdx;
            top = (int) Math.pow(2, l - 1);

            if (tmp >= 1 && tmp <= top) {
                return l;
            }
            l++;
        }
    }

    /**
     * 如果是满二叉树, 则tailIdx=2^k-1, 其中k为树的高度
     *
     * @return
     */
    public boolean isFull() {
        double tmp = Math.log(tailIdx) / Math.log(2);
        return tmp / 1 == tmp;
    }

    /**
     * 能被2整除
     *
     * @param nodeIdx
     * @return
     */
    private boolean isLeft(int nodeIdx) {
        if (nodeIdx == rootIdx) {
            return true;
        }
        return nodeIdx % 2 == 0;
    }

    static class MyNode implements Node {
        int value;
        String name;

        public MyNode(int value, String name) {
            this.value = value;
            this.name = name;
        }

        @Override
        public int value() {
            return value;
        }

        @Override
        public String name() {
            return name;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return name + "[" + value + "]";
        }
    }
}
