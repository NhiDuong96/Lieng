//package domain.gameplay;
//
//import bitzero.server.entities.User;
//import domain.gameplay.holder.Hand;
//
///**
// * Created by GSN on 10/30/2015.
// */
//public class Player implements Comparable<Player> {
//    private boolean isBot;
//
//    private Integer id;
//    private User user;
//    private String name;
//    private String avatar;
//
//    private int level;
//    private int vip;
//
//    private int showingBracelet;
//    private Game game;
//    private Hand hand;
//    private int timesNonInterActive;
//    private boolean doSaveBet;
//
//    private long chips;
//    private long buyinChips;
//
//    private int gamePosition;
//    private int finishPosition;
//    private boolean sittingOut;
//    private boolean acted;
//
//    private boolean leavingGame;
//
//    private boolean autoBuyIn;
//
//
//    private long betValue;
//    private long anteValue;
//
//
//    private boolean buyInSuccess;
//    private int totalHandSittingOut;
//    private int totalHandClientAuto;
//    private PlayerAutoState currentHandAutoState;
//
//    private long finalWonChips;
//    private long timeJoinGame;
//    private long lastTimeInGame;
//
//    private boolean registerLeaveGame;
//
//
//    private volatile boolean beingActing;
//
//    private SidePotPlayerData sidePotPlayerData;
//    private long timeStartSittingOut;
//
//    public Player(User user, long buyinChips) {
//        this.isBot = false;
//        setId(user.getId());
//        this.user = user;
//        this.buyinChips = buyinChips;
//        this.totalHandSittingOut = 0;
//        this.totalHandClientAuto = 0;
//        this.chips = buyinChips;
//        this.autoBuyIn = false;
//        this.name = user.getName();
//        this.betValue = 0;
//        this.anteValue = 0;
//        this.acted = false;
//        this.leavingGame = false;
//        this.finalWonChips = 0;
//        this.showingBracelet = -1;
//        this.level = 1;
//        this.vip = 0;
//        this.beingActing = false;
//        this.lastTimeInGame = 0;
//        this.registerLeaveGame= false;
//        this.sidePotPlayerData = new SidePotPlayerData(user);
//        this.timeStartSittingOut = 0;
//        initBettingMap();
//    }
//
////    public Player(long buyinChips) {
////        this(FakeFactory.createFakeUser(), buyinChips);
////        this.registerLeaveGame= false;
////        setIsBot(true);
////    }
//
//    public int getShowingBracelet() {
//        return showingBracelet;
//    }
//
//    public void setShowingBracelet(int showingBracelet) {
//        this.showingBracelet = showingBracelet;
//    }
//
//    public String getAvatar() {
//        return avatar;
//    }
//
//    public void setAvatar(String avatar) {
//        this.avatar = avatar;
//    }
//
//    public int getLevel() {
//        return level;
//    }
//
//    public void setLevel(int level) {
//        this.level = level;
//    }
//
//    public int getVip() {
//        return vip;
//    }
//
//    public void setVip(int vip) {
//        this.vip = vip;
//    }
//
//    public boolean isBuyInSuccess() {
//        return buyInSuccess;
//    }
//
//    public void setBuyInSuccess(boolean buyInSuccess) {
//        this.buyInSuccess = buyInSuccess;
//    }
//
//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
//
//    public boolean isBot() {
//        return isBot;
//    }
//
//    public void setIsBot(boolean isBot) {
//        this.isBot = isBot;
//    }
//
//    public Game getGame() {
//        return game;
//    }
//
//    public void setGame(Game game) {
//        this.game = game;
//    }
//
//    public Hand getHand() {
//        return hand;
//    }
//
//    public void setHand(Hand hand) {
//        this.hand = hand;
//    }
//
//    public PlayerStatus getStatus() {
//        return PlayerUtil.getPlayerStatus(this);
//    }
//
//    public int getGamePosition() {
//        return gamePosition;
//    }
//
//    public void setGamePosition(int gamePosition) {
//        this.gamePosition = gamePosition;
//    }
//
//    public int getClientGamePosition() {
//        return gamePosition - 1;
//    }
//
//    public long getChips() {
//        return chips;
//    }
//
//    public void setChips(long chips) {
//        this.chips = chips;
//    }
//
//    public long getBuyinChips() {
//        return buyinChips;
//    }
//
//    public void setBuyinChips(long buyinChips) {
//        this.buyinChips = buyinChips;
//    }
//
//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
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
//    public int getFinishPosition() {
//        return finishPosition;
//    }
//
//    public void setFinishPosition(int finishPosition) {
//        this.finishPosition = finishPosition;
//    }
//
//    public boolean isSittingOut() {
//        return sittingOut;
//    }
//
//    public void setSittingOut(boolean sittingOut) {
//        this.sittingOut = sittingOut;
//        if (sittingOut) {
//            setTimeStartSittingOut(getGame().getTimer());
//        } else {
//            setTimeStartSittingOut(0);
//        }
//    }
//
//    public int getTotalHandSittingOut() {
//        return totalHandSittingOut;
//    }
//
//    public void setTotalHandSittingOut(int totalHandSittingOut) {
//        this.totalHandSittingOut = totalHandSittingOut;
//    }
//
//    public boolean isAutoBuyIn() {
//        return autoBuyIn;
//    }
//
//    public void setAutoBuyIn(boolean autoBuyIn) {
//        this.autoBuyIn = autoBuyIn;
//    }
//
//    public boolean isLeavingGame() {
//        return leavingGame;
//    }
//
//    public void setLeavingGame(boolean leavingGame) {
//        this.leavingGame = leavingGame;
//    }
//
//    public boolean isActed() {
//        return acted;
//    }
//
//    public void setActed(boolean acted) {
//        this.acted = acted;
//    }
//
//    public long getFinalWonChips() {
//        return finalWonChips;
//    }
//
//    public void setFinalWonChips(long finalWonChips) {
//        this.finalWonChips = finalWonChips;
//    }
//
//    public int getTotalHandClientAuto() {
//        return totalHandClientAuto;
//    }
//
//    public void setTotalHandClientAuto(int totalHandClientAuto) {
//        this.totalHandClientAuto = totalHandClientAuto;
//    }
//
//    public PlayerAutoState getCurrentHandAutoState() {
//        return currentHandAutoState;
//    }
//
//    public void setCurrentHandAutoState(PlayerAutoState currentHandAutoState) {
//        this.currentHandAutoState = currentHandAutoState;
//    }
//
//    public long getTimeJoinGame() {
//        return timeJoinGame;
//    }
//
//    public void setTimeJoinGame(long timeJoinGame) {
//        this.timeJoinGame = timeJoinGame;
//    }
//
//    public long getAnteValue() {
//        return anteValue;
//    }
//
//    public void setAnteValue(long anteValue) {
//        getGame().getCurrentHand().mapAnteDetail.put(getClientGamePosition(), anteValue);
//        this.anteValue = anteValue;
//    }
//
//    public boolean isBeingActing() {
//        return beingActing;
//    }
//
//    public void setBeingActing(boolean beingActing) {
//        this.beingActing = beingActing;
//    }
//
//    public long getLastTimeInGame() {
//        return lastTimeInGame;
//    }
//
//    public void setLastTimeInGame(long lastTimeInGame) {
//        this.lastTimeInGame = lastTimeInGame;
//    }
//
//    public void addBetAmount(long value) {
//        if (value > 0) {
//            betValue += value;
//            getGame().getCurrentHand().mapBettingDetail.put(getClientGamePosition(), betValue);
//        } else {
//            LogicLogger.getInstance().trace("Error! add betting mount", this, value);
//        }
//    }
//
//    public void initBettingMap() {
//        setBetValue(0);
//    }
//
//    public long getBetValue() {
//        return betValue;
//    }
//
//    public void setBetValue(long betValue) {
//        this.betValue = betValue;
//    }
//
//    public boolean isRegisterLeaveGame() {
//        return registerLeaveGame;
//    }
//
//    public void setRegisterLeaveGame(boolean registerLeaveGame) {
//        this.registerLeaveGame = registerLeaveGame;
//    }
//
//    public SidePotPlayerData getSidePotPlayerData() {
//        return sidePotPlayerData;
//    }
//
//    public void setSidePotPlayerData(SidePotPlayerData sidePotPlayerData) {
//        this.sidePotPlayerData = sidePotPlayerData;
//    }
//
//    @Override
//    public int compareTo(Player o) {
//        return getGamePosition() - o.getGamePosition();
//    }
//
//    @Override
//    public String toString() {
//        return "Player{" +
//                "id='" + getId() + '\'' +
//                ", chips=" + chips +
//                ", hd=" + hand +
//                ", pos=" + getClientGamePosition() +
//                '}';
//    }
//
//    public long getTimeStartSittingOut() {
//        return timeStartSittingOut;
//    }
//
//    public void setTimeStartSittingOut(long timeStartSittingOut) {
//        this.timeStartSittingOut = timeStartSittingOut;
//    }
//
//    public BCTCGame getBctcGame() {
//        return bctcGame;
//    }
//
//    public void setBctcGame(BCTCGame bctcGame) {
//        this.bctcGame = bctcGame;
//    }
//
//    public int getTimesNonInterActive() {
//        return timesNonInterActive;
//    }
//
//    public void setTimesNonInterActive(int timesNonInterActive) {
//        this.timesNonInterActive = timesNonInterActive;
//    }
//
//    public boolean doSaveBet() {
//        return doSaveBet;
//    }
//
//    public void setDoSaveBet(boolean doSaveBet) {
//        this.doSaveBet = doSaveBet;
//    }
//}
