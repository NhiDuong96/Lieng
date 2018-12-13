package domain.gameplay.rules;

/**
 * Created by pc1 on 12/7/2018.
 */
public class Thung extends HandEstimate {
    @Override
    public boolean isAccept() {
        return dongChat();
    }

    @Override
    public float getValue() {
        int maxC = Math.max(Math.max(c1, c2), c3);
        return getChatValue(t1)*100.0f + maxC;
    }
}
