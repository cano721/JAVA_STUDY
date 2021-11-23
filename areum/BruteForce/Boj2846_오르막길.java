package BruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj2846_오르막길 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr =new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i]=Integer.parseInt(st.nextToken());
        }

        int max =0;
        int temp=0;

        for (int i = 1; i < N; i++) {
            if(arr[i-1]<arr[i]) { // 오르막길 여부 체크
                temp+= (arr[i]-arr[i-1]);
            }else {
                temp=0; // 새로운 오르막길 시작
            }
            max = Math.max(temp, max);
        }

        System.out.println(max);
    }
}
