package config;

import bitzero.framework.config.JsonLoader;
import bitzero.framework.log4j.BootLogger;
import domain.gameplay.card.HandType;
import domain.gameplay.evaluator.HandRank;
import domain.gameplay.rules.HandRankLoader;
import domain.gameplay.sidepot.SidePotCfg;
import domain.lobby.BlindLevel;
import domain.lobby.BuyinRange;
import domain.lobby.CashGameChannel;
import domain.lobby.format.CashGameFormat;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.*;

/**
 * Created by GSN on 10/29/2015.
 */
public class GameConfig {

    public static long MAIL_ITEM_LIFE_TIME_IN_SEC = GameProperties.getInstance().getInt("MAIL_ITEM_LIFE_TIME_IN_SEC");

    public static int SNAP_GLOBAL_RANKING_DELAY_SEC = GameProperties.getInstance().getInt("SNAP_GLOBAL_RANKING_DELAY_SEC");
    public static int SNAP_GLOBAL_RANKING_PERIOD_SEC = GameProperties.getInstance().getInt("SNAP_GLOBAL_RANKING_PERIOD_SEC");
    public static int GLOBAL_RANKING_UPDATE_PLAYING_DELAY_SEC = GameProperties.getInstance().getInt("GLOBAL_RANKING_UPDATE_PLAYING_DELAY_SEC");

    public static long INIT_USER_DATA_GOLD = GameProperties.getInstance().getLong("INIT_USER_DATA_GOLD");
    public static int INIT_USER_DATA_XU = GameProperties.getInstance().getInt("INIT_USER_DATA_XU");

    public static int MAX_FRIEND_LIST = GameProperties.getInstance().getInt("MAX_FRIEND_LIST");
    public static int MAX_LEADER_BOARD_LIST = GameProperties.getInstance().getInt("MAX_LEADER_BOARD_LIST");

    public static int MAX_TOUR_TOP_LIST = GameProperties.getInstance().getInt("MAX_TOUR_TOP_LIST");
    public static int MAX_TOUR_LEADER_LIST = GameProperties.getInstance().getInt("MAX_TOUR_LEADER_LIST");
    public static int UPDATE_TOUR_LEADER_DELAY_TIME = GameProperties.getInstance().getInt("UPDATE_TOUR_LEADER_DELAY_TIME");
    public static int GLOBAL_TOUR_INVITING_DELAY_TIME = GameProperties.getInstance().getInt("GLOBAL_TOUR_INVITING_DELAY_TIME");

    public static int MAX_ROOM_NAME_LENGTH = GameProperties.getInstance().getInt("MAX_ROOM_NAME_LENGTH");
    public static int MAX_VISIBLE_ROOM = GameProperties.getInstance().getInt("MAX_VISIBLE_ROOM");
    public static int MAX_VISIBLE_ROOM_PER_LEVEL = GameProperties.getInstance().getInt("MAX_VISIBLE_ROOM_PER_LEVEL");
    public static int RECONNECT_CACHE_EXPIRED_TIME_SEC = GameProperties.getInstance().getInt("RECONNECT_CACHE_EXPIRED_TIME_SEC");
    public static int RECONNECT_TOUR_CACHE_EXPIRED_TIME_SEC = GameProperties.getInstance().getInt("RECONNECT_TOUR_CACHE_EXPIRED_TIME_SEC");

    public static int OPTIMIZE_CASH_MATCHING_MAX_PLAYER = GameProperties.getInstance().getInt("OPTIMIZE_CASH_MATCHING_MAX_PLAYER");
    public static int OPTIMIZE_CASH_MATCHING_RATIO = GameProperties.getInstance().getInt("OPTIMIZE_CASH_MATCHING_RATIO");
    public static int OPTIMIZE_CASH_MATCHING_DEEP = GameProperties.getInstance().getInt("OPTIMIZE_CASH_MATCHING_DEEP");
    public static int OPTIMIZE_CASH_MATCHING_ORDER = GameProperties.getInstance().getInt("OPTIMIZE_CASH_MATCHING_ORDER");

    public static int TIMEOUT_PLAYER_ACTION_SEC = GameProperties.getInstance().getInt("TIMEOUT_PLAYER_ACTION_SEC");
    public static int TIMEOUT_BOT_ACTION_SEC = GameProperties.getInstance().getInt("TIMEOUT_BOT_ACTION_SEC");
    public static int TIMEOUT_GAME_IDLE = GameProperties.getInstance().getInt("TIMEOUT_GAME_IDLE");
    public static int TIMEOUT_TOUR_IDLE = GameProperties.getInstance().getInt("TIMEOUT_TOUR_IDLE");
    public static int NEW_HAND_DELAY_SEC = GameProperties.getInstance().getInt("NEW_HAND_DELAY_SEC");
    public static int NEW_HAND_DELAY_FAST_MODE_SEC = GameProperties.getInstance().getInt("NEW_HAND_DELAY_FAST_MODE_SEC");
    public static int PRE_FLOP_DELAY_SEC = GameProperties.getInstance().getInt("PRE_FLOP_DELAY_SEC");
    public static int WINNER_EFFECT_DELAY_SEC = GameProperties.getInstance().getInt("WINNER_EFFECT_DELAY_SEC");


