package baekjoon.bronzeⅡ;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*

5
1 2 1 4 6

5

8
12 20 1 3 4 4 11 1

8

6
10 8 8 6 4 3

0

*/


public class Boj_2846 {
    public static void main(String[] args) throws IOException {
        BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        int[] nums = new int[N];
        
        StringTokenizer st = new StringTokenizer(br.readLine(), " "); 
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        
        int ans = 0;
        int temp = 0;
        /* 
         for문을 돌리면서 이전 값보다 현재 값이 크면 오르막길
         임시변수에 그 차이를 더해줌

         크지 않다면 임시 변수 다시 0으로 만듬

         크기 비교가 끝나면 최대값을 가지는 변수를 담아주기. 
        */

        for (int i = 1; i < N; i++) {
            if (nums[i-1] < nums[i]) {
                temp += nums[i] - nums[i-1];
            } else {
                temp = 0;
            }
            ans = Math.max(temp, ans);
        }

        bw.write(ans + "\n");
        bw.flush();
        bw.close();

    }
}
