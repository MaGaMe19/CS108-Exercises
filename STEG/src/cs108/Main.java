package cs108;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public final class Main {
    public static void main(String[] args) throws IOException {
        String text;

        try (FileReader r = new FileReader("star_wars.txt", StandardCharsets.US_ASCII)) {
            StringBuilder builder = new StringBuilder();
            int b;
            while ((b = r.read()) != -1)
                builder.append((char) b);

            text = builder.toString();

        } catch (Exception e) {
            text = "Hello, World!";
        }

        BufferedImage image = ImageIO.read(new File("61.jpg"));

        System.out.println(STR."Text to insert: \n\{text}");
        BufferedImage insertedImage = Steganographer.insert(image, text);

        ImageIO.write(insertedImage, "jpg", new File("61_mod.jpg"));

        String extracted = Steganographer.extract(insertedImage);
        System.out.println(STR."Extracted text: \n\{extracted}");
    }
}
