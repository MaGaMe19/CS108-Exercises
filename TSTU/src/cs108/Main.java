package cs108;

public class Main {
    public static void main(String[] args) {
        BoundedIntQueueOk queue = new BoundedIntQueueOk(8);

        queue.addLast(1);
        queue.addLast(2);
        queue.addLast(3);

        System.out.println(queue);

        System.out.println(queue.removeFirst());
        System.out.println(queue);
    }
}
