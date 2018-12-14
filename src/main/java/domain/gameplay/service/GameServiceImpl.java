package domain.gameplay.service;

import bitzero.framework.util.FrameworkUtils;
import domain.gameplay.Game;
import domain.gameplay.Player;
import handler.event.LeaveGameReason;

/**
 * Created by pc1 on 12/13/2018.
 */
public class GameServiceImpl implements GameService {
    static GameServiceImpl instance = new GameServiceImpl();

    public GameServiceImpl() {
    }

    public static GameServiceImpl getInstance() {
        return instance;
    }


    @Override
    public Game startGame(Game game) {
        return null;
    }

    @Override
    public Player joinGame(Game game, Player player) {
        //may be have num of player join game simultaneously
        synchronized (game){
            //check if user already in game

            PlayerActionServiceImpl.getInstance().attachPlayerToUser(player.getUser(), player);
            game.addPlayer(player);

            long now = FrameworkUtils.currentTimeInSecond();
            player.setTimeJoinGame(now);

        }
        return player;
    }

    @Override
    public boolean quitGame(Player player, LeaveGameReason quitReason, String description) {
        return false;
    }

    @Override
    public boolean temporaryLeaveGame(Player player) {
        return false;
    }
}
