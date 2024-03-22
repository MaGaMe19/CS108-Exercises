package cs108;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import static javax.swing.SwingUtilities.invokeLater;

public final class Main {
    public static void main(String[] args) {
        /*Image<ColorRGB> redDisk = Image.RED_DISK;
        Image<ColorRGB> chessboard =
                Image.chessboard(7, ColorRGB.PURPLE, ColorRGB.YELLOW)
                        .rotated(10);
        Image<Double> mask = Image.HORIZONTAL_GRADIENT_MASK;
        Image<ColorRGB> composition = Image.composed(redDisk, chessboard, mask);*/

        Image<ColorRGB> c1 = Image.constant(ColorRGB.YELLOW);
        Image<ColorRGB> c2 = Image.constant(ColorRGB.BLUE);
        Image<Double> mandelbrotMask = Image.mandelbrot(100);
        Image<ColorRGB> mandelbrot = Image.composed(c1, c2, mandelbrotMask);

        Image<ColorRGB> image = mandelbrot;

        invokeLater(() -> {
            var mainWindow = new JFrame("Image viewer");
            mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            mainWindow.getContentPane().setLayout(new BorderLayout());
            var imageComponent = new ImageComponent(image, 0, 0, 4);
            mainWindow.getContentPane().add(imageComponent, BorderLayout.CENTER);

            mainWindow.pack();
            mainWindow.setVisible(true);
        });
    }

    private static final class ImageComponent extends JComponent {
        private final Image<ColorRGB> image;
        private final double centerX, centerY;
        private final double width;

        public ImageComponent(Image<ColorRGB> image, double centerX, double centerY, double width) {
            this.image = image;
            this.centerX = centerX;
            this.centerY = centerY;
            this.width = width;
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(400, 280);
        }

        @Override
        protected void paintComponent(Graphics g0) {
            var g = (Graphics2D) g0;

            var jImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
            var inc = width / getWidth();
            var xMin = centerX - inc * (getWidth() / 2.0);
            var yMax = centerY + inc * (getHeight() / 2.0);
            for (var imageX = 0; imageX < getWidth(); imageX += 1) {
                var x = xMin + inc * imageX;
                for (var imageY = 0; imageY < getHeight(); imageY += 1) {
                    var y = yMax - inc * imageY;
                    jImage.setRGB(imageX, imageY, image.apply(x, y).toAWTColor().getRGB());
                }
            }

            g.drawImage(jImage, 0, 0, null);
        }
    }
}
