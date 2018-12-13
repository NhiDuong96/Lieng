//package domain.lobby;
//
//import bitzero.framework.ExtensionUtility;
//import bitzero.framework.common.MsgService;
//import bitzero.server.BitZeroServer;
//import bitzero.server.entities.Room;
//import bitzero.server.entities.User;
//import bitzero.server.entities.Zone;
//import cmd.board.ResponseGameInfo;
//import cmd.board.ResponsePlayerJoinGame;
//import cmd.mini_game_BCTC.ResponseMiniGameInfo;
//import cmd.mini_game_BCTC.ResponseMiniGamePlayerJoinGame;
//import common.MyRandom;
//import config.GameConfig;
//import constant.CmdDefine;
//import constant.ErrorDefine;
//import constant.GameConstant;
//import constant.ServerConstant;
//import domain.gameplay.Game;
//import domain.gameplay.GameFactory;
//import domain.gameplay.GameType;
//import domain.gameplay.Player;
//import domain.gameplay.reconnect.CachePlayer;
//import domain.gameplay.service.GameServiceImpl;
//import domain.gameplay.service.PlayerActionServiceImpl;
//import domain.gameplay.service.ServiceManagerImpl;
//import domain.lobby.format.BCTCFormat;
//import domain.lobby.format.CashGameFormat;
//import domain.lobby.format.TournamentFormat;
//import domain.lobby.option.BuyInOption;
//import domain.lobby.option.PlayMode;
//import domain.minigame.BCTCGame;
//import domain.minigame.MiniGameFactory;
//import domain.minigame.service.MiniGameServiceImpl;
//import handler.event.LeaveGameReason;
//import handler.request.EventRequestHandler;
//import handler.request.GameRequestHandler;
//import handler.request.LobbyRequestHandler;
//import log.ZPThaiLog;
//import log.log4j.EventLogger;
//import log.log4j.LogicLogger;
//import model.UProfileModel;
//import model.UserDAOImpl;
//import model.dao.UserDAOImpl;
//
///**
// * Created by GSN on 11/4/2015.
// */
//public class LobbyServiceImpl implements LobbyService {
//    static LobbyServiceImpl instance = new LobbyServiceImpl();
//
//    LobbyServiceImpl() {
//    }
//
//    public static LobbyServiceImpl getInstance() {
//        return instance;
//    }
//
//    public Zone getGameZone(GameType gameType) {
//        return BitZeroServer.getInstance().getZoneManager().getZoneById(gameType.ordinal());
//    }
//
//    public Room createRoom(Zone zone, User owner, String rName, int maxUser, int maxSpectators, String password) {
//        return ExtensionUtility.instance().createRoom(zone, owner, rName, maxUser, maxSpectators, password);
//    }
//
//    public Game getGameByRoom(Room room) {
//        if (room == null) {
//            return null;
//        }
//        return (Game) room.getProperty(Game.class);
//    }
//
//    public void attachGameToRoom(Room room, Game game) {
//        room.setProperty(Game.class, game);
//    }
//
//
//    @Override
//    public byte playCashGame(User user, BuyInOption opt, String structureId, String rName) throws Exception {
//        CashGameFormat reqGameStructure = GameConfig.MAP_CASH_GAME_FORMAT.get(structureId);
//
//        CashGameFormat newRoomFormat = reqGameStructure;
//
//        if (newRoomFormat == null) {
//            // auto select room
//            Room room = LobbyUtil.autoSelectRoom(user, opt, playMode);
//            Game game = getGameByRoom(room);
//            if (game != null) {
//                CashGameFormat cgf = GameConfig.MAP_CASH_GAME_FORMAT.get(game.getGameStructure().getId());
//                byte errorCode = joinRoom(user, room, opt, CmdDefine.PLAY_CASH_GAME);
//                if (errorCode == ErrorDefine.SUCCESS) {
//                    return errorCode;
//                }
//                // continue create game
//                newRoomFormat = cgf;
//            } else {
//                newRoomFormat = LobbyUtil.selectOptimalCashGame(user, opt, playMode);
//            }
//        }
//
//        // create room
//        Player player = null;
//        if (newRoomFormat != null) {
//            player = GameFactory.createPlayer(user, opt, GameType.CASH);
//        }
//        if (player == null) {
//            return ErrorDefine.NOT_ENOUGH_MIN_BUYIN;
//        }
//
//        ExtensionUtility.instance().joinChannel(user, getGameZone(GameType.CASH).getId());
//
//        // create Room
//        boolean customizeRoomName = LobbyUtil.validateRoomName(rName);
//        if (!customizeRoomName) {
//            rName = MyRandom.takeRandom(newRoomFormat.getRoomNameSet());
//        }
//
//        Room room = createRoom(getGameZone(GameType.CASH), user, rName, newRoomFormat.getMaxPlayers(), newRoomFormat.getMaxSpectators(), "");
//
//        if (!customizeRoomName) {
//            room.setName(room.getName() + " " + room.getId());
//        }
//
//        room.setGroupId(newRoomFormat.getId());
//        user.getZone().addRoom(room);
//
//        Game game = GameFactory.createGame(room, newRoomFormat, playMode);
//        GameServiceImpl.getInstance().joinGame(game, player);
//
//        MsgService.responseCommon(user, CmdDefine.PLAY_CASH_GAME);
//        fillBotIntoGame(game, GameConfig.NUMBER_FILLED_BOT);
//
//        ResponseGameInfo res = new ResponseGameInfo();
//        res.game = ServiceManagerImpl.getInstance().buildGameStatus(game);
//        ExtensionUtility.getExtension().send(res, user);
//
//        ZPThaiLog.putCreateTable(game);
//        ZPThaiLog.putJoinTable(player, 3);
//        attachGameToRoom(room, game);
//        return ErrorDefine.SUCCESS;
//    }
//
//    public byte joinRoom(User user, Room room, BuyInOption opt, short joinAction) throws Exception {
//        Game game = getGameByRoom(room);
//        if (game == null) {
//            return ErrorDefine.ROOM_NOT_EXIST;
//        }
//
//        if (user == null || opt == null) {
//            // bot join
//            Player bot = GameFactory.createBotPlayer(game);
//            bot = GameServiceImpl.getInstance().joinGame(game, bot);
//            if (bot == null) {
//                return ErrorDefine.ROOM_IS_FULL;
//            }
//            game.setHasBot(true);
//            ResponsePlayerJoinGame res = new ResponsePlayerJoinGame();
//            res.player = ServiceManagerImpl.getInstance().buildPlayerStatus(game, bot);
//            res.showLastCard = !game.getGameStructure().getPlayMode().isProMode() || game.isCheckShowedLastCard();
//            GameRequestHandler.broadcastGame(game, res);
//            return ErrorDefine.SUCCESS;
//        }
//
//        GameType gameType = game.getGameStructure().getGameType();
//        Player player = GameFactory.createPlayer(user, opt, gameType);
//        if (player == null) {
//            return ErrorDefine.NOT_ENOUGH_MIN_BUYIN;
//        }
//
//        if (gameType == GameType.CASH) {
//            CashGameFormat cashGameFormat = GameConfig.MAP_CASH_GAME_FORMAT.get(game.getGameStructure().getId());
//            if (cashGameFormat == null) {
//                LogicLogger.getInstance().trace("Game structure id invalid ", game.getGameStructure().getId());
//                return ErrorDefine.GAME_STRUCTURE_INVALID;
//            }
//            if (opt.getBuyinChips() < cashGameFormat.getBuyinRange().getMin() || opt.getBuyinChips() > cashGameFormat.getBuyinRange().getMax()) {
//                LogicLogger.getInstance().trace("Out of buy-in range");
//                return ErrorDefine.OUT_BUYIN_RANGE;
//            }
//        }
//
//        Player joinedPlayer = GameServiceImpl.getInstance().joinGame(game, player);
//        if (joinedPlayer == null) {
//            return ErrorDefine.ROOM_IS_FULL;
//        }
//
//        // reconnect old player
//        if (joinedPlayer != player) {
//            MsgService.responseCommon(user, joinAction);
//            setReconnect(joinedPlayer, gameType);
//            boolean success = doReconnect(user);
//            if (success) {
//                return ErrorDefine.SUCCESS;
//            } else {
//                return ErrorDefine.FAIL;
//            }
//        }
//
//        ExtensionUtility.instance().joinChannel(user, getGameZone(gameType).getId());
//        MsgService.responseCommon(user, joinAction);
//        ResponsePlayerJoinGame res = new ResponsePlayerJoinGame();
//        res.player = ServiceManagerImpl.getInstance().buildPlayerStatus(game, player);
//        res.showLastCard = !game.getGameStructure().getPlayMode().isProMode() || game.isCheckShowedLastCard();
//        GameRequestHandler.broadcastGame(game, res);
//
//        room.removeUser(user);
//        ExtensionUtility.instance().joinRoom(user, room);
//
//        ResponseGameInfo responseGameInfo = new ResponseGameInfo();
//        responseGameInfo.game = ServiceManagerImpl.getInstance().buildGameStatus(game);
//        ExtensionUtility.getExtension().send(responseGameInfo, user);
//        ZPThaiLog.putJoinTable(player, joinAction == CmdDefine.JOIN_CASH_GAME ? 2 : 1);
//        return ErrorDefine.SUCCESS;
//    }
//
//    @Override
//    public void initGameChannels() {
//        ExtensionUtility.instance().createChannel(GameType.CASH.ordinal(), "CASH GAME ZONE", ServerConstant.MAX_USERS, ServerConstant.MAX_ROOMS);
//    }
//}
