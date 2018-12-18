package domain.gameplay;

import domain.gameplay.evaluator.HandRank;
import domain.gameplay.holder.Hand;

/**
 * Created by pc1 on 12/12/2018.
 */
public class PlayerHand {
    private Hand hand;

    private HandRank handRank;

    private long roundBetAmount;

    public PlayerHand() {
    }

    public Hand getHand() {
        return hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }

    public HandRank getHandRank() {
        return handRank;
    }

    public void setHandRank(HandRank handRank) {
        this.handRank = handRank;
    }

    public long getRoundBetAmount() {
        return roundBetAmount;
    }

    public void setRoundBetAmount(long roundBetAmount) {
        this.roundBetAmount = roundBetAmount;
    }

}
