package tree;

/**
 * @Description: 大顶堆
 * @Author: XinyuTian
 * @Date: 2019/6/4
 */
public class BigTopHeap<E> extends Heap<E> {

    public BigTopHeap(int size) {
        super(size);
    }

    public static void main(String[] args) {
        final int limit = (int) Math.pow(2, 4);
        Heap<Node> heap = new BigTopHeap<>(limit);
        for (int i = 0; i < limit; i++) {
            heap.insert(new MyNode((int) Math.round(Math.random() * limit), "node_" + i));
        }
        heap.prettyPrint2();
        heap.dropTop();
        System.out.println("--- after drop top ---");
        heap.prettyPrint2();
    }

    @Override
    protected void bottomUpHeapify() {
        if (tailIdx <= rootIdx + 1) {
            return;
        }
        int sonIdx = tailIdx - 1;
        int fatherIdx = sonIdx / 2;
        while (fatherIdx >= rootIdx) {
            if (nodes[fatherIdx].value() < nodes[sonIdx].value()) {
                Node tmp = nodes[fatherIdx];
                nodes[fatherIdx] = nodes[sonIdx];
                nodes[sonIdx] = tmp;
            }
            sonIdx = fatherIdx;
            fatherIdx = sonIdx / 2;
        }

    }

    @Override
    protected void topDownHeapify() {
        int curr = rootIdx;
        while (curr < tailIdx) {
            int leftSonIdx = 2*curr;
            int rightSonIdx = 2*curr + 1;
            if (leftSonIdx >= tailIdx) {
                break;
            }
            // find max-son
            int maxSon = leftSonIdx;
            if (rightSonIdx < tailIdx && nodes[rightSonIdx].value() > nodes[leftSonIdx].value()) {
                maxSon = rightSonIdx;
            }
            if (nodes[curr].value() >= nodes[maxSon].value()) {
                break;
            }
            // swap
            Node tmp = nodes[curr];
            nodes[curr] = nodes[maxSon];
            nodes[maxSon] = tmp;
            curr = maxSon;
        }

    }
}
