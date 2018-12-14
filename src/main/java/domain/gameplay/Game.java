package domain.gameplay;

import bitzero.server.entities.BZRoomRemoveMode;
import bitzero.server.entities.Room;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ScheduledFuture;

/**
 * Created by GSN on 10/29/2015.
 */
public class  Game {
    public long totalHand = 0;
    long id;
    Room room;
    GameStructure gameStructure;

    Set<Player> players;
    HandEntity currentHand;
    ScheduledFuture<?> loopTask;
    volatile long timer;
    boolean started;
    volatile boolean hasBot;

    public Game(Room room){
        if (room == null) {
            this.id = 1;
        } else {
            this.id = room.getId();
            room.setAutoRemoveMode(BZRoomRemoveMode.NEVER_REMOVE);
        }
        this.room = room;

        this.started = false;
        this.players = new HashSet<>();
        this.timer = 0;
        this.hasBot = false;
    }

    public Game(Room room, GameStructure gameStructure) {
        this(room);
        this.gameStructure = gameStructure;
    }

    public void addPlayer(Player player){
        players.add(player);
    }
}
