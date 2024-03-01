package cs108;

public class Chessboard implements Image<ColorRGB> {
    private final double size;
    private final ColorRGB odd;
    private final ColorRGB even;
    public static final Image<ColorRGB> IMAGE = new Chessboard(
            5,
            ColorRGB.BLACK,
            ColorRGB.WHITE
    );

    /**
     * Creates a new chessboard texture, note that size is inverted, meaning large values yield
     * small checkers.
     * @param size (double)
     * @param odd (ColorRGB)
     * @param even (ColorRGB)
     */
    public Chessboard(double size, ColorRGB odd, ColorRGB even) {
        if (size <= 0) throw new IllegalArgumentException("size needs to be >= 1");

        this.size = size;
        this.odd = odd;
        this.even = even;
    }

    @Override
    public ColorRGB apply(double x, double y) {
        return
                (Math.sin(this.size * x) * Math.sin(this.size * y)) < 0
                ? this.odd
                : this.even;
    }
}
