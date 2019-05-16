package Utils;

public class Range<T extends Comparable<T>> {

    private final T lowerBorder;
    private final T upperBorder;

    public Range(T lower, T upper) {
        lowerBorder = lower;
        upperBorder = upper;
    }

    public boolean inside(T point) {
        return (lowerBorder.compareTo(point) <= 0 && upperBorder.compareTo(point) >= 0);
    }

    public T getLowerBorder() {
        return lowerBorder;
    }

    public T getUpperBorder() {
        return upperBorder;
    }
}
