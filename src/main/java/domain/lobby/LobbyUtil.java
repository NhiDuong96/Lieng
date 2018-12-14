package domain.lobby;

import bitzero.server.entities.Room;
import bitzero.server.entities.User;
import domain.gameplay.GameType;
import model.UProfileModel;
import model.dao.UserDAOImpl;

import java.util.List;

/**
 * Created by pc1 on 12/13/2018.
 */
public class LobbyUtil {
    public static Room autoSelectRoom(User user){
        UProfileModel profileModel = UserDAOImpl.getInstance().getUProfile(user.getId());

        long currentGold = profileModel.getGold();

        List<Room> roomList = LobbyServiceImpl.getInstance().getGameZone(GameType.CASH).getRoomListFromGroup("playNow");

        if(roomList.size() > 0)
            return roomList.get(0);
        else
            return null;
    }
}
