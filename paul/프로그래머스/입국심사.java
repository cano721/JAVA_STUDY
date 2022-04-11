import java.util.*;

class Solution {
    long solution(int n, int[] times) {
        long answer = 0;
        Arrays.sort(times);
        long start = 1;
        long end = (long) times[times.length - 1] * n;

        while (start <= end) {
            long sum = 0;
            long mid = (start + end) / 2;
            for (int time : times) {
                sum += mid / time;
            }
            if (sum >= n) {
                end = mid - 1;
                answer = mid;
            } else {
                start = mid + 1;
            }
        }
        return answer;
    }
}