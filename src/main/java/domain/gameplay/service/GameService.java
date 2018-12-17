package domain.gameplay.service;

import domain.gameplay.CashGameImpl;
import domain.gameplay.Player;
import handler.event.LeaveGameReason;

/**
 * Service to handle the overall game operations
 */
public interface GameService {
    CashGameImpl startGame(CashGameImpl game);

    Player joinGame(CashGameImpl game, Player player);

    boolean quitGame(Player player, LeaveGameReason quitReason, String description);

    boolean temporaryLeaveGame(Player player);
}
