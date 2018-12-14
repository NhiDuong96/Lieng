package constant;

public class ErrorDefine {
    public static final byte SUCCESS = 0;
    public static final byte FAIL = 1;
    public static final byte PARAM_INVALID = 2;

    // MAINTAIN
    public static final byte SERVER_UNDER_MAINTAIN = 3;
    //LOGIN
    public static final byte PORTAL_SESSION_KEY_INVALID = 4;
    public static final byte PORTAL_SESSION_EXPIRED = 5;


    public static final byte CAN_NOT_JOIN_TOO_LOW_ROOM = 19;
    public static final byte ROOM_NOT_EXIST = 20;
    public static final byte NOT_ENOUGH_MIN_BUYIN = 21;
    public static final byte OUT_BUYIN_RANGE = 22;
    public static final byte GAME_STRUCTURE_INVALID = 23;
    public static final byte ALREADY_IN_GAME = 24;
    public static final byte ENTERING_GAME = 25;
    public static final byte ROOM_IS_LOCKING = 26;
    public static final byte FRIEND_EXISTED = 28;
    public static final byte FRIEND_LIST_IS_FULL = 29;
    public static final byte ROOM_IS_FULL = 30;
    public static final byte TOO_MUCH_GOLD_TO_RECEIVE_SUPPORT = 31;
    public static final byte REACH_MAX_DAILY_SUPPORT_TIME = 32;

    public static final byte NOT_ENOUGH_NUMBER_INVITED_FRIENDS = 40;
    public static final byte NOT_ENOUGH_TOTAL_GROSS = 41;
    public static final byte OVER_MAX_FRIEND_INVITATION_PER_DAY = 42;

    public static final byte GIFT_CODE_INVALID = 43;
    public static final byte GIFT_CODE_EXPIRED = 44;
    public static final byte GIFT_CODE_IS_USED = 45;
    public static final byte USER_USED_CODE_IN_EVENT = 46;
    public static final byte GIFT_CODE_SYSTEM_MAINTAINING = 47;


    // public static final byte NOT_ENOUGH_

    public static final byte PLAYER_ACTION_INVALID = 50;
    public static final byte PLAYER_ACTION_FAIL = 51;
    public static final byte PLAYER_ACTION_IN_COOLDOWN_TIME = 52;

    // public static final byte NOT_ENOUGH_

    public static final byte PAYMENT_TRANSACTION_IS_NOT_VERIFIED = 60;
    public static final byte PAYMENT_TRANSACTION_DUPLICATED = 61;
    public static final byte PAYMENT_TRANSACTION_SANDBOX_TO_PRODUCTION = 62;
    public static final byte PAYMENT_TRANSACTION_PRODUCTION_TO_SANDBOX = 63;
    public static final byte PAYMENT_PRODUCT_ID_INVALID = 64;
    public static final byte PAYMENT_CAN_NOT_UPDATE_TO_BILLING = 65;

    // pay
    public static final byte NOT_ENOUGH_XU = 100;
    public static final byte NOT_ENOUGH_GOLD = 101;


    // daily mission

    public static final byte QUEST_NOT_COMPLETED= 110;
    public static final byte NOT_REACH_TO_BIG_REWARD= 111;
    public static final byte INVALID_QUEST_ID= 112;
    public static final byte BIG_REWARD_HAD_BEEN_RECEIVED= 113;
    public static final byte INTERNAL_SERVER_ERROR= 114;

}
