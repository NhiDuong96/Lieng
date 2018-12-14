package domain;

import bitzero.framework.util.FrameworkUtils;

/**
 * Created by niennd on 5/1/2016.
 */
public class MaintainServiceImpl implements MaintainService {
    static MaintainServiceImpl instance = new MaintainServiceImpl();
    volatile long startMaintainTimeStamp;
    long maintainDelay;

    public MaintainServiceImpl() {
        this.startMaintainTimeStamp = 0;
        this.maintainDelay = 0;
    }

    public static MaintainServiceImpl getInstance() {
        return instance;
    }

    @Override
    public boolean isUnderMaintain() {
        return startMaintainTimeStamp > 0;
    }

    @Override
    public int getDownTimeRemain() {
        if (this.startMaintainTimeStamp <= 0) {
            return -1;
        }
        long dTime = this.startMaintainTimeStamp + this.maintainDelay - FrameworkUtils.currentTimeInSecond();
        return dTime < 0 ? 0 : (int) dTime;
    }

    @Override
    public void startMaintain(int delayInSec) {
        this.maintainDelay = delayInSec;
        this.startMaintainTimeStamp = FrameworkUtils.currentTimeInSecond();
    }

    @Override
    public void stopMaintain() {
        this.startMaintainTimeStamp = 0;
        this.maintainDelay = 0;
    }

}
