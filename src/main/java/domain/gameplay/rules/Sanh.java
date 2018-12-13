package domain.gameplay.rules;

/**
 * Created by pc1 on 12/7/2018.
 */
public class Sanh extends HandEstimate {
    @Override
    public boolean isAccept() {
        return lienTiep();
    }

    @Override
    public float getValue() {
        return maxHand().value();
    }
}

