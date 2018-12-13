/*
The MIT License (MIT)

Copyright (c) 2013 Jacob Kanipe-Illig

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
*/

package domain.gameplay.card;


import java.util.Arrays;
import java.util.Random;

/**
 * Standard deck of cards for poker. 52 Cards. 13 Clubs, Diamonds, Spades, and Hearts.
 *
 * @author jacobhyphenated
 */
public class Deck {
    private byte[] cards;
    private byte dealIndex = 0;

    /**
     * Construct a standard shuffled playing card deck.
     */
    public Deck() {
        this(true);
    }

    /**
     * Create a standard deck of playing cards
     *
     * @param shuffle true for shuffled deck.  False for not shuffled.
     */
    public Deck(boolean shuffle) {
        initDeck();
        if (shuffle) {
            shuffleDeck();
        }
    }

    public void initDeck() {
        cards = new byte[52];
        for(byte i = 0; i < 52; i++){
            cards[i] = i;
        }
    }

    public void shuffleDeck() {
        //shuffle cards
        shuffle(cards);
        dealIndex = 0;
    }

    private static void shuffle(byte cards[]){
        for(int i = cards.length - 1; i >= 1; i--){
            Random rand = new Random();
            int j = rand.nextInt(i+1);
            swap(cards, i, j);
        }
    }

    private static void swap(byte[] cards, int i, int j) {
        byte temp = cards[i];
        cards[i] = cards[j];
        cards[j] = temp;
    }

    public byte dealCard() {
        return cards[dealIndex++];
    }

    public byte[] dealCards(int num){
        byte[] cards = new byte[num];
        for(int i = 0; i < cards.length; i++){
            cards[i] = dealCard();
        }
        return cards;
    }
}
