package domain.lobby;

import cmd.api.ApiEntity;
import cmd.api.ApiField;

/**
 * Created by pc1 on 12/12/2018.
 */
@ApiEntity
public class Abc {
    @ApiField
    public int ddd;

    public Abc(int ddd) {
        this.ddd = ddd;
    }
}
