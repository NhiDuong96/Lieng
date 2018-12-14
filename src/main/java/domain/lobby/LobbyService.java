package domain.lobby;

import bitzero.server.entities.Room;
import bitzero.server.entities.User;
import bitzero.server.exceptions.BZTooManyRoomsException;
import domain.gameplay.Game;
import domain.gameplay.GameType;
import domain.gameplay.Player;

/**
 * Created by GSN on 11/4/2015.
 */
public interface LobbyService {
    void initGameChannels();

    Game getGameByRoom(Room room);

    byte playCashGame(User user) throws BZTooManyRoomsException;

    void setReconnect(Player player, GameType gameType);

    void clearReconnect(int userId);

    boolean doReconnect(User user);
}
