package domain.lobby;

import cmd.api.ApiEntity;
import cmd.api.ApiField;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Created by GSN on 11/11/2015.
 */

@ApiEntity
public class RoomStatusObject {
    @ApiField
    public int id;
    @ApiField
    public String name;
    @ApiField
    public String gameStructureId;
    @ApiField
    public byte numberPlayer;
    @ApiField
    public boolean proMode;
    @ApiField
    public boolean lightMode;
    @ApiField
    public List<Abc> fields;



    public RoomStatusObject(int id, String name, String gameStructureId, byte numberPlayer, boolean proMode, boolean lightMode) {
        this.id = id;
        this.name = name;
        this.gameStructureId = gameStructureId;
        this.numberPlayer = numberPlayer;
        this.proMode = proMode;
        this.lightMode = lightMode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGameStructureId() {
        return gameStructureId;
    }

    public void setGameStructureId(String gameStructureId) {
        this.gameStructureId = gameStructureId;
    }

    public byte getNumberPlayer() {
        return numberPlayer;
    }

    public void setNumberPlayer(byte numberPlayer) {
        this.numberPlayer = numberPlayer;
    }

    public boolean isProMode() {
        return proMode;
    }

    public void setProMode(boolean proMode) {
        this.proMode = proMode;
    }

    public boolean isLightMode() {
        return lightMode;
    }

    public void setLightMode(boolean lightMode) {
        this.lightMode = lightMode;
    }
}
