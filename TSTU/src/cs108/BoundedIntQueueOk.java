package cs108;

import java.util.Arrays;

public class BoundedIntQueueOk implements BoundedIntQueue {
    private final int[] queue;
    private final int capacity;
    private int size = 0;

    public BoundedIntQueueOk(int capacity) {
        if (capacity < 0) throw new IllegalArgumentException();

        this.capacity = capacity;
        this.queue = new int[capacity];
    }

    @Override
    public int capacity() {
        return this.capacity;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public boolean isFull() {
        return this.size == this.capacity;
    }

    @Override
    public void addLast(int newElement) {
        if (this.size == this.capacity) throw new IllegalStateException();

        this.queue[this.size] = newElement;

        this.size += 1;
    }

    @Override
    public int removeFirst() {
        if (this.size == 0) throw new IllegalStateException();

        int toReturn = this.queue[0];
        int[] temp = new int[this.capacity];
        System.arraycopy(this.queue, 1, temp, 0, this.size);
        System.arraycopy(temp, 0, this.queue, 0, this.capacity);

        this.size -= 1;
        return toReturn;
    }

    @Override
    public String toString() {
        return "BoundedIntQueueOk{" +
                "queue=" + Arrays.toString(queue) +
                ", capacity=" + capacity +
                ", size=" + size +
                '}';
    }
}
