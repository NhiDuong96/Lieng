package domain.gameplay.evaluator;


import domain.gameplay.holder.Hand;

public interface HandRankEvaluator {

    /**
     * Evaluates player's hand strength.  HandRank represents an absolute value representation of the hand strength
     */
    HandRank evaluate(Hand hand);
}
