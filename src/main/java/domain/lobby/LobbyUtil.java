package domain.lobby;

import bitzero.server.entities.Room;
import bitzero.server.entities.User;
import config.GameConfig;
import domain.gameplay.Game;
import domain.gameplay.GameType;
import domain.lobby.format.CashGameFormat;
import domain.lobby.option.BuyInOption;
import domain.lobby.option.PlayMode;
import model.UProfileModel;
import model.dao.UserDAOImpl;

import java.util.List;

/**
 * Created by pc1 on 12/13/2018.
 */
public class LobbyUtil {
    public static Room autoSelectRoom(User user, BuyInOption buyInOption, PlayMode playMode){
        UProfileModel profileModel = UserDAOImpl.getInstance().getUProfile(user.getId());

        long currentGold = profileModel.getGold() / GameConfig.OPTIMIZE_CASH_MATCHING_RATIO;

        if (currentGold < GameConfig.MIN_GOLD_TO_PLAY_CASH_GAME) {
            currentGold = profileModel.getGold();
        }

        CashGameFormat optimalCashGame = selectOptimalCashGame(user, buyInOption, playMode);

        if (optimalCashGame == null) {
            return null;
        }
        int channelID = optimalCashGame.getChannelID();

        int deep = 0;
        while (GameConfig.MAP_CASH_GAME_CHANNEL.containsKey(channelID)) {
            CashGameChannel cashGameChannel = GameConfig.MAP_CASH_GAME_CHANNEL.get(channelID);
            for (CashGameFormat format : GameConfig.LIST_ALL_CASH_GAME_FORMAT) {
                if (!cashGameChannel.getGameFormats().containsKey(format.getId())) {
                    continue;
                }
                // check light mode
                if (format.isLightFormat() != playMode.isLightMode() || format.isProModeFormat() != playMode.isProMode()) {
                    continue;
                }
                if (format.getBuyinRange().getMin() <= currentGold) {
                    System.out.println("find: " + format.getId());
                    List<Room> roomList = LobbyServiceImpl.getInstance().getGameZone(GameType.CASH).getRoomListFromGroup(format.getId());
                    int minPlayers = Math.min(optimalCashGame.getMaxPlayers(), GameConfig.OPTIMIZE_CASH_MATCHING_MAX_PLAYER);
                    Room minR = null;
                    for (Room r : roomList) {
                        Game game = LobbyServiceImpl.getInstance().getGameByRoom(r);
                        if (game != null && game.getPlayers().size() < minPlayers) {
                            minR = r;
                            break;
                        }
                    }
                    if (minR != null) {
                        buyInOption.setBuyinChips(Math.min(currentGold, format.getBuyinRange().getMax()));
                        return minR;
                    }
                }
            }
            channelID--;
            deep++;
            if (deep > GameConfig.OPTIMIZE_CASH_MATCHING_DEEP) {
                break;
            }
        }
            return null;
    }

    public static CashGameFormat selectOptimalCashGame(User user, BuyInOption buyInOption, PlayMode playMode) {
        UProfileModel profileModel = UserDAOImpl.getInstance().getUProfile(user.getId());
        long currentGold = profileModel.getGold() / GameConfig.OPTIMIZE_CASH_MATCHING_RATIO;

        if (currentGold < GameConfig.MIN_GOLD_TO_PLAY_CASH_GAME) {
            currentGold = profileModel.getGold();
        }

        for (CashGameFormat format : GameConfig.LIST_ALL_CASH_GAME_FORMAT) {
            if (format.getBuyinRange().getMin() <= currentGold
                    && playMode.isProMode() == format.isProModeFormat()
                    && playMode.isLightMode() == format.isLightFormat()) {
                System.out.println(currentGold + " " + format.getBuyinRange().getMax());
                buyInOption.setBuyinChips(Math.min(currentGold, format.getBuyinRange().getMax()));
                return format;
            }
        }
        return null;
    }
}
