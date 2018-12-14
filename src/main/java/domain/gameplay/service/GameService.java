package domain.gameplay.service;

import domain.gameplay.Game;
import domain.gameplay.Player;
import handler.event.LeaveGameReason;

/**
 * Service to handle the overall game operations
 */
public interface GameService {
    Game startGame(Game game);

    Player joinGame(Game game, Player player);

    boolean quitGame(Player player, LeaveGameReason quitReason, String description);

    boolean temporaryLeaveGame(Player player);
}
