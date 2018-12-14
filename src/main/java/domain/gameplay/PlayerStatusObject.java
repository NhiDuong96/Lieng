package domain.gameplay;

import cmd.api.ApiEntity;
import cmd.api.ApiField;
import domain.gameplay.holder.Hand;

/**
 * Created by GSN on 11/1/2015.
 */
@ApiEntity
public class PlayerStatusObject {
    private int id;
    private byte gamePosition;
    private String name;
    private PlayerStatus status;
    private long chips;
    private Hand hand;
    private long amountBetRound;
    private long amountToCall;
    private String defaultAvatar;
    private int level;
    private int vip;
    private int showingBracelet;

    public PlayerStatusObject() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ApiField
    public byte getGamePosition() {
        return gamePosition;
    }

    public void setGamePosition(byte gamePosition) {
        this.gamePosition = gamePosition;
    }

    @ApiField
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PlayerStatus getStatus() {
        return status;
    }

    public void setStatus(PlayerStatus status) {
        this.status = status;
    }

    @ApiField
    public long getChips() {
        return chips;
    }

    public void setChips(long chips) {
        this.chips = chips;
    }

    public Hand getHand() {
        return hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }

    public long getAmountBetRound() {
        return amountBetRound;
    }

    public void setAmountBetRound(long amountBetRound) {
        this.amountBetRound = amountBetRound;
    }

    public long getAmountToCall() {
        return amountToCall;
    }

    public void setAmountToCall(long amountToCall) {
        this.amountToCall = amountToCall;
    }

    public String getDefaultAvatar() {
        return defaultAvatar;
    }

    public void setDefaultAvatar(String defaultAvatar) {
        this.defaultAvatar = defaultAvatar;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getVip() {
        return vip;
    }

    public void setVip(int vip) {
        this.vip = vip;
    }

    public int getShowingBracelet() {
        return showingBracelet;
    }

    public void setShowingBracelet(int showingBracelet) {
        this.showingBracelet = showingBracelet;
    }

    @Override
    public String toString() {
        return "PlayerStatusObject{" +
                "id=" + id +
                ", gamePosition=" + gamePosition +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", chips=" + chips +
                ", hand=" + hand +
                ", amountBetRound=" + amountBetRound +
                ", amountToCall=" + amountToCall +
                '}';
    }
}
