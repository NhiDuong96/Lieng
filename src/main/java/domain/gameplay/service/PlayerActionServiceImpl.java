package domain.gameplay.service;

import bitzero.server.entities.User;
import domain.gameplay.HandEntity;
import domain.gameplay.Player;

/**
 * Created by pc1 on 12/13/2018.
 */
public class PlayerActionServiceImpl implements PlayerActionService {

    static PlayerActionServiceImpl instance = new PlayerActionServiceImpl();

    public PlayerActionServiceImpl() {
    }

    public static PlayerActionServiceImpl getInstance() {
        return instance;
    }

    @Override
    public Player getPlayerByUser(User user) {
        if (user == null) return null;
        return (Player) user.getProperty(Player.class);
    }

    @Override
    public void attachPlayerToUser(User user, Player player) {
        user.setProperty(Player.class, player);
    }

    @Override
    public boolean fold(Player player, HandEntity hand) {
        return false;
    }

    @Override
    public boolean bet(Player player, HandEntity hand, long betAmount) {
        return false;
    }

    @Override
    public boolean call(Player player, HandEntity hand) {
        return false;
    }
}
