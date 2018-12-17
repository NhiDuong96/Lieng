package domain.gameplay;

import bitzero.server.entities.User;
import cmd.api.ApiEntity;
import cmd.api.ApiField;
import domain.gameplay.holder.Hand;

/**
 * Created by pc1 on 12/13/2018.
 */
@ApiEntity
public class Player implements Comparable<Player>{
    private User user;

    private Game game;

    private long chips;

    private long buyInChips;

    private int gamePosition;

    private long betValue;

    private long anteValue;

    private long timeJoinGame;

    private Hand hand;

    public Player(User user){
        this.user = user;
    }

    public Player(User user, long buyInChips){
        this(user);
        this.buyInChips = buyInChips;
        this.chips = buyInChips;
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

    @ApiField
    public int getId() {
        return user.getId();
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    @ApiField
    public String getPlayerName(){
        return user.getName();
    }

    @ApiField
    public int getGamePosition() {
        return gamePosition;
    }

    public void setGamePosition(int gamePosition) {
        this.gamePosition = gamePosition;
    }

    @ApiField
    public long getChips() {
        return chips;
    }

    public void setChips(long chips) {
        this.chips = chips;
    }

    @ApiField
    public Hand getHand() {
        return hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }

    @Override
    public int compareTo(Player o) {
        return getGamePosition() - o.getGamePosition();
    }
}
