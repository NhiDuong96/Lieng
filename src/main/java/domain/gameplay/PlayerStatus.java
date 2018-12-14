package domain.gameplay;

public enum PlayerStatus {
    /**
     * The game has not been started
     */
    NOT_STARTED((byte) 0),
    /**
     * Players are taking there seats
     */
    SEATING((byte) 1),
    /**
     * Waiting for another player to act
     */
    WAITING((byte) 2),
    /**
     * Player is still in the hand, but has committed all of his/her chips and will take no further action
     */
    ALL_IN((byte) 3),
    /**
     * The hand went to showdown, but the player lost
     */
    LOST_HAND((byte) 4),
    /**
     * Won chips in the previous hand.  The old English for won is winnan.
     */
    WON_HAND((byte) 5),
    /**
     * Player is ready to act.  Player must call or raise to continue.
     */
    ACTION_TO_CALL((byte) 8),
    /**
     * Player is ready to act. There is no bet, so the player may check.
     */
    ACTION_TO_CHECK((byte) 9),
    /**
     * The Player is still in the game but not in the hand.
     */
    // after fold
    SIT_OUT((byte) 10),
    /**
     * The Player has been sat out of the game. The player still has a seat at the table, but is not in the seat.
     */
    SIT_OUT_GAME((byte) 11),
    /**
     * The Player is no longer in the game.
     */
    ELIMINATED((byte) 12);

    byte code;

    PlayerStatus(byte code) {
        this.code = code;
    }

    public byte getCode() {
        return code;
    }
}
