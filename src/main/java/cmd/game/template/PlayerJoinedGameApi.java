package cmd.game.template;

import domain.gameplay.CashGameImpl;
import domain.gameplay.HandEntity;
import domain.gameplay.Player;
import domain.gameplay.holder.Hand;


import java.util.Collection;

public interface PlayerJoinedGameApi
{
	Player getPlayer();
	boolean getShowLastCard();

}
