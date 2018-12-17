package domain.gameplay.service;

import bitzero.framework.util.FrameworkUtils;
import config.GameConfig;
import domain.gameplay.CashGameImpl;
import domain.gameplay.Game;
import domain.gameplay.GameStructure;
import domain.gameplay.Player;
import domain.gameplay.util.GameUtil;
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
    public CashGameImpl startGame(CashGameImpl game) {
        GameStructure gs = game.getGameStructure();
        if (game.getPlayers().size() < GameConfig.MIN_PLAYER_TO_START_GAME) {
            throw new IllegalStateException("Not Enough Players");
        }
//            if (game.getPlayers().size() > gs.getMaxPlayers()) {
//                throw new IllegalStateException("Too Many Players");
//            }
        if (game.isStarted()) {
            throw new IllegalStateException("Game already started");
        }

        game.start();
        game.setNewHandEntityDelay(0);
        return game;
    }

    @Override
    public Player joinGame(CashGameImpl game, Player player) {
        //may be have num of player join game simultaneously
        synchronized (game){
            //check if user already in game

            PlayerActionServiceImpl.getInstance().attachPlayerToUser(player.getUser(), player);
            game.addPlayerToGame(player);

            long now = FrameworkUtils.currentTimeInSecond();
            player.setTimeJoinGame(now);
            //start game
            if (game.getPlayers().size() >= GameConfig.MIN_PLAYER_TO_START_GAME && !game.isStarted()) {
                startGame(game);
            }
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
