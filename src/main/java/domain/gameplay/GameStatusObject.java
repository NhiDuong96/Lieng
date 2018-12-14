package domain.gameplay;

import cmd.api.ApiEntity;
import cmd.api.ApiField;

import java.util.*;

/**
 * Created by GSN on 11/9/2015.
 */
@ApiEntity
public class GameStatusObject {
    long id;
    String structureId;
    long blindLevel;
    private List<PlayerStatusObject> playerList;
    byte firstBetPos;
    byte curPlayerPos;
    int curPlayerTimeout;
    GameStatus status;
    Map<Integer, Long> mapAnteDetail;
    Map<Integer, Long> mapBettingDetail;
    byte endBetRoundPlayerPos;
    long lastBetAmount;

    boolean isLeavingRegister = false;

    String extraData = "";

    boolean proMode;
    boolean checkShowLastCard;
    int sidepotRound;
    long sidePotGold;

    public GameStatusObject() {
        mapAnteDetail = new HashMap<>();
        playerList=  new ArrayList<>();
    }

    @ApiField
    public Set<Integer> getPositions(){
        return mapAnteDetail.keySet();
    }

    public Map<Integer, Long> getMapAnteDetail() {
        return mapAnteDetail;
    }

    public void setMapAnteDetail(Map<Integer, Long> mapAnteDetail) {
        this.mapAnteDetail = mapAnteDetail;
    }

    public Map<Integer, Long> getMapBettingDetail() {
        return mapBettingDetail;
    }

    public void setMapBettingDetail(Map<Integer, Long> mapBettingDetail) {
        this.mapBettingDetail = mapBettingDetail;
    }

    public long getBlindLevel() {
        return blindLevel;
    }

    public void setBlindLevel(long blindLevel) {
        this.blindLevel = blindLevel;
    }

    public byte getCurPlayerPos() {
        return curPlayerPos;
    }

    public void setCurPlayerPos(byte curPlayerPos) {
        this.curPlayerPos = curPlayerPos;
    }

    public String getStructureId() {
        return structureId;
    }

    public void setStructureId(String structureId) {
        this.structureId = structureId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @ApiField
    public List<PlayerStatusObject> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(List<PlayerStatusObject> playerList) {
        this.playerList = playerList;
    }

    public GameStatus getStatus() {
        return status;
    }

    public void setStatus(GameStatus status) {
        this.status = status;
    }

    public int getCurPlayerTimeout() {
        return curPlayerTimeout;
    }

    public void setCurPlayerTimeout(int curPlayerTimeout) {
        this.curPlayerTimeout = curPlayerTimeout;
    }

    public byte getFirstBetPos() {
        return firstBetPos;
    }

    public void setFirstBetPos(byte firstBetPos) {
        this.firstBetPos = firstBetPos;
    }

    public byte getEndBetRoundPlayerPos() {
        return endBetRoundPlayerPos;
    }

    public void setEndBetRoundPlayerPos(byte endBetRoundPlayerPos) {
        this.endBetRoundPlayerPos = endBetRoundPlayerPos;
    }

    public long getLastBetAmount() {
        return lastBetAmount;
    }

    public void setLastBetAmount(long lastBetAmount) {
        this.lastBetAmount = lastBetAmount;
    }

    public String getExtraData() {
        return extraData;
    }

    public void setExtraData(String extraData) {
        this.extraData = extraData;
    }

    public boolean isProMode() {
        return proMode;
    }

    public void setProMode(boolean proMode) {
        this.proMode = proMode;
    }


    public boolean isCheckShowLastCard() {
        return checkShowLastCard;
    }

    public void setCheckShowLastCard(boolean checkShowLastCard) {
        this.checkShowLastCard = checkShowLastCard;
    }

    public boolean isLeavingRegister() {
        return isLeavingRegister;
    }

    public void setLeavingRegister(boolean leavingRegister) {
        isLeavingRegister = leavingRegister;
    }

    public long getSidePotGold() {
        return sidePotGold;
    }

    public void setSidePotGold(long sidePotGold) {
        this.sidePotGold = sidePotGold;
    }

    public int getSidepotRound() {
        return sidepotRound;
    }

    public void setSidepotRound(int sidepotRound) {
        this.sidepotRound = sidepotRound;
    }

    @Override
    public String toString() {
        return "GameStatusObject{" +
                "id=" + id +
                ", structureId='" + structureId + '\'' +
                ", blindLevel=" + blindLevel +
                ", playerList=" + playerList +
                ", firstBetPos=" + firstBetPos +
                ", curPlayerPos=" + curPlayerPos +
                ", curPlayerTimeout=" + curPlayerTimeout +
                ", status=" + status +
                ", mapAnteDetail=" + mapAnteDetail +
                ", mapBettingDetail=" + mapBettingDetail +
                '}';
    }
}
