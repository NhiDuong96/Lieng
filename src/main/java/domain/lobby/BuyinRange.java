package domain.lobby;

/**
 * Created by GSN on 11/4/2015.
 */
public class BuyinRange {
    long min;
    long max;

    public BuyinRange(long min, long max) {
        this.min = min;
        this.max = max;
    }

    public long getMin() {
        return min;
    }

    public void setMin(long min) {
        this.min = min;
    }

    public long getMax() {
        return max;
    }

    public void setMax(long max) {
        this.max = max;
    }

    public long getMid() {
        return (max + min) / 2;
    }
}
