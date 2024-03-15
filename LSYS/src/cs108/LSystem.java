package cs108;

import java.util.*;

public record LSystem(String string, Map<Character, String> rules,
                      Set<Character> lineChars, int turningAngle) {

    public LSystem {
        rules = Map.copyOf(rules);
        lineChars = Set.copyOf(lineChars);
    }

    public LSystem(String string, Map<Character, String> rules,
                   String lineChars, int turningAngle) {

        this(string, rules, stringToCharSet(lineChars), turningAngle);
    }

    private static Set<Character> stringToCharSet(String s) {
        char[] chars = s.toCharArray();
        Set<Character> charSet = new HashSet<>();

        for (char c : chars) {
            charSet.add(c);
        }

        return charSet;
    }

    public LSystem evolve() {
        StringBuilder sb = new StringBuilder();
        for (Character c : this.string.toCharArray()) {
            sb.append(this.rules.getOrDefault(c, c.toString()));
        }

        return new LSystem(sb.toString(), this.rules, this.lineChars, this.turningAngle);
    }

    public LSystem evolve(int steps) {
        if (steps <= 1)
            return this.evolve();
        else
            return this.evolve(steps - 1).evolve();
    }

}
