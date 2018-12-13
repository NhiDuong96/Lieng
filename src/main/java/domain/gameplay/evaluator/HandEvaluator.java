package domain.gameplay.evaluator;


import config.GameConfig;
import domain.gameplay.holder.Hand;

import java.util.Map;

/**
 * Created by GSN on 12/4/2015.
 */
public class HandEvaluator implements HandRankEvaluator {

    public static Map<Long, HandRank> handRanks;
    static HandRankEvaluator instance = new HandEvaluator();

    public HandEvaluator() {
        handRanks = GameConfig.HAND_RANKS;
    }

    public static HandRankEvaluator getInstance() {
        return instance;
    }

    /**
     * Evaluates player's hand strength.  HandRank represents an absolute value representation of the hand strength
     *
     * @param hand
     */
    @Override
    public HandRank evaluate(Hand hand) {
        return handRanks.get(hand.getValue());
    }
}
