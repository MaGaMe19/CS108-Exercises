package cs108;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoundedIntQueueTest {
    BoundedIntQueueBuggy trivialQueueEmpty = new BoundedIntQueueBuggy(8);
    BoundedIntQueueBuggy edgeCaseZeroCapacity = new BoundedIntQueueBuggy(0);

    @Test
    void constructorFailsOnNegativeCapacity() {
        assertThrows(IllegalArgumentException.class, () -> {
            BoundedIntQueueBuggy negCapacity = new BoundedIntQueueBuggy(-8);
        });
    }

    @Test
    void capacityWorksOnTrivialQueue() {
        assertEquals(8, trivialQueueEmpty.capacity());
    }

    @Test
    void capacityWorksOnEdgeCase() {
        assertEquals(0, edgeCaseZeroCapacity.capacity());
    }

    @Test
    void sizeWorksOnTrivialCase() {
        BoundedIntQueueBuggy queue = new BoundedIntQueueBuggy(8);
        queue.addLast(1);
        queue.addLast(2);
        queue.addLast(3);

        assertEquals(3, queue.size());
    }

    @Test
    void sizeWorksOnEdgeCase() {
        assertEquals(0, trivialQueueEmpty.size());
    }

    @Test
    void isEmptyWorksOnTrivialQueue() {
        assertTrue(trivialQueueEmpty.isEmpty());
    }

    @Test
    void isEmptyWorksOnFilledQueue() {
        BoundedIntQueueBuggy queue = new BoundedIntQueueBuggy(8);
        queue.addLast(1);
        queue.addLast(2);
        queue.addLast(3);
        assertFalse(queue.isEmpty());
    }

    @Test
    void isFullWorksOnFullQueue() {
        BoundedIntQueueBuggy queue = new BoundedIntQueueBuggy(4);
        for (int i = 0; i < 4; i++) {
            queue.addLast(i);
        }

        assertTrue(queue.isFull());
    }

    @Test
    void isFullWorksOnEmptyQueue() {
        assertFalse(trivialQueueEmpty.isFull());
    }

    @Test
    void addLastFailsOnFullQueue() {
        BoundedIntQueueBuggy queue = new BoundedIntQueueBuggy(4);
        // "full" queue as it always has 1 less capacity than expected
        for (int i = 0; i < 3; i++) {
            queue.addLast(i);
        }

        assertThrows(IllegalStateException.class, () -> {
            queue.addLast(99);
        });
    }

    @Test
    void removeFirstFailsOnEmptyQueue() {
        assertThrows(IllegalStateException.class, () -> {
            trivialQueueEmpty.removeFirst();
        });
    }
}