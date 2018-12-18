package domain.gameplay;

import bitzero.server.entities.User;
import cmd.api.ApiEntity;
import cmd.api.ApiField;
import domain.gameplay.holder.Hand;
import domain.gameplay.util.PlayerUtil;

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

    private PlayerHand playerHand;

    private long timeStartSittingOut;

    private volatile boolean beingActing;
    private boolean sittingOut;
    private boolean acted;

    public Player(User user){
        this.user = user;
    }

    public Player(User user, long buyInChips){
        this(user);
        this.buyInChips = buyInChips;
        this.chips = buyInChips;
        betValue = 0;
        sittingOut = false;
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
        if(playerHand == null) return null;
        return playerHand.getHand();
    }

    public PlayerHand getPlayerHand() {
        return playerHand;
    }

    public boolean isBeingActing() {
        return beingActing;
    }

    public void setBeingActing(boolean beingActing) {
        this.beingActing = beingActing;
    }

    public void setPlayerHand(PlayerHand hand) {
        this.playerHand = hand;
    }

    public boolean isSittingOut() {
        return sittingOut;
    }

    public void setSittingOut(boolean sittingOut) {
        this.sittingOut = sittingOut;
        if (sittingOut) {
            setTimeStartSittingOut(getGame().getTimer());
        } else {
            setTimeStartSittingOut(0);
        }
    }

    public void setTimeStartSittingOut(long timeStartSittingOut) {
        this.timeStartSittingOut = timeStartSittingOut;
    }

    @Override
    public int compareTo(Player o) {
        return getGamePosition() - o.getGamePosition();
    }

    @ApiField
    public byte getPlayerStatusCode(){
        return PlayerUtil.getPlayerStatus(this).getCode();
    }

    public void addBetAmount(long value) {
        if (value > 0) {
            betValue += value;
//            getGame().getCurrentHand().mapBettingDetail.put(getClientGamePosition(), betValue);
        } else {
//            LogicLogger.getInstance().trace("Error! add betting mount", this, value);
        }
    }

    @ApiField
    public long getBetValue() {
        return betValue;
    }

    public void setBetValue(long betValue) {
        this.betValue = betValue;
    }

    public boolean isActed() {
        return acted;
    }

    public void setActed(boolean acted) {
        this.acted = acted;
    }
}
