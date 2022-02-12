import java.util.*;

class Solution {
    static int max;
    static int[] answer;
    
    public int[] solution(int n, int[] info) {
        answer = new int[11];
        dfs(0, n, 0, new int[n], info);
        return max==0? new int[]{-1} : answer;
    }

    public static void dfs(int start, int n, int idx, int[] result, int[] info) {
        int lion = 0;
        int apeach = 0;
        if (idx == n) {
            int[] map = new int[11];
            for (int score : result) {
                map[10 - score]++;
            }

            for (int i = 0; i < 11; i++) {
                int score = 10 - i;
                if (map[i] > info[i]) {
                    lion += score;
                } else if (map[i] < info[i]) {
                    apeach += score;
                } else if (info[i] != 0 && map[i] == info[i]) {
                    apeach += score;
                }
            }

            if (lion - apeach > max) {
                max = lion - apeach;
                answer = map;
            }
            return;
        }

        for (int i = start; i < 11; i++) {
            result[idx] = i;
            dfs(i, n ,idx+1, result, info);
        }
    }
}
