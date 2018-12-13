package domain.gameplay.rules;

/**
 * Created by pc1 on 12/7/2018.
 */
public class Xam extends HandEstimate {
    @Override
    public boolean isAccept() {
        return c1 == c2 && c1 == c3;
    }

    @Override
    public float getValue() {
        if(c1 == 2) return 100;//con 3
        else return c1;
    }
}
