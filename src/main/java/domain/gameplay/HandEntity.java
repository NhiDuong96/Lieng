package domain.gameplay;


import cmd.api.ApiEntity;
import domain.lobby.BlindLevel;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@ApiEntity
public class HandEntity {
    private long id;
    private Game game;

    private BlindLevel blindLevel;
    public Map<Integer, Long> mapAnteDetail;
    public Map<Integer, Long> mapBettingDetail;
    private Set<PlayerHand> players;
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
        this.showdown = false;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Set<PlayerHand> getPlayers() {
        return players;
    }

    public void setPlayers(Set<PlayerHand> players) {
        this.players = players;
    }

}
