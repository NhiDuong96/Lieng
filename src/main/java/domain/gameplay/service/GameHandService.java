package domain.gameplay.service;

import domain.gameplay.CashGameImpl;
import domain.gameplay.Game;
import domain.gameplay.HandEntity;

/**
 * Created by GSN on 11/1/2015.
 */
public interface GameHandService {
    HandEntity startNewHand(CashGameImpl game);

    void endHand(HandEntity hand);
}
