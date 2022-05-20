import java.util.*;

class Solution {
    public int solution(int sticker[]) {
         
        int[] dp1 = new int[sticker.length];
        int[] dp2 = new int[sticker.length];

        if (sticker.length <= 3) {
            int max = 0;
            for (int i = 0; i < sticker.length; i++) {
                max = Math.max(max, sticker[i]);
            }
            return max;
        }

        dp1[0] = sticker[0];
        dp1[1] = sticker[0];

        dp2[0] = 0;
        dp2[1] = sticker[1];

        for (int i = 2; i < sticker.length; i++) {
            dp1[i] = Math.max(dp1[i - 2] + sticker[i], dp1[i - 1]);
            dp2[i] = Math.max(dp2[i - 2] + sticker[i], dp2[i - 1]);
        }
        
        int n = sticker.length - 1;
        dp1[n] = Math.max(dp1[n - 1], dp1[n - 2]);

        return Math.max(dp1[sticker.length - 1], dp2[sticker.length - 1]);
    }

}
