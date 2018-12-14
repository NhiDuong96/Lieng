package cmd.test.template;

import domain.gameplay.GameStatusObject;
import domain.gameplay.PlayerStatusObject;


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
	GameStatusObject getObject();

}
