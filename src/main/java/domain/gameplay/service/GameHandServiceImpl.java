package domain.gameplay.service;

import config.GameConfig;
import domain.gameplay.*;
import domain.gameplay.card.Deck;
import domain.gameplay.holder.Hand;

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

            Set<PlayerHand> participatingPlayers = new HashSet<PlayerHand>();

            List<Player> sortedPlayers = new ArrayList<Player>();
            sortedPlayers.addAll(game.getPlayers());
            Collections.sort(sortedPlayers);
            for(Player player: sortedPlayers){
                if(player.getChips() > 0){
                    PlayerHand playerHand = new PlayerHand();
                    playerHand.setHand(new Hand(deck.dealCards(3)));
                    playerHand.setPlayer(player);
                    player.setHand(playerHand.getHand());
                    participatingPlayers.add(playerHand);
                }
            }

            if(participatingPlayers.size() < GameConfig.MIN_PLAYER_TO_START_GAME){
//                LogicLogger.getInstance().trace("Number participants less than min require");
                return null;
            }

            handEntity.setPlayers(participatingPlayers);
            game.setCurrentHand(handEntity);

            //todo : verify dealer

            //todo : response start new hand
            return handEntity;
        }
    }

    @Override
    public void endHand(HandEntity hand) {

    }
}
