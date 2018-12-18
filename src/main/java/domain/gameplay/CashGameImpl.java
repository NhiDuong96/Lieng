package domain.gameplay;

import bitzero.server.entities.Room;
import cmd.api.ApiEntity;
import cmd.api.ApiField;
import config.GameConfig;
import domain.gameplay.service.GameHandServiceImpl;
import domain.gameplay.util.GameUtil;
import domain.lobby.BlindLevel;

import java.util.Set;

/**
 * Created by pc1 on 12/17/2018.
 */
@ApiEntity
public class CashGameImpl extends Game{
    GameStructure gameStructure;
    HandEntity currentHand;

    Player firstBetPlayer;
    boolean checkShowedLastCard;

    public CashGameImpl(Room room, GameStructure gameStructure) {
        super(room);
        this.gameStructure = gameStructure;
    }

    public void setNewHandEntityDelay(int totalWinner){
        setActionTimeout(() -> {
            System.out.println("start new hand entity");
            //create new hand entity here
            HandEntity hand = GameHandServiceImpl.getInstance().startNewHand(this);
            if(hand == null){
                //todo
            }
        }, GameConfig.NEW_HAND_DELAY_SEC + GameConfig.WINNER_EFFECT_DELAY_SEC * totalWinner);
    }

    @ApiField
    public long getBlindAnte(){
        return gameStructure.getCurrentBlindLevel().getAnte();
    }

    @ApiField
    public HandEntity getCurrentHand() {
        return currentHand;
    }

    public void setCurrentHand(HandEntity currentHand) {
        this.currentHand = currentHand;
    }

    public GameStructure getGameStructure() {
        return gameStructure;
    }

    public void setGameStructure(GameStructure gameStructure) {
        this.gameStructure = gameStructure;
    }

    public Player getFirstBetPlayer() {
        return firstBetPlayer;
    }

    public void setFirstBetPlayer(Player firstBetPlayer) {
        this.firstBetPlayer = firstBetPlayer;
    }

    public boolean addPlayerToGame(Player player) {
        int freePos = findFreePos();
        if (freePos >= 0) {
            addPlayer(player);
            player.setGame(this);
            player.setGamePosition(freePos);
            return true;
        }
        return false;
    }

    int findFreePos() {
        Integer[] playerPos = GameConfig.DEFAULT_PLAYER_POS;

        for (int j = 0; j < playerPos.length; j++) {
            int i = playerPos[j];
            boolean existed = false;
            for (Player p : players) {
                if (p.getGamePosition() == i) {
                    existed = true;
                    break;
                }
            }
            if (!existed) {
                return i;
            }
        }
        return -1;
    }

    @ApiField
    public byte getGameStatusCode(){
        return GameUtil.getGameStatus(this).getCode();
    }

    public void tryNewBettingRound(Player curPlayer) {
        if (curPlayer == null) return;

        if (getCurrentHand() == null) {
            return;
        }

        boolean isEnded = GameHandServiceImpl.getInstance().tryEndHand(getCurrentHand());

        if (isEnded) {
            return;
        }

//        if (curPlayer.isActed() && GameHandServiceImpl.getInstance().isActionResolved(getCurrentHand()) ) {
//            switch (GameUtil.getGameStatus(this)) {
//                case PRE_FLOP:
//                    GameHandServiceImpl.getInstance().endHand(getCurrentHand());
//                    break;
//                default:
//            }
//        }
    }

    public boolean isCheckShowedLastCard() {
        return checkShowedLastCard;
    }

    public void setCheckShowedLastCard(boolean checkShowedLastCard) {
        this.checkShowedLastCard = checkShowedLastCard;
    }

}