    public static int NUMBER_BOT_PER_CHANNEL = GameProperties.getInstance().getInt("NUMBER_BOT_PER_CHANNEL");
    public static int BOT_AUTO_JOIN_PERIOD_IN_SEC = GameProperties.getInstance().getInt("BOT_AUTO_JOIN_PERIOD_IN_SEC");
    public static int LIMIT_BOT_HAND = GameProperties.getInstance().getInt("LIMIT_BOT_HAND");
    public static int MIN_PLAYER_TO_START_GAME = GameProperties.getInstance().getInt("MIN_PLAYER_TO_START_GAME");
    public static int NUMBER_FILLED_BOT = GameProperties.getInstance().getInt("NUMBER_FILLED_BOT");
    public static int NUMBER_FILLED_BOT_TOUR = GameProperties.getInstance().getInt("NUMBER_FILLED_BOT_TOUR");

    public static int LIMIT_HAND_SITTING_OUT = GameProperties.getInstance().getInt("LIMIT_HAND_SITTING_OUT");
    public static int LIMIT_TIME_SITTING_OUT_CASH_GAME = GameProperties.getInstance().getInt("LIMIT_TIME_SITTING_OUT_CASH_GAME");

    public static int LIMIT_HAND_CLIENT_AUTO = GameProperties.getInstance().getInt("LIMIT_HAND_AUTO");

    public static SidePotCfg SIDE_POT_CFG = null;

    public static long MAX_DAILY_SUPPORT_TIME = 1;

    public static long GOLD_DAILY_SUPPORT;
    public static long GOLD_PROMOTION_GUEST_ACCOUNT;
    public static long GOLD_PROMOTION_NEW_ACCOUNT;

    public static long GOLD_LIKE_FANPAGE;
    public static long GOLD_RATE_APP;
    public static long GOLD_PORTAL_CAMPAIGN;

    public static double COMMISSION_RATE = GameProperties.getInstance().getDouble("COMMISSION_RATE");

    public static double CHIP_TO_EXP_CONVERSION_RATE = GameProperties.getInstance().getDouble("CHIP_TO_EXP_CONVERSION_RATE");

    public static Map<Long, HandRank> HAND_RANKS = HandRankLoader.loadHandRankResource("HandRanks.dat");

    public static List<Integer> BOT_BUY_IN_RANGE_MULTIPILER = Arrays.asList(new Integer[]{50, 75, 100, 125});
    public static Set<String> BOT_NAME_SET = new HashSet<>(Arrays.asList(GameProperties.getInstance().get("BOT_NAME_SET").trim().split(";")));

    public static Map<String, CashGameFormat> MAP_CASH_GAME_FORMAT = new HashMap<>();
    public static Map<Integer, CashGameChannel> MAP_CASH_GAME_CHANNEL = new HashMap<>();
    public static List<CashGameFormat> LIST_ALL_CASH_GAME_FORMAT = new ArrayList<>();
    public static long MIN_GOLD_TO_PLAY_CASH_GAME = 0;

