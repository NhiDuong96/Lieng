package domain.gameplay;

import bitzero.server.entities.Room;
import bitzero.server.entities.User;
import domain.gameplay.util.GameUtil;
import domain.lobby.format.AbstractGameFormat;
import domain.lobby.option.BuyInOption;
import domain.lobby.option.PlayMode;
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


    public static CashGameImpl createCashGame(Room room, AbstractGameFormat gf, PlayMode playMode) {
        GameStructure gameStructure = GameUtil.buildGameStructure(gf, playMode);
        return new CashGameImpl(room, gameStructure);
    }
}
