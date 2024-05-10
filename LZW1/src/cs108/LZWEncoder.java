package cs108;

import java.util.*;

public class LZWEncoder implements Encoder {
    private final Map<String, Integer> dictionary;
    private final int dictCapacity;

    public LZWEncoder(SortedSet<Character> alphabet, int dictCapacity) {
        if (dictCapacity < alphabet.size()) throw new IllegalArgumentException();

        this.dictCapacity = dictCapacity;

        this.dictionary = new HashMap<>();
        int index = 0;
        for (Character c : alphabet) {
            dictionary.put(c.toString(), index++);
        }
    }

    private String getPrefix(String str) {
        for (int i = str.length(); i > 0; i--) {
            String sub = str.substring(0, i);
            if (dictionary.containsKey(sub))
                return sub;
        }

        return "";
    }

    @Override
    public List<Integer> encode(String str) {
        List<Integer> encoded = new ArrayList<>();

        if (!str.isEmpty()) {

            String prefix = getPrefix(str);
            encoded.add(dictionary.get(prefix));

            if (dictionary.size() < dictCapacity) {
                Integer newIndex = dictionary.size();
                if (prefix.length() < str.length()) {
                    String newElement = prefix + str.charAt(prefix.length());

                    dictionary.put(newElement, newIndex);
                }
            }

            encoded.addAll(encode(str.substring(prefix.length())));
        }

        return encoded;
    }
}
