package cmd.entity;

import cmd.api.Api;
import cmd.api.ApiRender;
import domain.lobby.Abc;
import domain.lobby.RoomStatusObject;


/**
 * Created by pc1 on 11/28/2018.
 */
@Api(entities = {RoomStatusObject.class, Abc.class}, name = "type")
public class ApiTypeTool extends ApiRender{
    public static void main(String[] args) {
        ApiRender.clear();
        new ApiTypeTool().render(true);
    }
}
