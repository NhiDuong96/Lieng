package domain.lobby.option;

/**
 * Created by niennd on 7/3/2017.
 */
public class PlayMode {
    boolean lightMode;
    boolean proMode;

    public PlayMode(boolean lightMode, boolean proMode) {
        this.lightMode = lightMode;
        this.proMode = proMode;
    }

    public PlayMode(byte code){
        if(code == 0){
            lightMode = true;
            proMode = false;
        }else if(code == 1){
            lightMode = false;
            proMode = true;
        }
    }

    public boolean isLightMode() {
        return lightMode;
    }

    public void setLightMode(boolean lightMode) {
        this.lightMode = lightMode;
    }

    public boolean isProMode() {
        return proMode;
    }

    public void setProMode(boolean proMode) {
        this.proMode = proMode;
    }

    @Override
    public boolean equals(Object obj) {
        PlayMode playMode = (PlayMode) obj;
        return isLightMode() == playMode.isLightMode()
                && isProMode() == playMode.isProMode();
    }

    public int getValueInt() {
        if ( !proMode && !lightMode ) return 1;
        if (!proMode) return 2;
        if (!lightMode) return 3;
        return 4;
    }
}
