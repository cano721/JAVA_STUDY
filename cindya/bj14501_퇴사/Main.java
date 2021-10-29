package cindya.bj14501_퇴사;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static Map<Integer, int[]> counsels = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int max;

        for(int i = 0; i < n; i++){
            int[] c = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            if(c[0] <= n - i) {
                counsels.put(i, c);
            }
        }

        max = getBiggestMoney(n, 0, 0);

        System.out.println(max);

        br.close();
    }

    private static int getBiggestMoney(int n, int start, int money){
        List<Integer> keys = new ArrayList<>(counsels.keySet());
        int max = 0;

        for(int i = start; i < keys.size(); i++){
            int day = keys.get(i);
            int[] counsel = counsels.get(keys.get(i));
            int nIdx = keys.size();
            for(int j = i + 1; j < keys.size(); j++) {
                if (day + counsel[0] <= keys.get(j)) {
                    nIdx = j;
                    break;
                }
            }
            int res = getBiggestMoney(n, nIdx, money + counsel[1]);
            max = Math.max(max, res);
        }
        if(max == 0) {
            max = money;
        }

        return max;
    }
}