    public static Integer[] DEFAULT_PLAYER_POS = new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8};
    public static Map<Integer, Integer[]> CASH_GAME_PLAYER_POS_MAP = new HashMap<>();

    public static void init() {
        try {
            initGamePlay();
            initCashGame();
        } catch (Exception e) {
            BootLogger.getInstance().getLogger().error("Init config failure", e);
        }
    }

    private static void initGamePlay() throws JSONException {
        JSONObject dataJSON = JsonLoader.loadConfig("Gameplay.json");
        JSONObject shareJSON = dataJSON.getJSONObject("SidepotShare");
        JSONObject handTypeJSON = dataJSON.getJSONObject("SidepotHandType");
        int shareFullLimitRound = shareJSON.getInt("shareFullLimitRound");
        List<Integer> shareRateAfterLimitedRound = new ArrayList<>();
        JSONArray shareRateListJson = shareJSON.getJSONArray("shareRateAfterLimitedRound");
        for (int i = 0; i < shareRateListJson.length(); i++) {
            shareRateAfterLimitedRound.add(shareRateListJson.getInt(i));
        }
        Map<HandType, Map<HandType, Integer[]>> shareHandType = new HashMap<>();
        Iterator<String> keyIter = handTypeJSON.keys();
        while (keyIter.hasNext()) {
            String keyRow = keyIter.next();
            HandType handTypeRow = HandType.valueOf(keyRow);
            shareHandType.put(handTypeRow, new HashMap<HandType, Integer[]>());

            JSONObject rowJson = handTypeJSON.getJSONObject(keyRow);
            Iterator<String> keyColIter = rowJson.keys();
            while (keyColIter.hasNext()) {
                String keyCol = keyColIter.next();
                HandType handTypeCol = HandType.valueOf(keyCol);
                String value = rowJson.getString(keyCol);
                Integer[] shareRateArr = new Integer[2];
                String[] shareRateArrStr = value.split(":");
                shareRateArr[0] = Integer.valueOf(shareRateArrStr[0]);
                shareRateArr[1] = Integer.valueOf(shareRateArrStr[1]);

                shareHandType.get(handTypeRow).put(handTypeCol, shareRateArr);
            }
        }

        SIDE_POT_CFG = new SidePotCfg(shareFullLimitRound, shareRateAfterLimitedRound, shareHandType);
    }

    public static void initCashGame() throws JSONException {
        JSONObject channelData = JsonLoader.loadConfig("Channel.json");
        JSONObject channelJson = channelData.getJSONObject("Channel");
        JSONObject cashGameStructureJson = channelData.getJSONObject("CashGameStructure");
        JSONObject playerPositionJson = channelData.getJSONObject("PlayerPosition");

        MAP_CASH_GAME_FORMAT.clear();
        Iterator<String> keyIter = cashGameStructureJson.keys();
        while (keyIter.hasNext()) {
            String key = keyIter.next();
            JSONObject e = cashGameStructureJson.getJSONObject(key);
            int maxPlayer = e.getInt("maxPlayer");
            int maxSpectator = e.getInt("maxSpectator");
            BlindLevel blindLevel = new BlindLevel(e.getLong("blindLevel"), e.getLong("sidepotValue"));
            long buyinMin = e.getLong("buyInMin");
            long buyinMax = e.getLong("buyInMax");
            boolean lightFormat = e.getInt("light") == 1;
            boolean proModeFormat = e.getInt("proMode") == 1;
            BuyinRange buyinRange = new BuyinRange(buyinMin, buyinMax);
            MAP_CASH_GAME_FORMAT.put(key, new CashGameFormat(key, maxPlayer, maxSpectator, blindLevel, buyinRange, lightFormat, proModeFormat));
        }

        MAP_CASH_GAME_CHANNEL.clear();
        LIST_ALL_CASH_GAME_FORMAT.clear();
        keyIter = channelJson.keys();

        while (keyIter.hasNext()) {
            String key = keyIter.next();
            int id = Integer.valueOf(key);
            JSONObject e = channelJson.getJSONObject(key);
            String name = e.getString("name");
            long minGoldRequire = e.getLong("minRequire");
            Map<String, CashGameFormat> formatsMap = new HashMap<>();
            Set<String> nameSet = new HashSet<>();
            if (e.has("roomName")) {
                String roomNames = e.getString("roomName");
                String[] nameArr = roomNames.split(";");
                nameSet.addAll(Arrays.asList(nameArr));
            }
            boolean available = e.getInt("available") == 1;
            int playerTimeOut = e.getInt("playerTimeout");
            int fastPlayerTimeOut = e.getInt("fastPlayerTimeout");
            int nonInterActiveTimeOut = e.getInt("nonInterActiveTimeOut");
            if (!e.isNull("structure")) {
                JSONArray array = e.getJSONArray("structure");
                for (int i = 0; i < array.length(); i++) {
                    String structureID = array.getString(i);
                    CashGameFormat cashGameFormat = MAP_CASH_GAME_FORMAT.get(structureID);
                    if (cashGameFormat == null) {
                        throw new IllegalArgumentException("Incorrect structure id");
                    }
                    if (available) {
                        LIST_ALL_CASH_GAME_FORMAT.add(cashGameFormat);
                    }
                    if (cashGameFormat.getBuyinRange().getMin() <= MIN_GOLD_TO_PLAY_CASH_GAME || MIN_GOLD_TO_PLAY_CASH_GAME == 0) {
                        MIN_GOLD_TO_PLAY_CASH_GAME = cashGameFormat.getBuyinRange().getMin();
                    }

                    cashGameFormat.setRoomNameSet(nameSet);
                    cashGameFormat.setChannelID(id);
                    formatsMap.put(structureID, cashGameFormat);
                }
            }
            MAP_CASH_GAME_CHANNEL.put(id, new CashGameChannel(id, name, minGoldRequire, formatsMap, nameSet, available, playerTimeOut, fastPlayerTimeOut, nonInterActiveTimeOut));
        }
        Collections.sort(LIST_ALL_CASH_GAME_FORMAT);

        CASH_GAME_PLAYER_POS_MAP.clear();
        keyIter = playerPositionJson.keys();
        while (keyIter.hasNext()) {
            String key = keyIter.next();
            JSONArray array = playerPositionJson.getJSONArray(key);
            Integer[] values = new Integer[array.length()];
            for (int i = 0; i < array.length(); i++) {
                values[i] = array.getInt(i);
            }
            CASH_GAME_PLAYER_POS_MAP.put(Integer.valueOf(key), values);
        }
    }
}
