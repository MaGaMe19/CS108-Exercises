package cs108;

public class Composed implements Image<ColorRGB> {
    private final Image<ColorRGB> background;
    private final Image<ColorRGB> foreground;
    private final Image<Double> mask;

    public Composed(Image<ColorRGB> background, Image<ColorRGB> foreground, Image<Double> mask) {
        this.background = background;
        this.foreground = foreground;
        this.mask = mask;
    }

    @Override
    public ColorRGB apply(double x, double y) {
        return background.apply(x, y).mixWith(
                foreground.apply(x, y), mask.apply(x, y)
        );
    }
}
