/**
 * 누적합 또는 슬라이딩 윈도우로 풀 수 있을 것 같음.
 * 
 * 여기선 누적합으로 풀 예정.
 * 
 * sumArr = 이전원소부터 현재원소까지의 합을 나타낸 배열 생성
 * 
 * k날짜만큼의 합은 sumArr[k]-sumArr[k-5];
 * 
 * k일부터~ 배열의크기
 */

import java.util.*;
import java.io.*;

public class BJ2559_수열 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] sumArr = new int[n+1];
        int sum = 0;

        for(int i = 1; i <= n; i++){
            int num = Integer.parseInt(st.nextToken());
            sum += num;
            sumArr[i] = sum;
        }

        int answer = Integer.MIN_VALUE;
        for(int i = k; i <= n; i++){
            answer = Math.max(answer,sumArr[i]-sumArr[i-k]);
        }

        System.out.println(answer);
    }
}
