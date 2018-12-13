package domain.gameplay.rules;

/**
 * Created by pc1 on 12/7/2018.
 */
public class MatNguoi extends HandEstimate {
    @Override
    public boolean isAccept() {
        return (c1 >= 10 && c1 <= 12) && (c2 >= 10 && c2 <= 12) && (c3 >= 10 && c3 <= 12);
    }

    @Override
    public float getValue() {
        return maxHand().value();
    }
}
