package domain.gameplay;

import domain.lobby.BlindLevel;
import domain.lobby.option.PlayMode;

/**
 * Created by GSN on 11/2/2015.
 */
public class GameStructure {
    String id;
    GameType gameType;
    BlindLevel currentBlindLevel;
    int maxPlayers;
    int channelID;
    int defaultPlayerTimeout;
    PlayMode playMode;

    public GameStructure(String id, GameType gameType, BlindLevel currentBlindLevel, int maxPlayers, int channelID, int defaultPlayerTimeout, PlayMode playMode) {
        this.id = id;
        this.gameType = gameType;
        this.currentBlindLevel = currentBlindLevel;
        this.maxPlayers = maxPlayers;
        this.channelID = channelID;
        this.defaultPlayerTimeout = defaultPlayerTimeout;
        this.playMode = playMode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BlindLevel getCurrentBlindLevel() {
        return currentBlindLevel;
    }

    public void setCurrentBlindLevel(BlindLevel currentBlindLevel) {
        this.currentBlindLevel = currentBlindLevel;
    }


    public GameType getGameType() {
        return gameType;
    }

    public void setGameType(GameType gameType) {
        this.gameType = gameType;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public int getChannelID() {
        return channelID;
    }

    public void setChannelID(int channelID) {
        this.channelID = channelID;
    }

    public int getDefaultPlayerTimeout() {
        return defaultPlayerTimeout;
    }

    public void setDefaultPlayerTimeout(int defaultPlayerTimeout) {
        this.defaultPlayerTimeout = defaultPlayerTimeout;
    }

    public PlayMode getPlayMode() {
        return playMode;
    }

    public void setPlayMode(PlayMode playMode) {
        this.playMode = playMode;
    }

    @Override
    public GameStructure clone(){
        BlindLevel cl_BlindLevel= new BlindLevel(this.currentBlindLevel.getAnte(), this.currentBlindLevel.getSidepot());
        return new GameStructure(this.id, this.gameType,cl_BlindLevel, maxPlayers, channelID, defaultPlayerTimeout, playMode);
    }
}