package domain.gameplay;

import bitzero.server.entities.Room;
import bitzero.server.entities.User;
import domain.lobby.option.BuyInOption;
import model.UProfileModel;
import model.dao.UserDAOImpl;

/**
 * Created by GSN on 11/27/2015.
 */
public class GameFactory {
    public static Player createPlayer(User user, BuyInOption opt) {
        UProfileModel profileModel = UserDAOImpl.getInstance().getUProfile(user.getId());

        if (opt.getBuyinChips() > profileModel.getGold()) {
            return null;
        }

        Player player = new Player(user, opt.getBuyinChips());
        //
        return player;
    }


    public static CashGameImpl createCashGame(Room room) {
        return new CashGameImpl(room, null);
    }
}
