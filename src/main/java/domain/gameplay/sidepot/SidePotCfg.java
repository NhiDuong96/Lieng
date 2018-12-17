package domain.gameplay.sidepot;

import domain.gameplay.card.HandType;

import java.util.List;
import java.util.Map;

/**
 * Created by NienND on 1/17/2018.
 */
public class SidePotCfg {
    public static final int top_split = 2;
    int shareFullLimitRound;
    List<Integer> shareRateAfterLimitedRound;

    Map<HandType, Map<HandType, Integer[]>> shareHandType;

    public SidePotCfg(int shareFullLimitRound, List<Integer> shareRateAfterLimitedRound, Map<HandType, Map<HandType, Integer[]>> shareHandType) {
        this.shareFullLimitRound = shareFullLimitRound;
        this.shareRateAfterLimitedRound = shareRateAfterLimitedRound;
        this.shareHandType = shareHandType;
    }

    public int getShareFullLimitRound() {
        return shareFullLimitRound;
    }

    public void setShareFullLimitRound(int shareFullLimitRound) {
        this.shareFullLimitRound = shareFullLimitRound;
    }

    public List<Integer> getShareRateAfterLimitedRound() {
        return shareRateAfterLimitedRound;
    }

    public void setShareRateAfterLimitedRound(List<Integer> shareRateAfterLimitedRound) {
        this.shareRateAfterLimitedRound = shareRateAfterLimitedRound;
    }

    public Map<HandType, Map<HandType, Integer[]>> getShareHandType() {
        return shareHandType;
    }

    public void setShareHandType(Map<HandType, Map<HandType, Integer[]>> shareHandType) {
        this.shareHandType = shareHandType;
    }
}
