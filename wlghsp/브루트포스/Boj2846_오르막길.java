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

public class Boj2846_오르막길 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine()); // 수열의 크기 N 

        int[] nums = new int[N]; // N크기의 정수 배열 nums  선언  

        StringTokenizer st = new StringTokenizer(br.readLine(), " "); // StringTokenizer로 한 줄로 들어온 숫자 들을 나눈다. ex) 1 2 1 4 6
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken()); // N개의 양의 정수 nums 배열에 집어 넣기 
        }

        int ans = 0; // 최대값  
        int temp = 0; // 길이 임시변수 
        /*
         * for문을 돌리면서 이전 값보다 현재 값이 크면 오르막길 임시변수에 그 차이를 더해줌
         * 
         * 크지 않다면 임시 변수 다시 0으로 만듬
         * 
         * 크기 비교가 끝나면 최대값을 가지는 변수를 담아주기.
         */

        for (int i = 1; i < N; i++) {
            if (nums[i - 1] < nums[i]) { // 뒷 요소가 앞 요소보다 크다면 오르막길이다. 
                temp += nums[i] - nums[i - 1]; // 뒷 요소에서 앞 요소의 차를 빼서 temp에 더해줌 
            } else { // 뒷 요소가 앞 요소보다 크지 않다면 
                temp = 0; //  임시변수를 0으로 만든다. 
            }
            ans = Math.max(temp, ans); // 임시변수와 현재까지 가장 큰 오르막길의 길이 중에 큰 수를 결과 변수에 담는다.  
        }

        bw.write(ans + "\n");
        bw.flush();
        bw.close();

    }
}
