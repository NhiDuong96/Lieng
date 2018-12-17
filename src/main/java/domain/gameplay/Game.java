package domain.gameplay;

import bitzero.framework.ExtensionUtility;
import bitzero.server.BitZeroServer;
import bitzero.server.core.BZEvent;
import bitzero.server.core.BZEventParam;
import bitzero.server.entities.BZRoomRemoveMode;
import bitzero.server.entities.Room;
import bitzero.server.entities.User;
import cmd.api.ApiEntity;
import config.GameConfig;
import domain.gameplay.service.GameServiceImpl;
import domain.gameplay.util.GameUtil;
import handler.event.LeaveGameReason;

import java.util.*;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * Created by GSN on 10/29/2015.
 */

public class Game {
    public long totalBroadcastMsg = 0;


    long id;
    Room room;
    Set<Player> players;

    ScheduledFuture<?> loopTask;
    volatile int timeIdle;
    volatile int mActionTimeout;
    volatile long timer;

    boolean isStarted;
    boolean isDestroy;
    int timeOutRepeat;

    public interface ActionTimeOutListener{
        void onActionTimeOut();
    }

    ActionTimeOutListener timeOutListener;

    public Game(Room room){
        if (room == null) {
            this.id = 1;
        } else {
            this.id = room.getId();
            room.setAutoRemoveMode(BZRoomRemoveMode.NEVER_REMOVE);
        }
        this.room = room;
        this.players = new HashSet<>();

        isStarted = false;
        isDestroy = false;
        timeIdle = GameConfig.TIMEOUT_GAME_IDLE;
        startLoop();
    }

    public boolean start(){
        if(players.size() == 0){
            destroy("No player in game");
            return false;
        }
        onStart();
        isStarted = true;
        return true;
    }

    public void addPlayer(Player player){
        players.add(player);
    }

    public void removePlayer(Player player){
        players.remove(player);
        if(players.size() == 0){
            destroy("No player in game");
        }
    }

    public Set<Player> getPlayers() {
        return players;
    }

    void startLoop() {
        timer = 0;
        timeOutRepeat = 0;
        mActionTimeout = 0;
        Runnable loop = () -> {
            try {
                timer++;
                if (!isStarted()) {
                    //game was idled too long -> stop it
                    if(timer >= timeIdle) {
                        kickAllUser(LeaveGameReason.DESTROY_GAME);
                        System.out.println("idle too long -> kick all user");
                    }
                    return;
                }
                onUpdate();
                if(timeOutRepeat != 0 && timer > mActionTimeout){
                    timeOutListener.onActionTimeOut();
                    timeOutRepeat--;
                }
            } catch (Exception e) {
//                    LogicLogger.getInstance().getLogger().error(getName(), e);
                destroy("exception from Loop()");
            }
        };
        loopTask = BitZeroServer.getInstance().getTaskScheduler().scheduleAtFixedRate(loop, 0, 1, TimeUnit.SECONDS);
    }

    public void destroy(String reason){
        if(isDestroy()) {
//        LogicLogger.getInstance().warn(this, "was destroyed", reason);
            return;
        }
        onDestroy();
        Set<Player> tempSet = new HashSet<>();
        tempSet.addAll(players);
        for (Player p : tempSet) {
            User user = p.getUser();
            if (user != null) {
                user.removeProperty(Player.class);
                user.removeJoinedRoom(getRoom());
                ExtensionUtility.instance().outChannel(user);
            }
        }
        if (getRoom() != null) {
            // remove room
            getRoom().removeProperty(Game.class);
            ExtensionUtility.instance().removeRoom(getRoom());
        }
        loopTask.cancel(false);
        isStarted = false;
        isDestroy = true;
    }

    public void kickAllUser(LeaveGameReason leaveGameReason) {
//        LogicLogger.getInstance().warn(this, "kick all user", leaveGameReason);
        if (getRoom() == null) {
            return;
        }
//        List<User> listSpec = getRoom().getSpectatorsList();
        Set<Player> tempSet = new HashSet<>();
        tempSet.addAll(players);
        for (Player p : tempSet) {
            GameUtil.forcedLeaveGame(p, leaveGameReason);
        }
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public boolean isStarted() {
        return isStarted;
    }

    public boolean isDestroy() {
        return isDestroy;
    }

    public void setActionTimeout(ActionTimeOutListener listener, int pActionTimeout) {
        this.setActionTimeout(listener, pActionTimeout, 1);
    }

    public void setActionTimeout(ActionTimeOutListener listener, int pActionTimeout, int repeat){
        this.timeOutListener = listener;
        this.mActionTimeout = pActionTimeout;
        this.timeOutRepeat = repeat;
    }

    public void clearActionTimeout(){
        this.timeOutRepeat = 0;
        this.mActionTimeout = 0;
    }

    public int getGameTimeIdle() {
        return timeIdle;
    }

    public void setGameTimeIdle(int timeIdle) {
        this.timeIdle = timeIdle;
    }

    public int getActionTimeout() {
        return mActionTimeout;
    }

    protected void onStart() {
        //override for game start
    }

    protected void onDestroy() {
        //override for game destroy
    }

    protected void onUpdate() {
        //override for game loop
    }


}
