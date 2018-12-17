package domain.gameplay.holder;

import cmd.api.ApiEntity;
import cmd.api.ApiField;

/**
 * Created by pc1 on 12/12/2018.
 */
@ApiEntity
public class Hand {
    private long cards;

    public Hand(){
        cards = 0L;
    }

    public Hand(byte[] cards){
        addCards(cards);
    }

    public void addCard(byte card){
        cards |= (1L << card);
    }

    public void addCards(byte[] cards){
        for (byte card : cards) {
            this.cards |= (1L << card);
        }
    }

    public void removeCard(byte card){
        cards &= ~(1L << card);
    }

    public void removeCards(byte[] cards){
        for (byte card : cards) {
            this.cards &= ~card;
        }
    }

    public int getCardsNumber(){
        return Long.bitCount(cards);
    }

    @ApiField
    public long getValue(){
        return cards;
    }
}
