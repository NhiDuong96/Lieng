package model;

import bitzero.framework.dao.model.AbstractUProfileModel;

/**
 * Created by niennd on 10/29/2015.
 */
public class UProfileModel extends AbstractUProfileModel{

    long gold;
    String username;
    String displayname;
    String avatarURL;
    String defaultAvatar;
    int level;
    long exp;
    boolean hasGame;

    public UProfileModel(int userID) {
        super(userID);
        this.username = "";
        this.displayname = "";
        this.avatarURL = "";
        this.gold = 0;
        this.defaultAvatar = "";
        level = 0;
        exp = 0;
    }

    @Override
    public void update() {
        boolean hasChange = false;
        if (hasChange) {
            save();
        }
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setDisplayname(String displayname) {
        this.displayname = displayname;
    }

    public void setAvatarURL(String avatarURL) {
        this.avatarURL = avatarURL;
    }

    public void setGold(long gold) {
        this.gold = gold;
    }

    public boolean getHasGame() {
        return false;
    }

    public void setDefaultAvatar(String defaultAvatar) {
        this.defaultAvatar = defaultAvatar;
    }


    public String getUserName() {
        return username;
    }


    public String getDisplayName() {
        return displayname;
    }


    public int getLevel() {
        return level;
    }

    public long getGold() {
        return gold;
    }


    public long getExp() {
        return exp;
    }


    public String getAvatarURL() {
        return avatarURL;
    }


    public String getDefaultAvatar() {
        return defaultAvatar;
    }

    @Override
    public String toString() {
        return getUserID() +
                "|" + getUserName() +
                "|" + getGold() +
                "|" + getCoin();
    }
}
