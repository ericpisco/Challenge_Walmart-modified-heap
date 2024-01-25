import java.util.ArrayList;
import java.util.List;

public class ModifiedHeap {
    private List<Integer> heap;

    public ModifiedHeap() {
        heap = new ArrayList<>();
    }

    public void insert(int value) {
        heap.add(value);
        heapifyUp();
    }

    public Integer popMax() {
        if (heap.isEmpty()) {
            return null;
        }

        int maxValue = heap.get(0);

        if (heap.size() > 1) {
            // Move the last element to the root
            heap.set(0, heap.remove(heap.size() - 1));
            heapifyDown();
        } else {
            // If only one element is present, just remove it
            heap.remove(0);
        }

        return maxValue;
    }

    private void heapifyUp() {
        int index = heap.size() - 1;

        while (index > 0) {
            int parentIndex = (index - 1) / 2;
            if (heap.get(index) > heap.get(parentIndex)) {
                // Swap the elements if the heap property is violated
                int temp = heap.get(index);
                heap.set(index, heap.get(parentIndex));
                heap.set(parentIndex, temp);
                index = parentIndex;
            } else {
                break;
            }
        }
    }

    private void heapifyDown() {
        int index = 0;

        while (true) {
            int leftChildIndex = 2 * index + 1;
            int rightChildIndex = 2 * index + 2;
            int largest = index;

            if (leftChildIndex < heap.size() && heap.get(leftChildIndex) > heap.get(largest)) {
                largest = leftChildIndex;
            }

            if (rightChildIndex < heap.size() && heap.get(rightChildIndex) > heap.get(largest)) {
                largest = rightChildIndex;
            }

            if (largest != index) {
                // Swap the elements if the heap property is violated
                int temp = heap.get(index);
                heap.set(index, heap.get(largest));
                heap.set(largest, temp);
                index = largest;
            } else {
                break;
            }
        }
    }

    public static void main(String[] args) {
        ModifiedHeap modifiedHeap = new ModifiedHeap();
        modifiedHeap.insert(5);
        modifiedHeap.insert(8);
        modifiedHeap.insert(3);
        modifiedHeap.insert(12);
        modifiedHeap.insert(7);

        System.out.println("Modified Heap: " + modifiedHeap.heap);

        Integer maxVal = modifiedHeap.popMax();
        System.out.println("Popped Max Value: " + maxVal);
        System.out.println("Modified Heap after popMax: " + modifiedHeap.heap);
    }
}
