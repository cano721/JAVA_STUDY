package studyGroup.april.april10;

/*
이분탐색
가장 작은 경우, 가장 큰 경우 설정 / start, end
mid 관한 알고리즘 구현
검증 작업 if에 작을 때 else에 클 때, 정답 적용
*/



import java.util.*;
import java.lang.*;

public class 입국심사 {

    public static void main(String[] args) {
        int n = 6;
        int[] times = {7, 10};

        System.out.println(solution(n, times));
    }

    public static long solution(int n, int[] times)
    {
        long answer = 0;

        Arrays.sort(times);

        long start = 0;
        long end = times[times.length - 1] * (long)n;


        while(start <= end)
        {
            long mid = (start + end)/2;

            long count = 0;

            for(int one : times)
            {
                count += mid / one;
            }

            if (count < n)
            {
                start = mid + 1;
            }
            else
            {
                end = mid - 1;
                answer = mid;
            }
        }
        return answer;
    }

}
