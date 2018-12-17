package cmd.api;


import domain.gameplay.CashGameImpl;
import domain.gameplay.HandEntity;
import domain.gameplay.Player;
import domain.gameplay.holder.Hand;
import model.UProfileModel;

/**
 * Created by pc1 on 11/28/2018.
 */
@Api(entities = {UProfileModel.class, CashGameImpl.class, HandEntity.class, Player.class, Hand.class}, name = "type")
public class ApiTypeTool extends ApiRender{
    public static void main(String[] args) {
        ApiRender.clear();
        new ApiTypeTool().render(true);
    }
}
