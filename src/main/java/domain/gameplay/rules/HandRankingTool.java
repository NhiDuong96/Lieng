package domain.gameplay.rules;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by pc1 on 12/6/2018.
 */
public class HandRankingTool {

    static int index = 0;
    public static Map<Long, Integer> rankMap = new HashMap<>();
    static Map<Long, Integer> closedRank = new HashMap<>();
    public static void recurse(int[] A, String out, long key , int i, int n, int k){
        if(k > n){
            return;
        }

        if(k == 0){
            index++;
            System.out.println(out + " " + key + " ->" + index);
            rankMap.put(key, -1);
            return;
        }

        for(int j = i; j < n; j++){
            //o
            long outtemp = key;
            //System.out.println(A[j]);
            outtemp |= (1L << A[j]);
            recurse(A, out + " " + A[j], outtemp, j+1, n, k-1);
            while (j < n - 1 && A[j] == A[j+1]){
                j++;
            }
        }
    }

    public static void main(String[] args) throws Exception {
//        int[] cards = new int[52];
//        for(int i = 0; i < 52 ; i++){
//            cards[i] = i;
//        }
//        int k = 3;
//        int out[] = new int[3];
//        recurse(cards, "",0L, 0, cards.length, k);
//        writeFile("data.txt");


        //rule("data.txt", "data1.txt", new Xam());
        //rule("data1.txt", "data2.txt", new ThungSanh());
        //rule("data2.txt", "data3.txt", new MatNguoi());
        //rule("data3.txt", "data4.txt", new Sanh());
        //rule("data4.txt", "data5.txt", new Thung());
        //rule("data5.txt", "data6.txt", new Diem());

        //System.out.println(LonHon("data6.txt", getKey(5, 3, 3, 1, 8, 3), getKey(8, 2, 10, 2, 10, 1)));
        printCards(105553149820928L);
    }

    public static void rule(String inputFile, String outFile, HandEstimate estimate) throws Exception {
        readFile(inputFile);
        Map<Long, Float> unsortRank = new HashMap<>();
        int count = 0;
        for(Long key: rankMap.keySet()){
            if(closedRank.containsKey(key)) continue;
            estimate.view(key);
            if(estimate.isAccept()){
                float value = estimate.getValue();
//                System.out.println(value);
//                printCards(key);
                if(unsortRank.containsValue(value)){
//                    System.out.println("same value! ");
                    count++;
                }
                unsortRank.put(key, value);
            }
        }

        Map<Long, Float> sortedRank = unsortRank.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));


        for(Long key: sortedRank.keySet()){
            System.out.println(key + ": " + sortedRank.get(key));
        }

        System.out.println(sortedRank.size() + " value same: " + count);

        int startPoint = sortedRank.size() + closedRank.size();

        for(Long key: sortedRank.keySet()){
            rankMap.replace(key, startPoint--);
        }

        writeFile(outFile);
    }

    private static int rightMostBitPos(long n){
        if((n&1) == 1) return 1;
        return (int) (Math.log(n & -n)/Math.log(2) + 1);
    }

    public static void printCards(long key) {
        int card1, card2, card3, c1, c2, c3;
        card1 = rightMostBitPos(key) - 1; key &= ~(1L << card1);
        card2 = rightMostBitPos(key) - 1; key &= ~(1L << card2);
        card3 = rightMostBitPos(key) - 1;

        c1 = card1 / 4 + 1;
        c2 = card2 / 4 + 1;
        c3 = card3 / 4 + 1;
        System.out.println(c1 + "-" + card1%4 + " " + c2 + "-" + card2%4 + " " + c3 + "-" + card3%4);
    }

    public static boolean LonHon(String file, long key1, long key2) throws Exception {
        readFile(file);
        System.out.println(rankMap.get(key1) + " < "  + rankMap.get(key2));
        return rankMap.get(key1) < rankMap.get(key2);
    }

    public static long getKey(int c1, int t1, int c2, int t2, int c3, int t3){
        int card1 = (c1-1)*4+t1;
        int card2 = (c2-1)*4+t2;
        int card3 = (c3-1)*4+t3;
        return (1L << card1) | (1L << card2) | (1L << card3);
    }

    public static void writeFile(String fileName) throws IOException {
        File file = new File("C:/Users/pc1/Documents/LiengThai/src/main/java/domain/gameplay/rules/" + fileName);
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        for(Long key: rankMap.keySet()){
            bw.write(key + "|" + rankMap.get(key));
            bw.newLine();
        }
        bw.flush();
        bw.close();
    }

    public static void readFile(String fileName) throws Exception {
        File file = new File("C:/Users/pc1/Documents/LiengThai/src/main/java/domain/gameplay/rules/" + fileName);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line = "";
        int maxRank = -1;
        while ((line = br.readLine()) != null){
            StringTokenizer tokenizer = new StringTokenizer(line, "|");
            long key = Long.valueOf(tokenizer.nextToken().trim());
            int rank = Integer.valueOf(tokenizer.nextToken().trim());
            if(maxRank < rank) maxRank = rank;
            if(rank != -1){
                if(closedRank.containsValue(rank)){
                    System.out.println("error ranking: " + key + " " + rank);
                }
                closedRank.put(key, rank);
            }
            if(rankMap.containsKey(key)){
                System.out.println("error key : " + key);
            }
            rankMap.put(key, rank);
        }
        System.out.println("max rank = " + maxRank);
        System.out.println("closed rank size = " + closedRank.size());
        System.out.println("un rank size = " + (22100 - closedRank.size()));

        br.close();
    }
}
