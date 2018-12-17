package cmd.lobby.template;

import domain.gameplay.Player;
import domain.gameplay.holder.Hand;


import java.util.Collection;

public interface UserInfoApi
{
	int getUID();
	String getUserName();
	String getDisplayName();
	int getLevel();
	long getGold();
	long getExp();
	String getAvatarURL();
	String getDefaultAvatar();
	boolean getHasGame();

}
