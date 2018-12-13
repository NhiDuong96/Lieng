//package domain.gameplay;
//
//import bitzero.framework.ExtensionUtility;
//import bitzero.server.BitZeroServer;
//import bitzero.server.entities.BZRoomRemoveMode;
//import bitzero.server.entities.Room;
//import bitzero.server.entities.User;
//import common.MyRandom;
//import config.GameConfig;
//import domain.cheat.CheatDeck;
//import domain.gameplay.service.GameHandServiceImpl;
//import domain.gameplay.sidepot.SidePotGameData;
//import domain.gameplay.util.GameUtil;
//import domain.gameplay.util.PlayerUtil;
//import domain.lobby.format.TournamentFormat;
//import domain.tournament.TournamentServiceImpl;
//import handler.event.LeaveGameReason;
//import log.ZPThaiLog;
//import log.log4j.LogicLogger;
//
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//import java.util.concurrent.ScheduledFuture;
//import java.util.concurrent.TimeUnit;
//
//import static domain.gameplay.GameType.TOURNAMENT;
//
///**
// * Created by GSN on 10/29/2015.
// */
//public class  Game {
//    public static boolean STEP_BY_STEP_TEST = false;
//    /**
//     * dummies variables
//     */
//    public long totalBroadcastMsg = 0;
//    public long totalHandWithoutPlayer = 0;
//    public long totalHand = 0;
//    public CheatDeck cheatDeck = null;
//    long id;
//    String name;
//    GameStructure gameStructure;
//    Room room;
//    Set<Player> players;
//    Player firstBetPlayer;
//    HandEntity currentHand;
//    int playersRemaining;
//    ScheduledFuture<?> loopTask;
//    volatile long timer;
//    volatile int timeIdle;
//    volatile int pActionTimeout;
//    volatile int pActionTimeUsed;
//    volatile int newHandDelay;
//    boolean started;
//
//    boolean checkShowedLastCard;
//    volatile boolean hasBot;
//
//    long lastTimeChangeBlindLevel;
//
//    private SidePotGameData sidePotGameData;
//
//    public Game(Room room, String name, GameStructure gameStructure) {
//        if (room == null) {
//            this.id = 1;
//        } else {
//            this.id = room.getId();
//            room.setAutoRemoveMode(BZRoomRemoveMode.NEVER_REMOVE);
//        }
//        this.room = room;
//        this.name = name;
//        this.gameStructure = gameStructure;
//        this.started = false;
//        this.players = new HashSet<>();
//        this.playersRemaining = 0;
//        this.lastTimeChangeBlindLevel = 0;
//        this.timer = 0;
//        this.hasBot = false;
//        this.sidePotGameData = new SidePotGameData(getId(), getGameStructure().clone());
//
//        if (!STEP_BY_STEP_TEST) {
//            startLoop();
//        }
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public Room getRoom() {
//        return room;
//    }
//
//    public void setRoom(Room room) {
//        this.room = room;
//    }
//
//    public long getId() {
//        return id;
//    }
//
//    public void setId(long id) {
//        this.id = id;
//    }
//
//    public GameStatus getStatus() {
//        return GameUtil.getGameStatus(this);
//    }
//
//
//    public Set<Player> getPlayers() {
//        return players;
//    }
//
//    public void setPlayers(Set<Player> players) {
//        this.players = players;
//    }
//
//    public boolean isStarted() {
//        return started;
//    }
//
//    public void setStarted(boolean started) {
//        this.started = started;
//    }
//
//    public GameStructure getGameStructure() {
//        return gameStructure;
//    }
//
//    public void setGameStructure(GameStructure gameStructure) {
//        this.gameStructure = gameStructure;
//    }
//
//    public Player getFirstBetPlayer() {
//        return firstBetPlayer;
//    }
//
//    public void setFirstBetPlayer(Player firstBetPlayer) {
//        this.firstBetPlayer = firstBetPlayer;
//    }
//
//    public HandEntity getCurrentHand() {
//        return currentHand;
//    }
//
//    public void setCurrentHand(HandEntity currentHand) {
//        this.currentHand = currentHand;
//    }
//
//    public int getPlayersRemaining() {
//        return playersRemaining;
//    }
//
//    public void setPlayersRemaining(int playersRemaining) {
//        this.playersRemaining = playersRemaining;
//    }
//
//    public long getLastTimeChangeBlindLevel() {
//        return lastTimeChangeBlindLevel;
//    }
//
//    public void setLastTimeChangeBlindLevel(long lastTimeChangeBlindLevel) {
//        this.lastTimeChangeBlindLevel = lastTimeChangeBlindLevel;
//    }
//
//    public boolean isHasBot() {
//        return hasBot;
//    }
//
//    public void setHasBot(boolean hasBot) {
//        this.hasBot = hasBot;
//    }
//
//    public long getTimer() {
//        return timer;
//    }
//
//    public void setTimer(long timer) {
//        this.timer = timer;
//    }
//
//    public SidePotGameData getSidePotGameData() {
//        return sidePotGameData;
//    }
//
//    public void setSidePotGameData(SidePotGameData sidePotGameData) {
//        this.sidePotGameData = sidePotGameData;
//    }
//
//    public void setNewHandDelay(int totalWinner) {
//        if (totalHand < 1) {
//            this.newHandDelay = 1;
//        } else {
//            this.newHandDelay = GameConfig.NEW_HAND_DELAY_SEC + GameConfig.WINNER_EFFECT_DELAY_SEC * totalWinner;
//        }
//    }
//
//    public int getpActionTimeout() {
//        return pActionTimeout;
//    }
//
//    public void setpActionTimeout(Player player, int delayTime) {
//        this.pActionTimeUsed = 0;
//        if (player.isBot()) {
//            this.pActionTimeout = MyRandom.getRandom(1, GameConfig.TIMEOUT_BOT_ACTION_SEC, 1);
//        } else {
//            if(player.getCurrentHandAutoState() == PlayerAutoState.AUTO || player.isLeavingGame()){
//                this.pActionTimeout = GameConfig.MAP_CASH_GAME_CHANNEL.get(getGameStructure().getChannelID()).getNonInterActiveTimeOut();
//            }else {
//                this.pActionTimeout = GameConfig.TIMEOUT_PLAYER_ACTION_SEC;
//            }
//        }
//        this.pActionTimeout += delayTime;
//    }
//
//    public int getpActionTimeUsed() {
//        return pActionTimeUsed;
//    }
//
//    public void destroy(String reason) {
//        getSidePotGameData().destroy(reason);
//
//        Set<Player> tempSet = new HashSet<>();
//        tempSet.addAll(players);
//        for (Player p : tempSet) {
//            if (p.getUser() != null) {
//                p.getUser().removeProperty(Player.class);
//                p.getUser().removeJoinedRoom(getRoom());
//            }
//        }
//        LogicLogger.getInstance().warn(this, "was destroyed", reason);
//        ZPThaiLog.putDestroyTable(getId());
//        setStarted(false);
//        if (getRoom() != null) {
//            // remove room
//            getRoom().removeProperty(Game.class);
//            ExtensionUtility.instance().removeRoom(getRoom());
//        }
//        loopTask.cancel(false);
//    }
//
//    public void kickAllUser(LeaveGameReason leaveGameReason) {
//        LogicLogger.getInstance().warn(this, "kick all user", leaveGameReason);
//        if (getRoom() == null) {
//            return;
//        }
//        List<User> listSpec = getRoom().getSpectatorsList();
//        Set<Player> tempSet = new HashSet<>();
//        tempSet.addAll(players);
//        for (Player p : tempSet) {
//            GameUtil.forcedLeaveGame(p, leaveGameReason);
//        }
//    }
//
//    public void cashGameLoop() {
//        if (!isStarted()) {
//            timeIdle++;
//            if (timeIdle >= GameConfig.TIMEOUT_GAME_IDLE) {
//                kickAllUser(LeaveGameReason.DESTROY_GAME);
//            }
//            return;
//        } else {
//            timeIdle = 0;
//        }
//        GameStatus gameStatus = GameUtil.getGameStatus(this);
//        if (gameStatus == GameStatus.SHOW_DOWN || gameStatus == GameStatus.SEATING) {
//            if (newHandDelay == 0) {
//                HandEntity hand = GameHandServiceImpl.getInstance().startNewHand(this);
//                if (hand == null) {
//                    setCurrentHand(null);
//                    setNewHandDelay(0);
//                    GameUtil.verifyPlayerLimitTimeSitOut(this, GameConfig.LIMIT_TIME_SITTING_OUT_CASH_GAME);
//                }
//            } else if (newHandDelay < 0) {
//                LogicLogger.getInstance().error(this, "Can't start new hand");
//                setNewHandDelay(0);
//            }
//            newHandDelay--;
//        }
//
//        if (getCurrentHand() == null) {
//            return;
//        }
//
//        if (getCurrentHand().getCurrentToAct() != null) {
//            if (pActionTimeout == 0) {
//                if (getCurrentHand().getCurrentToAct().isBot()) {
//                    AIController.botPlay(getCurrentHand().getCurrentToAct());
//                } else {
//                    AIController.actionInTimeout(getCurrentHand().getCurrentToAct());
//                }
//            } else if (pActionTimeout < 0) {
//                LogicLogger.getInstance().error(this, getCurrentHand().getCurrentToAct(), "Can't action", getCurrentHand().getPot());
//                getCurrentHand().setCurrentToAct(null);
//            }
//            pActionTimeout--;
//            pActionTimeUsed++;
//        }
//    }
//
//    public void tournamentLoop() {
//        if (!isStarted()) {
//            timeIdle++;
//            if (timeIdle >= GameConfig.TIMEOUT_TOUR_IDLE) {
//                kickAllUser(LeaveGameReason.DESTROY_GAME);
//            }
//            return;
//        } else {
//            timeIdle = 0;
//        }
//        if (GameUtil.getGameStatus(this) == GameStatus.SHOW_DOWN || GameUtil.getGameStatus(this) == GameStatus.SEATING) {
//            if (newHandDelay == 0) {
//                HandEntity hand = GameHandServiceImpl.getInstance().startNewHand(this);
//                if (hand == null) {
//                    //kickAllUser(LeaveGameReason.FINISH_TOURNAMENT);
//                    if (players.size() == 1) {
//                        TournamentFormat tournamentFormat = GameConfig.MAP_TOURNAMENT_FORMAT.get(this.getGameStructure().getId());
//                        Player winner = players.iterator().next();
//                        winner.setFinishPosition(1);
//                        TournamentServiceImpl.getInstance().updateWinner(tournamentFormat, winner);
//                        this.destroy("FINISH_TOURNAMENT");
//                    }
//
//                }
//            } else if (newHandDelay < 0) {
//                LogicLogger.getInstance().error(this, "Can't start new hand", isStarted());
//                setNewHandDelay(0);
//            }
//            newHandDelay--;
//        }
//
//        if (getCurrentHand() == null) {
//            return;
//        }
//
//        if (getCurrentHand().getCurrentToAct() != null) {
//            if (pActionTimeout == 0) {
//                if (getCurrentHand().getCurrentToAct().isBot()) {
//                    AIController.botPlay(getCurrentHand().getCurrentToAct());
//                } else {
//                    AIController.actionInTimeout(getCurrentHand().getCurrentToAct());
//                }
//            } else if (pActionTimeout < 0) {
//                LogicLogger.getInstance().error(this, getCurrentHand().getCurrentToAct(), "Can't action", getCurrentHand().getPot());
//                getCurrentHand().setCurrentToAct(null);
//            }
//            pActionTimeout--;
//            pActionTimeUsed++;
//        }
//    }
//
//    public void loop() throws Exception {
//        timer++;
//        switch (getGameStructure().getGameType()) {
//            case CASH:
//                cashGameLoop();
//                break;
//            case TOURNAMENT:
//                tournamentLoop();
//                break;
//            default:
//                destroy("Wrong gameType " + getGameStructure().getGameType());
//        }
//    }
//
//    void startLoop() {
//        timer = 0;
//        pActionTimeout = 0;
//        Runnable loop = new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    loop();
//                } catch (Exception e) {
//                    LogicLogger.getInstance().getLogger().error(getName(), e);
//                    destroy("exception from Loop()");
//                }
//            }
//        };
//        loopTask = BitZeroServer.getInstance().getTaskScheduler().scheduleAtFixedRate(loop, 0, 1, TimeUnit.SECONDS);
//    }
//
//    int findFreePos() {
//        Integer[] playerPos = null;
//        switch (getGameStructure().getGameType()) {
//            case TOURNAMENT:
//                playerPos = GameConfig.TOURNAMENT_PLAYER_POS_MAP.get(getGameStructure().getMaxPlayers());
//                break;
//            case CASH:
//                playerPos = GameConfig.CASH_GAME_PLAYER_POS_MAP.get(getGameStructure().getMaxPlayers());
//                break;
//        }
//
//        if (playerPos == null) {
//            playerPos = GameConfig.DEFAULT_PLAYER_POS;
//        }
//
//        for (int j = 0; j < playerPos.length; j++) {
//            int i = playerPos[j];
//            boolean existed = false;
//            for (Player p : players) {
//                if (p.getGamePosition() == i) {
//                    existed = true;
//                    break;
//                }
//            }
//            if (!existed) {
//                return i;
//            }
//        }
//        return -1;
//    }
//
//    public boolean addPlayer(Player player) {
//        int freePos = findFreePos();
//        if (freePos > 0) {
//            players.add(player);
//            playersRemaining++;
//            player.setGame(this);
//            player.setGamePosition(freePos);
//            return true;
//        }
//        return false;
//    }
//
//    public boolean removePlayer(Player player) {
//        playersRemaining--;
//        return players.remove(player);
//    }
//
//    public void tryNewBettingRound(Player curPlayer) {
//        if (STEP_BY_STEP_TEST) return;
//        if (curPlayer == null) return;
//
//        if (getCurrentHand() == null) {
//            return;
//        }
//
//        boolean isEnded = GameHandServiceImpl.getInstance().tryEndHand(getCurrentHand());
//
//        if (isEnded) {
//            return;
//        }
//        if (curPlayer.isActed() && GameHandServiceImpl.getInstance().isActionResolved(getCurrentHand()) ) {
//            if(gameStructure.getPlayMode().isProMode()){
//                switch (GameUtil.getGameStatus(this)) {
//                    case PREFLOP:
//                        if(checkShowedLastCard ){
//                            GameHandServiceImpl.getInstance().endHand(getCurrentHand());
//                        }else{
//                            sendLastCard();
//                            if(GameHandServiceImpl.getInstance().isAllIn(getCurrentHand())){
//                                GameHandServiceImpl.getInstance().endHand(getCurrentHand());
//                            }
//                        }
//                        break;
//                }
//            }else{
//                switch (GameUtil.getGameStatus(this)) {
//                    case PREFLOP:
//                        GameHandServiceImpl.getInstance().endHand(getCurrentHand());
//                        break;
//                    default:
//                }
//            }
//
//        }
//    }
//
//    public boolean isCheckShowedLastCard() {
//        return checkShowedLastCard;
//    }
//
//    public void setCheckShowedLastCard(boolean checkShowedLastCard) {
//        this.checkShowedLastCard = checkShowedLastCard;
//    }
//
//    public void sendLastCard(){
//        Player next = PlayerUtil.getFirstPlayerAct(getCurrentHand(), firstBetPlayer);
//        getCurrentHand().setCurrentToAct(next);
//        GameUtil.sendLastCard(this);
//        getCurrentHand().setTotalBetAmount(0);
//        for(PlayerHand playerHand: getCurrentHand().getPlayers()){
//            playerHand.setRoundBetAmount(0);
//        }
//        for(Player player: players){
//            player.setActed(false);
//        }
//        checkShowedLastCard = true;
//    }
//
//    @Override
//    public String toString() {
//        return "Game{" +
//                "type=" + getGameStructure().getGameType() +
//                ", id=" + id +
//                ", blindLevel=" + getGameStructure().getCurrentBlindLevel().getAnte() +
//                '}';
//    }
//}
