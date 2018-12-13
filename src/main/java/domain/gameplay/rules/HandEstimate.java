package domain.gameplay.rules;

import bitzero.server.Main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by pc1 on 12/7/2018.
 */
public abstract class HandEstimate {
    //1 <= c <= 13
    //0 <= t <= 3
    //0 <= getChatValue(t) <= 100

    protected int c1, c2, c3;
    protected int t1,t2,t3;

    class Hand{
        int c,t;
        Hand(int c, int t){
            this.c = c;
            this.t = t;
        }

        float value(){
            return c*100.0f + t;
        }
    }

    List<Hand> hands = new ArrayList<>();

    private int rightMostBitPos(long n){
        if((n&1) == 1) return 1;
        return (int) (Math.log(n & -n)/Math.log(2) + 1);
    }

    public void view(long key){
        hands.clear();
        c1 = rightMostBitPos(key) - 1; key &= ~(1L << c1);
        c2 = rightMostBitPos(key) - 1; key &= ~(1L << c2);
        c3 = rightMostBitPos(key) - 1;

        //chat
        t1 = c1%4;
        t2 = c2%4;
        t3 = c3%4;
        //vitri
        c1 = c1 / 4 + 1;
        c2 = c2 / 4 + 1;
        c3 = c3 / 4 + 1;

        hands.add(new Hand(c1,t1));
        hands.add(new Hand(c2,t2));
        hands.add(new Hand(c3,t3));
    }

    protected boolean lienTiep(){
        int max = Math.max(Math.max(c1,c2),c3);
        int min = Math.min(Math.min(c1,c2),c3);
        int tb = (c1+c2+c3)- (max+min);
        return (max - tb == 1) && (tb - min == 1);
    }

    protected boolean dongChat(){
        return t1 == t2 && t1 == t3;
    }

    public int allChatValue(){
        return getChatValue(t1)+ getChatValue(t2)+ getChatValue(t3);
    }

    public int getChatValue(int c){
        switch (c){
            case 0:
                return 0;
            case 1:
                return 1;
            case 2:
                return 10;
            case 3:
                return 100;
        }
        System.out.println("error chat");
        return 1;
    }

    protected Hand maxHand(){
        Hand max = hands.get(0);
        for(int i = 1; i < hands.size(); i++){
            Hand hand = hands.get(i);
            if(max.value() < hand.value()){
                max = hand;
            }
        }
        return max;
    }


    public abstract boolean isAccept();
    public abstract float getValue();

//    public static void main(String[] args) {
//        Xam xam = new Xam();
//        xam.view(1442559255642112L);
//    }
}
