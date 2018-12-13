//package domain.lobby.format;
//
//import config.GameConfig;
//import domain.lobby.BlindLevel;
//import domain.lobby.BuyinRange;
//
///**
// * Created by GSN on 11/4/2015.
// */
//public class CashGameFormat extends AbstractGameFormat implements Comparable {
//    BlindLevel blindLevel;
//    BuyinRange buyinRange;
//    boolean lightFormat;
//    boolean proModeFormat;
//
//    public CashGameFormat(String id, int maxPlayers, int maxSpectators, BlindLevel blindLevel, BuyinRange buyinRange, boolean lightFormat, boolean proModeFormat) {
//        super(id, maxPlayers, maxSpectators);
//        this.blindLevel = blindLevel;
//        this.buyinRange = buyinRange;
//        this.lightFormat = lightFormat;
//        this.proModeFormat = proModeFormat;
//    }
//
//    public BlindLevel getBlindLevel() {
//        return blindLevel;
//    }
//
//    public void setBlindLevel(BlindLevel blindLevel) {
//        this.blindLevel = blindLevel;
//    }
//
//    public BuyinRange getBuyinRange() {
//        return buyinRange;
//    }
//
//    public void setBuyinRange(BuyinRange buyinRange) {
//        this.buyinRange = buyinRange;
//    }
//
//    public boolean isLightFormat() {
//        return lightFormat;
//    }
//
//    public void setLightFormat(boolean lightFormat) {
//        this.lightFormat = lightFormat;
//    }
//
//    public boolean isProModeFormat() {
//        return proModeFormat;
//    }
//
//    public void setProModeFormat(boolean proModeFormat) {
//        this.proModeFormat = proModeFormat;
//    }
//
//    @Override
//    public int compareTo(Object o) {
//        CashGameFormat cashGameFormat = (CashGameFormat) o;
//        if (getBlindLevel().getAnte() > cashGameFormat.getBlindLevel().getAnte()) {
//            return -1 * GameConfig.OPTIMIZE_CASH_MATCHING_ORDER;
//        } else if (getBlindLevel().getAnte() < cashGameFormat.getBlindLevel().getAnte()) {
//            return 1 * GameConfig.OPTIMIZE_CASH_MATCHING_ORDER;
//        }
//        return 0;
//    }
//}
