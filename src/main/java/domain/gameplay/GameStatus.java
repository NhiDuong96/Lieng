package domain.gameplay;

public enum GameStatus {
    NOT_STARTED((byte) 0),
    SEATING((byte) 1),
    PRE_FLOP((byte) 2),
    SHOW_DOWN((byte) 3),
    ANTE((byte) 4);

    byte code;

    GameStatus(byte code) {
        this.code = code;
    }

    public byte getCode() {
        return code;
    }
}
