package domain.gameplay.rules;

/**
 * Created by pc1 on 12/7/2018.
 */
public class Diem extends HandEstimate {
    @Override
    public boolean isAccept() {
        return true;
    }

    @Override
    public float getValue() {
        if(c1 >= 9 && c1 <= 12) c1 = 0;
        else if(c1 == 13) c1 = 1;
        else c1++;
        if(c2 >= 9 && c2 <= 12) c2 = 0;
        else if(c2 == 13) c2 = 1;
        else c2++;
        if(c3 >= 9 && c3 <= 12) c3 = 0;
        else if(c3 == 13) c3 = 1;
        else c3++;

        Hand max = maxHand();
        return ((c1+c2+c3)%10)*10000.0f + max.value();
    }
}
