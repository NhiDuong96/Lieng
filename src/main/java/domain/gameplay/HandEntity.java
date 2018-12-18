package domain.gameplay;


import cmd.api.ApiEntity;
import cmd.api.ApiField;
import config.GameConfig;
import domain.lobby.BlindLevel;

import java.util.*;

@ApiEntity
public class HandEntity {
    private long id;
    private CashGameImpl game;

    public Map<Integer, Long> mapAnteDetail;
    public Map<Integer, Long> mapBettingDetail;
    private TreeMap<Integer, Player> playerSortedMap;
    private Set<PlayerHand> bkPlayers;
    private Player currentToAct;
    private Player lastPlayerBetting;
    private long pot;
    private long totalBetAmount;
    private long lastBetAmount;
    private boolean waitingForPlayerAction;
    private boolean showdown;

    public HandEntity() {
        this(1);
    }

    public HandEntity(long id) {
        this.id = id++;
        mapBettingDetail = new HashMap<>();
        mapAnteDetail = new HashMap<>();
        playerSortedMap = new TreeMap<>();
        this.showdown = false;
    }

    public CashGameImpl getGame() {
        return game;
    }

    public void setGame(CashGameImpl game) {
        this.game = game;
    }

    public Collection<Player> getPlayers() {
        return playerSortedMap.values();
    }


    public void addPlayer(int pos, Player player) {
        playerSortedMap.put(pos, player);
        System.out.println(Arrays.toString(playerSortedMap.keySet().toArray()));
    }

    public Player nextPlayer(Player startPlayer){
        return playerSortedMap.higherEntry(startPlayer.getGamePosition()).getValue();
    }

    public Player getCurrentToAct() {
        return currentToAct;
    }

    public void setCurrentToAct(Player currentToAct) {
        setCurrentToAct(currentToAct, 0);
    }

    public void setCurrentToAct(Player currentToAct, int delayTime) {
        this.currentToAct = currentToAct;
        if (currentToAct != null) {
            this.currentToAct.setBeingActing(false);
            this.game.setActionTimeout(() -> {
                //bot play if action timeout
            }, GameConfig.TIMEOUT_PLAYER_ACTION_SEC + delayTime);
        }
    }

    public long getLastBetAmount() {
        return lastBetAmount;
    }

    public void setLastBetAmount(long lastBetAmount) {
        this.lastBetAmount = lastBetAmount;
    }


    public long getTotalBetAmount() {
        return totalBetAmount;
    }

    public void setTotalBetAmount(long totalBetAmount) {
        this.totalBetAmount = totalBetAmount;
    }

    public int getNumOfParticipatingPlayers(){
        return playerSortedMap.size();
    }

}
