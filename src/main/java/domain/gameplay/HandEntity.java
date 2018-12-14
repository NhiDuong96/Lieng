package domain.gameplay;


import domain.gameplay.card.Deck;
import domain.lobby.BlindLevel;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class HandEntity {
    private BlindLevel blindLevel;
    public Map<Integer, Long> mapAnteDetail;
    public Map<Integer, Long> mapBettingDetail;
    private long id;
    private Game game;
    private Set<PlayerHand> players;
    private Set<PlayerHand> bkPlayers;
    private Player currentToAct;
    private Player lastPlayerBetting;
    private Deck deck;
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
}
