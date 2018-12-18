package domain.gameplay.holder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by pc1 on 12/12/2018.
 */
public class HandUtils {
    private static Map<Long, Byte> CARD_INDEX_MAP = new HashMap<>();

    static{
        for(byte i = 0; i < 52; i++){
            CARD_INDEX_MAP.put((1L << i), i);
        }
    }

    public static byte getCardIndex(long card){
      return CARD_INDEX_MAP.get(card);
    }

    public static List<Byte> getCardsList(Hand cards){
        List<Byte> list = new ArrayList<>();
        long value = cards.getValue();
        while (value != 0L){
            long card = Long.lowestOneBit(value);
            list.add(getCardIndex(card));
            value &= ~card;
        }
        return list;
    }

    public static byte[] getCardsArray(Hand cards){
        byte[] list = new byte[cards.getCardsNumber()];
        long value = cards.getValue();
        int i = 0;
        while (value != 0L){
            long card = Long.lowestOneBit(value);
            list[i++] = getCardIndex(card);
            value &= ~card;
        }
        return list;
    }
}
