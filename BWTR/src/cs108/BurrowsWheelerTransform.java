package cs108;

import java.util.*;

public final class BurrowsWheelerTransform {
    private BurrowsWheelerTransform() {}

    public static ArrayDeque<Character> stringToCharDeque(String string) {
        ArrayDeque<Character> deque = new ArrayDeque<>();
        for (char c : string.toCharArray()) {
            deque.offer(c);
        }

        return deque;
    }

    public static String charDequeToString(Deque<Character> deque) {
        StringBuilder builder = new StringBuilder();
        for (Character c : deque) {
            builder.append(c);
        }

        return builder.toString();
    }

    public static Pair<Integer, String> forward(String s) {
        if (s == null || s.isEmpty())
            throw new IllegalArgumentException("Empty / Null string passed to forward()");

        List<String> rotations = new ArrayList<>();

        Deque<Character> q = stringToCharDeque(s);
        for (int i = 0; i < s.length(); i++) {
            Character last = q.pollLast();
            q.offerFirst(last);

            rotations.add(charDequeToString(q));
        }

        Collections.sort(rotations);

        StringBuilder builder = new StringBuilder();
        for (String rotation : rotations) {
            builder.append(rotation.charAt(rotation.length() - 1));
        }

        return new Pair<>(rotations.indexOf(s), builder.toString());
    }

    public static String backward(Pair<Integer, String> p) {
        if (p.second().length() <= p.first() || p.second().isEmpty())
            throw new IndexOutOfBoundsException(
                    "Invalid index and string combination passed to backward()");

        List<String> rotations = new ArrayList<>();
        for (int i = 0; i < p.second().length(); i++) {
            rotations.add("");
        }

        for (int i = 0; i < p.second().length(); i++) {
            List<Deque<Character>> deques = new ArrayList<>();
            for (String rotation : rotations) {
                deques.add(stringToCharDeque(rotation));
            }

            Deque<Character> q = stringToCharDeque(p.second());
            for (Deque<Character> deque : deques) {
                deque.offerFirst(q.poll());
            }

            for (int j = 0; j < deques.size(); j++) {
                rotations.set(j, charDequeToString(deques.get(j)));
            }

            Collections.sort(rotations);
        }

        return rotations.get(p.first());
    }
}
