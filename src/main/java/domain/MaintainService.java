package domain;

/**
 * Created by niennd on 5/1/2016.
 */
public interface MaintainService {
    boolean isUnderMaintain();

    int getDownTimeRemain();

    void startMaintain(int delayInSec);

    void stopMaintain();
}
