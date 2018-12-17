package domain.gameplay;

import bitzero.server.entities.Room;
import cmd.api.ApiEntity;
import cmd.api.ApiField;
import config.GameConfig;
import domain.gameplay.service.GameHandServiceImpl;

import java.util.Set;

/**
 * Created by pc1 on 12/17/2018.
 */
@ApiEntity
public class CashGameImpl extends Game{
    GameStructure gameStructure;
    HandEntity currentHand;

    public CashGameImpl(Room room, GameStructure gameStructure) {
        super(room);
        this.gameStructure = gameStructure;
    }

    public void setNewHandEntityDelay(int totalWinner){
        setActionTimeout(() -> {
            //create new hand entity here
            HandEntity hand = GameHandServiceImpl.getInstance().startNewHand(this);
            if(hand == null){
                //todo
            }
        }, GameConfig.NEW_HAND_DELAY_SEC + GameConfig.WINNER_EFFECT_DELAY_SEC * totalWinner);
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

    @ApiField
    public Set<Player> getPlayers(){
        return players;
    }

    public boolean addPlayerToGame(Player player) {
        int freePos = findFreePos();
        if (freePos >= 0) {
            players.add(player);
            player.setGame(this);
            player.setGamePosition(freePos);
            return true;
        }
        return false;
    }
    int findFreePos() {
        Integer[] playerPos = null;//GameConfig.CASH_GAME_PLAYER_POS_MAP.get(getGameStructure().getMaxPlayers());

        if (playerPos == null) {
            playerPos = GameConfig.DEFAULT_PLAYER_POS;
        }

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

}
