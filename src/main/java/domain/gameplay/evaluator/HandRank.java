package domain.gameplay.evaluator;

/**
 * Created by GSN on 11/1/2015.
 */

import domain.gameplay.card.HandType;

/**
 * Representation of the poker hand strength.
 */
public class HandRank implements Comparable<HandRank> {

    private final int groupValue;
    private final int mainValue;

    public HandRank(int mainValue, int groupValue) {
        super();
        this.groupValue = groupValue;
        this.mainValue = mainValue;
    }

    @Override
    public final int compareTo(HandRank rank) {
        return -(getMainValue() - rank.getMainValue());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HandRank handRank = (HandRank) o;
        return handRank.getMainValue() == getMainValue();
    }

    public int getMainValue() {
        return mainValue;
    }

    public HandType getHandType() {
        return HandType.values()[groupValue];
    }

    @Override
    public String toString() {
        return "HandRank{" +
                "groupValue=" + groupValue +
                ", mainValue=" + mainValue +
                '}';
    }
}