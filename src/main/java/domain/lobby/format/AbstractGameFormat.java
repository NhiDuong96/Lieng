package domain.lobby.format;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by GSN on 11/4/2015.
 */
public abstract class AbstractGameFormat {
    String id;

    int maxPlayers;
    int maxSpectators;

    Set<String> roomNameSet = new HashSet<>();
    int channelID;
    boolean available;

    public AbstractGameFormat(String id, int maxPlayers, int maxSpectators) {
        this.id = id;
        this.maxPlayers = maxPlayers;
        this.maxSpectators = maxSpectators;
        this.roomNameSet.add("Room name");
        this.channelID = 0;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public int getMaxSpectators() {
        return maxSpectators;
    }

    public void setMaxSpectators(int maxSpectators) {
        this.maxSpectators = maxSpectators;
    }

    public Set<String> getRoomNameSet() {
        return roomNameSet;
    }

    public void setRoomNameSet(Set<String> roomNameSet) {
        this.roomNameSet = roomNameSet;
    }

    public int getChannelID() {
        return channelID;
    }

    public void setChannelID(int channelID) {
        this.channelID = channelID;
    }

    @Override
    public boolean equals(Object o) {
        AbstractGameFormat gameFormat = (AbstractGameFormat) o;
        return getId().compareTo(gameFormat.getId()) == 0;
    }
}
