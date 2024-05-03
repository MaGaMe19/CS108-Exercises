package cs108;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringJoiner;

public final class Main {
    private static final int ARRAY_SIZE = 256;
    private static final String[] FILES = new String[]{
            "files/file0.bin",
            "files/file1.bin",
            "files/file2.bin",
            "files/file3.bin",
            "files/file4.bin",
            "files/file5.bin"
    };

    public static void main(String[] args) throws IOException {
        for (String fileName : FILES) {
            int[] freq = byteFrequencies(fileName);
            System.out.printf(
                    "%s: Average: %.2f | Entropy: %.2f | Max frequency: %d%n",
                    fileName,
                    average(freq),
                    entropy(freq),
                    Arrays.stream(freq).max().orElse(0)
            );

        }

        String file = FILES[2];
        System.out.printf(
                "%nStem-Plot of %s:%n%s%n",
                file,
                stemPlot(byteFrequencies(file))
        );
    }

    public static int[] byteFrequencies(String fileName) throws IOException {
        try (FileInputStream input = new FileInputStream(fileName)) {
            int[] frequencies = new int[ARRAY_SIZE];

            int b;
            while ((b = input.read()) != -1)
                frequencies[b] += 1;

            return frequencies;
        }
    }

    public static double average(int[] freq) {
        double sum = 0;
        for (int i = 0; i < freq.length; i++) {
            sum += freq[i] * i;
        }
        return sum / freq.length;
    }

    public static double entropy(int[] freq) {
        int freqLength = Arrays.stream(freq).sum();

        double entropy = 0;
        for (int f : freq) {
            double p = (double) f / freqLength;
            entropy += p == 0f ? 0f : p * (Math.log(p) / Math.log(2));
        }
        return -entropy;
    }

    public static String stemPlot(int[] freq) {
        StringJoiner sj = new StringJoiner("\n");

        StringBuilder[] stems = new StringBuilder[(freq.length / 10) + 1];
        for (int i = 0; i < stems.length; i++) {
            stems[i] = new StringBuilder();
        }

        for (int i = 0; i < freq.length; i++) {
            for (int j = 0; j < freq[i]; j++) {
                stems[i / 10].append(i - ((i / 10) * 10));
            }
        }

        for (int i = 0; i < stems.length; i++) {
            sj.add(String.format(
                    "%02d | %s",
                    i, stems[i].toString()
            ));
        }

        return sj.toString();
    }
}
