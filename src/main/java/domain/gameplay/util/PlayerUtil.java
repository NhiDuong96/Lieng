package domain.gameplay.util;

import domain.gameplay.*;

import java.util.*;

/**
 * Created by pc1 on 12/18/2018.
 */
public class PlayerUtil {

    public static PlayerStatus getPlayerStatus(Player player) {
        if (player == null) {
            return PlayerStatus.ELIMINATED;
        }

        if (player.isSittingOut()) {
            return PlayerStatus.SIT_OUT_GAME;
        }

        CashGameImpl game = (CashGameImpl) player.getGame();
        if (game == null || !game.isStarted()) {
            return PlayerStatus.SEATING;
        }

        HandEntity hand = game.getCurrentHand();
        if (hand == null) {
            return PlayerStatus.SEATING;
        }
        PlayerHand playerHand = player.getPlayerHand();

        if (playerHand == null || !hand.getPlayers().contains(playerHand)) {
            if (player.getChips() <= 0) {
                return PlayerStatus.ELIMINATED;
            }
            return PlayerStatus.SIT_OUT;
        }

//        if (hand.getCurrentToAct() == null) {
//            //Only one player, everyone else folded, player is the winner
//            if (hand.getPlayers().size() == 1) {
//                return PlayerStatus.WON_HAND;
//            }
//            //Get the list of players who won at least some amount of chips at showdown
//            Map<Player, Long> winners = PlayerUtil.getAmountWonInHandForAllPlayers(hand);
//            if (winners != null && winners.containsKey(player)) {
//                //Player is contained in this collection, so the player was a winner in the hand
//                return PlayerStatus.WON_HAND;
//            } else {
//                //Hand is over but player lost at showdown.
//                return PlayerStatus.LOST_HAND;
//            }
//        }

        if (player.getChips() <= 0) {
            return PlayerStatus.ALL_IN;
        }

        if (!player.equals(hand.getCurrentToAct())) {
            //Small and Big Blind to be determined later?
            //Let controller handle that status
            return PlayerStatus.WAITING;
        }

//        if (hand.getTotalBetAmount() > playerHand.getRoundBetAmount()) {
//            return PlayerStatus.ACTION_TO_CALL;
//        } else if (playerHand.getRoundBetAmount() > 0) {
//            //We have placed a bet but now our action is check?  This means the round of betting is over
//            //TODO still problem when every player checks or BB.  Need additional info to solve this
//            return PlayerStatus.ACTION_TO_CHECK;
//        } else {
//            return PlayerStatus.ACTION_TO_CHECK;
//        }
        return PlayerStatus.ACTION_TO_CALL;
    }


    public static Player getNextPlayerToAct(HandEntity hand, Player startPlayer) {
        Player next = startPlayer;

        //Skip all in players and sitting out players
        boolean lookingForNextPlayer = true;
        List<Player> playersToRemove = new ArrayList<Player>();
        while (lookingForNextPlayer) {
            next = hand.nextPlayer(startPlayer);
            //Escape condition
            if (next.equals(startPlayer)) {
                break;
            }
            //If the player is sitting out, it will not be next to act
            if (next.isSittingOut()) {
                //Player is sitting out and needs to call, automatic fold
                PlayerHand playerHand = next.getPlayerHand();

                if (playerHand != null && hand.getTotalBetAmount() > playerHand.getRoundBetAmount()) {
                    playersToRemove.add(next);
                }
            }
            //If the player is not sitting out and still has chips, then this player is next to act
            else if (next.getChips() > 0) {
                lookingForNextPlayer = false;
            }
        }
        //Remove sitting out players
        for (Player p : playersToRemove) {
            removePlayerFromHand(p, hand);
        }
        return next;
    }

    public static boolean removePlayerFromHand(Player player, HandEntity hand) {
        PlayerHand playerHand = player.getPlayerHand();
        //Player is not in the hand.  Sanity check case.
        if (playerHand == null) {
            return false;
        }

        Collection<Player> players = hand.getPlayers();
//        playerHand.setDead(true);
//        if (!player.isBot() && player.getGame().getGameStructure().getGameType() == GameType.CASH) {
//            ItemHandler.addExp(playerHand.getPlayer().getId(), CharacterUtil.convertChipToExp(playerHand.getBetAmount()), "LeaveCashGame");
//        }
        return players.remove(player);
    }
}
