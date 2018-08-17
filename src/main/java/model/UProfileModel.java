package model;

import bitzero.framework.dao.model.AbstractUProfileModel;

/**
 * Created by niennd on 10/29/2015.
 */
public class UProfileModel extends AbstractUProfileModel {

    long gold;
    String username;
    String displayname;
    String avatarURL;
    String defaultAvatar;

    public UProfileModel(int userID) {
        super(userID);
        this.username = "";
        this.displayname = "";
        this.avatarURL = "";
        this.gold = 0;
        this.defaultAvatar = "";
    }

    @Override
    public void update() {
        boolean hasChange = false;
        if (hasChange) {
            save();
        }
    }

    public int getTotalExp() {
        return 0;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDisplayname() {
        return displayname;
    }

    public void setDisplayname(String displayname) {
        this.displayname = displayname;
    }

    public String getAvatarURL() {
        return avatarURL;
    }

    public void setAvatarURL(String avatarURL) {
        this.avatarURL = avatarURL;
    }

    public String getDefaultAvatar() {
        return defaultAvatar;
    }

    public void setDefaultAvatar(String defaultAvatar) {
        this.defaultAvatar = defaultAvatar;
    }

    public long getGold() {
        return gold;
    }

    public void setGold(long gold) {
        this.gold = gold;
    }

    @Override
    public String toString() {
        return getUserID() +
                "|" + getUsername() +
                "|" + getGold() +
                "|" + getCoin();
    }
}
