package cs108;

public final class Rotated<T> implements Image<T> {
    private final Image<T> image;
    private final double angle;

    public Rotated(Image<T> image, double angle) {
        if (angle < 0 || angle > 360) throw new IllegalArgumentException("Invalid angle (deg)");
        this.image = image;
        this.angle = (angle * Math.PI) / 180;
    }

    @Override
    public T apply(double x, double y) {
        return image.apply(
                (x * Math.cos(this.angle)) + (y * Math.sin(this.angle)),
                (x * Math.sin(this.angle)) - (y * Math.cos(this.angle))
                );
    }
}
