package domain.gameplay.rules;

import bitzero.framework.ExtensionUtility;
import domain.gameplay.card.HandType;
//import domain.gameplay.evaluator.HandRank;
import domain.gameplay.evaluator.HandRank;
import org.apache.log4j.Logger;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

/**
 * Utility class for loading configuration and resource files
 */
public class HandRankLoader {

    private static final int HAND_RANK_SIZE = 22100;//52C3 : to hop 3 cua 52 cards
    private static Logger log = Logger.getLogger(HandRankLoader.class);

    public static void main(String[] args) {
        loadHandRankResource("HandRanks.dat");
    }

    private static String getFullPath(String fileName) {
        String path = System.getProperty("user.dir");
        return path + "/res/" + fileName;
    }

    public static Map<Long, HandRank> loadHandRankResource(String name)
            throws RuntimeException {
        Map<Long, HandRank> handRankMap = new HashMap<>();
        try {
            String filePath = getFullPath(name);
            BufferedInputStream br = new BufferedInputStream(new FileInputStream(filePath));

            long hashValue;//8 bytes
            int mainValue;//4 bytes
            int groupValue;//4 bytes
            //total = 8+4+4 = 16
            int tableSize = HAND_RANK_SIZE * 16; //need 16 byte for
            byte[] b = new byte[tableSize];
            int bytesRead = br.read(b, 0, tableSize);
            if (bytesRead != tableSize) {
                log.error("Read " + bytesRead + " bytes out of " + tableSize);
            }
            for (int i = 0; i < HAND_RANK_SIZE * 4; i+=4) {
                hashValue = bytesToLong(b, i * 4);
                mainValue = byteArrayToInt(b, (i + 2) * 4);
                groupValue = byteArrayToInt(b, (i + 3) * 4);
                //System.out.println(hashValue + " " + mainValue + " " + groupValue);

                if (handRankMap.containsKey(hashValue) || groupValue >= HandType.values().length) {
                    ExtensionUtility.getExtension().trace("Error reading", mainValue, groupValue);
                }
                handRankMap.put(hashValue, new HandRank(mainValue, groupValue));
            }
            br.close();
            return handRankMap;
        } catch (IOException e) {
            throw new RuntimeException("cannot read resource " + name, e);
        }
    }

    public static int byteArrayToInt(byte[] b, int offset) {
        return (b[offset + 3] << 24) + ((b[offset + 2] & 0xFF) << 16)
                + ((b[offset + 1] & 0xFF) << 8) + (b[offset] & 0xFF);
    }

    public static byte[] IntToByteArray(int value) {
        return new byte[]{
                (byte) (value),
                (byte) (value >> 8),
                (byte) (value >> 16),
                (byte) (value >> 24)
        };
    }

    public static byte[] longtoBytes(long x){
        ByteBuffer bf = ByteBuffer.allocate(Long.BYTES);
        bf.putLong(0, x);
        return bf.array();
    }

    public static long bytesToLong(byte[] bytes, int offset){
        ByteBuffer bf = ByteBuffer.allocate(Long.BYTES);
        bf.put(bytes, offset, 8);
        bf.flip();
        return bf.getLong();
    }
}
