package domain.gameplay.service;

import bitzero.server.entities.User;
import domain.gameplay.*;
import domain.gameplay.util.PlayerUtil;
import handler.request.GameRequestHandlerImpl;

/**
 * Created by pc1 on 12/13/2018.
 */
public class PlayerActionServiceImpl implements PlayerActionService {

    static PlayerActionServiceImpl instance = new PlayerActionServiceImpl();

    public PlayerActionServiceImpl() {
    }

    public static PlayerActionServiceImpl getInstance() {
        return instance;
    }

    @Override
    public Player getPlayerByUser(User user) {
        if (user == null) return null;
        return (Player) user.getProperty(Player.class);
    }

    @Override
    public void attachPlayerToUser(User user, Player player) {
        user.setProperty(Player.class, player);
    }

    @Override
    public boolean fold(Player player, HandEntity hand) {
        //Cannot fold out of turn
        if (!player.equals(hand.getCurrentToAct())) {
            return false;
        }

        if (hand.getPlayers().size() == 1) {
//            LogicLogger.getInstance().warn("Must have at least one player hand");
            return false;
        }
        synchronized (player) {
            if (player.isBeingActing()) {
//                LogicLogger.getInstance().warn(player, " is acting, prevent action FOLD");
                return false;
            }
            player.setBeingActing(true);
        }

        Player next = PlayerUtil.getNextPlayerToAct(hand, player);
        PlayerUtil.removePlayerFromHand(player, hand);
        hand.setCurrentToAct(next);

        GameRequestHandlerImpl.sendPlayerAction(hand.getGame(), PlayerAction.FOLD, player, 0);

        player.setActed(true);
        player.setBeingActing(false);
        return true;
    }

    @Override
    public boolean bet(Player player, HandEntity hand, long betAmount) {
        if (!player.equals(hand.getCurrentToAct())) {
//            LogicLogger.getInstance().warn(player, PlayerAction.BET, betAmount, "wrong turn", hand.getCurrentToAct());
            return false;
        }

        PlayerHand playerHand = player.getPlayerHand();

        long toCall = hand.getTotalBetAmount() - playerHand.getRoundBetAmount();
        long total = betAmount + toCall;
        if (total > player.getChips()) {
            total = player.getChips();
            betAmount = total - toCall;
        }

        //Bet must meet the minimum of twice the previous bet.  Call bet amount and raise exactly that amount or more
        //Alternatively, if there is no previous bet, the first bet must be at least the big blind
        CashGameImpl cashGame = (CashGameImpl) hand.getGame();
        boolean lessThanLastBetAmount = betAmount < hand.getLastBetAmount();
        boolean lessThanBigBlind = betAmount < cashGame.getBlindAnte();
        if (lessThanLastBetAmount || lessThanBigBlind) {
            // all-in
            if (total == player.getChips()) {
                // not enough toCall -> move to call action
                if (betAmount <= 0) {
                    return call(player, hand);
                }
            } else {
                if (lessThanLastBetAmount) {
//                    LogicLogger.getInstance().trace(player, PlayerAction.BET, betAmount, "less than last bet amount");
                }
                if (lessThanBigBlind) {
//                    LogicLogger.getInstance().trace(player, PlayerAction.BET, betAmount, "less than blind value");
                }
                // bet incorrect amount
//                LogicLogger.getInstance().trace("bet incorrect amount" + betAmount);
                return false;
            }
        }

        synchronized (player) {
            if (player.isBeingActing()) {
//                LogicLogger.getInstance().warn(player, " is acting, prevent action BET");
                return false;
            }
            player.setBeingActing(true);
        }

        playerHand.setRoundBetAmount(playerHand.getRoundBetAmount() + total);
        player.setChips(player.getChips() - total);
        player.addBetAmount(total);

        hand.setLastBetAmount(betAmount);
        hand.setTotalBetAmount(hand.getTotalBetAmount() + betAmount);

        Player next = PlayerUtil.getNextPlayerToAct(hand, player);
        hand.setCurrentToAct(next);

        GameRequestHandlerImpl.sendPlayerAction(hand.getGame(), PlayerAction.BET, player, betAmount);

        player.setActed(true);
        player.setBeingActing(false);
        return true;
    }

    @Override
    public boolean call(Player player, HandEntity hand) {
        if (!player.equals(hand.getCurrentToAct())) {
            System.out.println("not in acting");
//            LogicLogger.getInstance().warn(player, PlayerAction.CALL, "wrong turn", hand.getCurrentToAct());
            return false;
        }

        PlayerHand playerHand = player.getPlayerHand();
        if(playerHand == null)
            return false;

        long toCall = hand.getTotalBetAmount() - playerHand.getRoundBetAmount();
        toCall = Math.min(toCall, player.getChips());
        if (toCall <= 0) {
            return false;
        }

        synchronized (player) {
            if (player.isBeingActing()) {
                System.out.println("player in acting");
//                LogicLogger.getInstance().warn(player, " is acting, prevent action CALL");
                return false;
            }
            player.setBeingActing(true);
        }

        playerHand.setRoundBetAmount(playerHand.getRoundBetAmount() + toCall);
        player.setChips(player.getChips() - toCall);
        player.addBetAmount(toCall);

        Player next = PlayerUtil.getNextPlayerToAct(hand, player);
        hand.setCurrentToAct(next);

        GameRequestHandlerImpl.sendPlayerAction(hand.getGame(), PlayerAction.CALL, player, toCall);

        player.setActed(true);
        player.setBeingActing(false);
        hand.getGame().tryNewBettingRound(next);
        return true;
    }

    @Override
    public boolean check(Player player, HandEntity hand) {
        return false;
    }
}
