package cs108;

import java.awt.image.BufferedImage;
import java.util.Arrays;

public final class Steganographer {
    private Steganographer() {
    }

    public static String extract(BufferedImage image) {
        final int IMAGE_WIDTH = image.getWidth();
        final int IMAGE_HEIGHT = image.getHeight();
        final int MAX_BITS = IMAGE_WIDTH * IMAGE_HEIGHT;

        byte[] bits = new byte[MAX_BITS];
        for (int row = 0; row < IMAGE_HEIGHT; row++) {
            for (int col = 0; col < IMAGE_WIDTH; col++) {
                int color = image.getRGB(col, row);
                bits[row * IMAGE_WIDTH + col] = (byte) (color & 1);
            }
        }


        int validCharCount = 0;
        char[] chars = new char[MAX_BITS / Character.SIZE];
        for (int i = 0; i < MAX_BITS / Character.SIZE; i++) {
            char temp = 0;
            for (int j = 0; j < Character.SIZE; j++) {
                temp = (char) (temp | (bits[(i * Character.SIZE) + j] << (Character.SIZE - j - 1)));
            }

            chars[i] = temp;
            if (temp != 0)
                validCharCount++;
        }

        return String.valueOf(Arrays.copyOfRange(chars, 0, validCharCount));
    }

    public static BufferedImage insert(BufferedImage image, String string) {
        char[] chars = string.toCharArray();

        final int IMAGE_WIDTH = image.getWidth();
        final int IMAGE_HEIGHT = image.getHeight();
        final int MAX_BITS = IMAGE_WIDTH * IMAGE_HEIGHT;

        byte[] bits = new byte[MAX_BITS];

        for (int i = 0; i < chars.length; i++) {
            for (int j = 0; j < Character.SIZE; j++) {
                int index = (i * Character.SIZE) + j;
                if (index < MAX_BITS)
                    bits[index] = (byte) ((chars[i] >> (Character.SIZE - j - 1)) & 1);
            }
        }

        for (int row = 0; row < IMAGE_HEIGHT; row++) {
            for (int col = 0; col < IMAGE_WIDTH; col++) {
                image.setRGB(
                        col, row,
                        ((image.getRGB(col, row) >> 1) << 1) | bits[row * IMAGE_WIDTH + col]
                );
            }
        }

        return image;
    }
}