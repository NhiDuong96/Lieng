package config;

import domain.gameplay.evaluator.HandRank;
import domain.gameplay.rules.HandRankLoader;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by pc1 on 12/12/2018.
 */
public class GameConfig {
    public static Map<Long, HandRank> HAND_RANKS = HandRankLoader.loadHandRankResource("HandRanks.dat");
}
