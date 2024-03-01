package cs108;

public class HorizontalGradientMask implements Image<Double> {

    @Override
    public Double apply(double x, double y) {
        return x < -1 ? 0 : x > 1 ? 1 : ((x + 1) / 2);
    }
}
