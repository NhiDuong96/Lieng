package domain.gameplay;

import bitzero.server.entities.User;

/**
 * Created by pc1 on 12/13/2018.
 */
public class Player {
    private User user;

    private long chips;

    private long buyInChips;

    private int gamePosition;

    private long betValue;

    private long anteValue;

    private long timeJoinGame;

    public Player(User user){
        this.user = user;
    }

    public Player(User user, long buyInChips){
        this(user);
        this.buyInChips = buyInChips;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getTimeJoinGame() {
        return timeJoinGame;
    }

    public void setTimeJoinGame(long timeJoinGame) {
        this.timeJoinGame = timeJoinGame;
    }
}
