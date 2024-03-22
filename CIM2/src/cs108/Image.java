package cs108;

/**
 * Une image continue et infinie, représentée par une fonction associant une
 * couleur à chaque point du plan.
 */

@FunctionalInterface
public interface Image<T> {

    T apply(double x, double y);

    Image<ColorRGB> RED_DISK = (x, y) -> (
            Math.sqrt(x * x + y * y) <= 1d ? ColorRGB.RED : ColorRGB.WHITE
    );

    Image<Double> HORIZONTAL_GRADIENT_MASK = (x, y) -> (
            x < -1 ? 0 : x > 1 ? 1 : ((x + 1) / 2)
    );

    static Image<ColorRGB> chessboard(int size, ColorRGB odd, ColorRGB even) {
        if (size <= 0) throw new IllegalArgumentException("size needs to be >= 1");

        return ((x, y) -> Math.sin(size * x) * Math.sin(size * y) < 0 ? odd : even);
    }

    static Image<ColorRGB> composed(
            Image<ColorRGB> background, Image<ColorRGB> foreground, Image<Double> mask) {

        return ((x, y) -> background.apply(x, y).mixWith(foreground.apply(x, y), mask.apply(x, y)));
    }

    default Image<T> rotated(double angle) {
        double a = Math.toRadians(angle);
        return ((x, y) -> this.apply(
                (x * Math.cos(a)) + (y * Math.sin(a)),
                (x * Math.sin(a)) - (y * Math.cos(a))
        ));
    }

    static Image<Double> mandelbrot(int maxIterations) {
        return ((x, y) -> {
            double zR = 0;
            double zI = 0;
            int m = 0;
            while (Math.sqrt(zR*zR + zI*zI) < 2 && m < maxIterations) {
                double zRNew = (zR*zR - zI*zI) + x;
                double zINew = (2 * zR * zI) + y;

                zR = zRNew;
                zI = zINew;
                m++;
            }
            return m / (double) maxIterations;
        });
    }

    static <T> Image<T> constant(T value) {
        return ((x, y) -> value);
    }
}
