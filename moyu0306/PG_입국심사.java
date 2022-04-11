import java.util.*;

class Solution {
    public long solution(int n, int[] times) {
        Arrays.sort(times);
        int len = times.length;
        long hi = (long)n*(long)times[len-1];
        long lo = 0;
        long answer = Long.MAX_VALUE;
        while(lo<=hi){
            long mid = (lo+hi)/(long)2;
            long sum = 0;
            for(int t: times){
                sum += mid/(long)t;
            }
            if(sum>=(long) n){
                hi = mid - 1;
                answer = Long.min(answer,mid);
            }else lo = mid + 1;
        }
        return answer;
    }
}