package cs108;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;

public class LZWDecoder implements Decoder {
    private final Map<Integer, String> dictionary;
    private final int dictCapacity;

    public LZWDecoder(SortedSet<Character> alphabet, int dictCapacity) {
        if (dictCapacity < alphabet.size()) throw new IllegalArgumentException();

        this.dictCapacity = dictCapacity;

        this.dictionary = new HashMap<>();
        int index = 0;
        for (Character c : alphabet) {
            dictionary.put(index++, c.toString());
        }
    }

    @Override
    public String decode(List<Integer> encoded) {
        StringBuilder builder = new StringBuilder();

        if (!encoded.isEmpty()) {

            String previous = "";
            int index = 0;
            for (Integer enc : encoded) {
                String next = dictionary.get(enc);

                if (next != null) {
                    builder.append(next);
                    if (index++ != 0 && dictionary.size() < dictCapacity)
                        dictionary.put(dictionary.size(), previous + next);

                } else {
                    next = previous + previous.charAt(0);
                    builder.append(next);
                    if (index++ != 0 && dictionary.size() < dictCapacity)
                        dictionary.put(dictionary.size(), next);
                }
                previous = next;
            }

        }

        return builder.toString();
    }
}
