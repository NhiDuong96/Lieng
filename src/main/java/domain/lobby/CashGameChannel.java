package domain.lobby;

import domain.lobby.format.CashGameFormat;

import java.util.Map;
import java.util.Set;

/**
 * Created by GSN on 11/11/2015.
 */
public class CashGameChannel {
    int id;
    String name;
    long minGoldRequire;
    Map<String, CashGameFormat> gameFormats;

    Set<String> setRoomNames;

    boolean available;

    int playerTimeOut;
    int fastPlayerTimeOut;
    int nonInterActiveTimeOut;

    public CashGameChannel(int id, String name, long minGoldRequire, Map<String, CashGameFormat> gameFormats, Set<String> setRoomNames, boolean available, int playerTimeOut, int fastPlayerTimeOut, int nonInterActiveTimeOut) {
        this.id = id;
        this.name = name;
        this.minGoldRequire = minGoldRequire;
        this.gameFormats = gameFormats;
        this.setRoomNames = setRoomNames;
        this.available = available;
        this.playerTimeOut = playerTimeOut;
        this.fastPlayerTimeOut = fastPlayerTimeOut;
        this.nonInterActiveTimeOut = nonInterActiveTimeOut;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getMinGoldRequire() {
        return minGoldRequire;
    }

    public void setMinGoldRequire(long minGoldRequire) {
        this.minGoldRequire = minGoldRequire;
    }

    public Map<String, CashGameFormat> getGameFormats() {
        return gameFormats;
    }

    public void setGameFormats(Map<String, CashGameFormat> gameFormats) {
        this.gameFormats = gameFormats;
    }

    public Set<String> getSetRoomNames() {
        return setRoomNames;
    }

    public void setSetRoomNames(Set<String> setRoomNames) {
        this.setRoomNames = setRoomNames;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public int getPlayerTimeOut() {
        return playerTimeOut;
    }

    public void setPlayerTimeOut(int playerTimeOut) {
        this.playerTimeOut = playerTimeOut;
    }

    public int getFastPlayerTimeOut() {
        return fastPlayerTimeOut;
    }

    public void setFastPlayerTimeOut(int fastPlayerTimeOut) {
        this.fastPlayerTimeOut = fastPlayerTimeOut;
    }

    public int getNonInterActiveTimeOut() {
        return nonInterActiveTimeOut;
    }

    public void setNonInterActiveTimeOut(int nonInterActiveTimeOut) {
        this.nonInterActiveTimeOut = nonInterActiveTimeOut;
    }
}
