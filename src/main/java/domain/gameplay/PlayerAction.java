package domain.gameplay;

/**
 * Created by pc1 on 12/18/2018.
 */
public enum PlayerAction {
    FOLD(0),
    CHECK(1),
    BET(2),
    CALL(3),
    QUIT(4),
    BUYIN(5),
    RAISE(6),
    ANTE(7),
    SIDEPOT(8),
    ACTIVE(9),
    ;

    byte code;

    PlayerAction(int i) {
        this.code = (byte) i;
    }

    public byte getCode() {
        return code;
    }
}
