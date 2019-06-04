package tree;

/**
 * @Description: 小顶堆
 *
 * @Author: XinyuTian
 * @Date: 2019/6/4
 */
public class SmallTopHeap<E> extends Heap<E> {
    public SmallTopHeap(int size) {
        super(size);
    }

    public static void main(String[] args) {
        final int limit = (int) Math.pow(2, 4);
        Heap<Node> heap = new SmallTopHeap<>(limit);
        for (int i = 0; i < limit; i++) {
            heap.insert(new MyNode((int) Math.round(Math.random() * limit), "n_" + i));
        }
        heap.prettyPrint2();
        heap.dropTop();
        System.out.println("--- after drop top ---");
        heap.prettyPrint2();
    }

    @Override
    protected void bottomUpHeapify() {
        if (tailIdx <= rootIdx) {
            return;
        }
        int sonIdx = tailIdx - 1;
        int fatherIdx = sonIdx / 2;
        while (fatherIdx >= rootIdx) {
            if (nodes[fatherIdx].value() > nodes[sonIdx].value()) {
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
            int minSon = leftSonIdx;
            if (rightSonIdx < tailIdx && nodes[rightSonIdx].value() < nodes[leftSonIdx].value()) {
                minSon = rightSonIdx;
            }
            if (nodes[curr].value() <= nodes[minSon].value()) {
                break;
            }
            // swap
            Node tmp = nodes[curr];
            nodes[curr] = nodes[minSon];
            nodes[minSon] = tmp;
            curr = minSon;
        }
    }
}
