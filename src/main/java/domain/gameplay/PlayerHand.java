package domain.gameplay;

import domain.gameplay.evaluator.HandRank;
import domain.gameplay.holder.Hand;

/**
 * Created by pc1 on 12/12/2018.
 */
public class PlayerHand {
    private Hand hand;

    private Player player;

    private HandRank handRank;

    public PlayerHand() {
    }

    public Hand getHand() {
        return hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public HandRank getHandRank() {
        return handRank;
    }

    public void setHandRank(HandRank handRank) {
        this.handRank = handRank;
    }
}
