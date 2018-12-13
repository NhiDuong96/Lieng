package domain.gameplay.rules;

import bitzero.server.util.BinaryHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Iterator;

/**
 * Created by niennd on 12/7/2015.
 */
public class HandRankConverter {
    private static int[] groupRankValue = new int[]{52,96,300,900,2000,22100};
    public static void main(String args[]) throws Exception {
        HandRankingTool.readFile("data6.txt");
        String path = "C:/Users/pc1/Documents/LiengThai/res/HandRanks.dat";

        OutputStream outputStream = new FileOutputStream(new File(path));
        for(Long key: HandRankingTool.rankMap.keySet()){
            int value = HandRankingTool.rankMap.get(key);
            outputStream.write(HandRankLoader.longtoBytes(key));
            outputStream.write(HandRankLoader.IntToByteArray(value));
            for(int i = 0; i < groupRankValue.length; i++){
                if(value <= groupRankValue[i]){
                    outputStream.write(HandRankLoader.IntToByteArray(i));
                    break;
                }
            }
        }
        outputStream.flush();
        outputStream.close();
    }

}
