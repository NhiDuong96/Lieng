package domain.gameplay.service;

import bitzero.server.entities.User;
import domain.gameplay.HandEntity;
import domain.gameplay.Player;

public interface PlayerActionService {

    Player getPlayerByUser(User user);

    void attachPlayerToUser(User user, Player player);

    boolean fold(Player player, HandEntity hand);

    boolean bet(Player player, HandEntity hand, long betAmount);

    boolean call(Player player, HandEntity hand);

    boolean check(Player player, HandEntity hand);
}
