package domain.gameplay.service;

import cmd.game.response.GameResponseExtension;
import cmd.game.template.NewHandEntityApi;
import config.GameConfig;
import domain.gameplay.*;
import domain.gameplay.card.Deck;
import domain.gameplay.holder.Hand;
import domain.gameplay.holder.HandUtils;

import java.util.*;

/**
 * Created by pc1 on 12/17/2018.
 */
public class GameHandServiceImpl implements GameHandService {

    static GameHandServiceImpl instance = new GameHandServiceImpl();

    public GameHandServiceImpl() {
    }

    public static GameHandServiceImpl getInstance() {
        return instance;
    }
    @Override
    public HandEntity startNewHand(CashGameImpl game) {
        synchronized (game){
            HandEntity handEntity = new HandEntity();
            handEntity.setGame(game);

            Deck deck = new Deck();

            for(Player player: game.getPlayers()){
                if(player.getChips() > 0){
                    PlayerHand playerHand = new PlayerHand();
                    playerHand.setHand(new Hand(deck.dealCards(3)));
                    player.setPlayerHand(playerHand);

                    //todo importtant
                    handEntity.addPlayer(player.getGamePosition(), player);
                }
                //clear playerHand of prev handEntity
                else
                    player.setPlayerHand(null);
            }

            if(handEntity.getNumOfParticipatingPlayers() < GameConfig.MIN_PLAYER_TO_START_GAME){
//                LogicLogger.getInstance().trace("Number participants less than min require");
                return null;
            }

            game.setCurrentHand(handEntity);

            //todo : verify dealer
            verifyDealer(handEntity);
            handEntity.setCurrentToAct(game.getFirstBetPlayer(), GameConfig.PRE_FLOP_DELAY_SEC);
            handEntity.setTotalBetAmount(game.getBlindAnte());
            handEntity.setLastBetAmount(game.getBlindAnte());

            //todo : response start new hand

            System.out.println("player in HandEntity: " + handEntity.getPlayers().size());
            for (Player p : handEntity.getPlayers()) {
                if(p.getUser() == null) continue;
                System.out.println("response hand entity");
                GameResponseExtension.sendNewHandEntity(new NewHandEntityApi() {
                    @Override
                    public byte getCurrentPos() {
                        return 0;
                    }

                    @Override
                    public byte[] getCards() {
                        return HandUtils.getCardsArray(p.getHand());
                    }
                }, p.getUser());
            }
            return handEntity;
        }
    }

    private void verifyDealer(HandEntity hand) {
        //start with first player
        CashGameImpl cashGame = (CashGameImpl) hand.getGame();
        //todo
        Player player = hand.getPlayers().iterator().next();
        System.out.println("first player: " + player.getUser().getName());
        cashGame.setFirstBetPlayer(player);
    }

    @Override
    public void endHand(HandEntity hand) {

    }
}
