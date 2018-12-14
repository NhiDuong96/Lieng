package domain.lobby;

/**
 * Created by GSN on 11/2/2015.
 */
public class BlindLevel {
    long ante;
    long sidepot;

    public BlindLevel(long ante, long sidepot) {
        this.ante = ante;
        this.sidepot = sidepot;
    }

    public long getSidepot() {
        return sidepot;
    }

    public void setSidepot(long sidepot) {
        this.sidepot = sidepot;
    }

    public long getAnte() {
        return ante;
    }

    public void setAnte(long ante) {
        this.ante = ante;
    }

}
