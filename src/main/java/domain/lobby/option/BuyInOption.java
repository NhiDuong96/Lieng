package domain.lobby.option;

/**
 * Created by GSN on 11/4/2015.
 */
public class BuyInOption {
    long buyinChips;
    boolean autoBuyin;

    public BuyInOption(long buyinChips, boolean autoBuyin) {
        this.buyinChips = buyinChips;
        this.autoBuyin = autoBuyin;
    }

    public long getBuyinChips() {
        return buyinChips;
    }

    public void setBuyinChips(long buyinChips) {
        this.buyinChips = buyinChips;
    }

    public boolean isAutoBuyin() {
        return autoBuyin;
    }

    public void setAutoBuyin(boolean autoBuyin) {
        this.autoBuyin = autoBuyin;
    }

    @Override
    public String toString() {
        return "BuyInOption{" +
                "buyinChips=" + buyinChips +
                ", autoBuyin=" + autoBuyin +
                '}';
    }
}
