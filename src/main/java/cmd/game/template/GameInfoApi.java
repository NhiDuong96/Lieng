package cmd.game.template;

import model.UProfileModel;
import domain.gameplay.CashGameImpl;
import domain.gameplay.HandEntity;
import domain.gameplay.Player;
import domain.gameplay.holder.Hand;


import java.util.Collection;

public interface GameInfoApi
{
	CashGameImpl getGame();

}
