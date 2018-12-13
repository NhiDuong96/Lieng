package cmd.test.template;

import domain.lobby.RoomStatusObject;
import domain.lobby.Abc;


import java.util.List;

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
