package studyGroup.may.may19;

import java.util.*;

public class 스티커모으기2 {

    public static void main(String[] args) {
        int[] sticker = {14, 6, 5, 11, 3, 9, 2, 10};

        System.out.println(solution(sticker));
    }

    public static int solution(int sticker[]) {
        int answer = 0;

        int n = sticker.length;

        // 길이가 1일 때의 예외 처리
        if(n == 1) return sticker[0];

        int[] dp1 = new int[100001]; // 처음을 붙였을 경우
        int[] dp2 = new int[100001];
        dp1[1] = sticker[0];


        for(int i = 2; i < n; i++)
        {
            dp1[i] = Math.max(dp1[i-2] + sticker[i-1], dp1[i-1]);
        }

        dp2[2] = sticker[1];
        for(int i = 3; i < n+1; i++)
        {
            dp2[i] = Math.max(dp2[i-2] + sticker[i-1], dp2[i-1]);
        }



        answer = Math.max(dp1[n-1], dp2[n]);

        return answer;
    }

}
