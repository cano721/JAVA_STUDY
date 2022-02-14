import java.util.*;

class Solution {
    public String solution(String play_time, String adv_time, String[] logs) {

        int play = getsec(play_time);
        int adt = getsec(adv_time);

        int[] totalt = new int[play + 1];

        for(String str : logs) {
            int start = getsec(str.substring(0,8));
            int end = getsec(str.substring(9, 17));

            for(int i = start; i < end; i++) {
                totalt[i]++;
            }
        }

        long cur = 0;
        
        for(int i = 0; i < adt; i++) {
            cur += totalt[i];
        }

        long max = cur;
        int ans = 0;
        
        for(int i = adt; i < play; ++i) {
            cur = cur + totalt[i] - totalt[i - adt];
            if(max < cur) {
                max = cur;
                ans = i - adt + 1;
            }
        }

       return String.format("%02d:%02d:%02d", ans/3600, ans/60 % 60, ans%60);
    }

    static int getsec(String time) {
        String[] nums = time.split(":");
        
        return Integer.parseInt(nums[0]) * 3600 
            + Integer.parseInt(nums[1]) * 60 
            + Integer.parseInt(nums[2]);
    }
}
