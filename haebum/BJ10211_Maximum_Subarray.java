/**
 * 누적합으로 풀 예정.
 * 
 * x 배열의 원소들의 합을 나타낸 배열 생성
 * 
 * 2중 포문으로
 * 배열의 시작지점
 * 몇개의 원소를 더할건지 끝지점을 지정하여 구하기
 */
import java.util.*;
import java.io.*;

public class BJ10211_Maximum_Subarray {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        
        int t = Integer.parseInt(br.readLine());

        for(int tc = 0; tc < t; tc++){
            int n = Integer.parseInt(br.readLine());

            st = new StringTokenizer(br.readLine());
            int[] sumArr = new int[n+1];
            int sum = 0;

            for(int i = 1; i <= n; i++){
                int num = Integer.parseInt(st.nextToken());
                sum += num;
                sumArr[i] = sum;
            }

            int answer = Integer.MIN_VALUE;
            // i는 끝지점
            for(int i = 1; i <= n; i++){
                // j는 시작지점
                for(int j = 0; j < i; j++){
                    answer = Math.max(answer,sumArr[i]-sumArr[j]);
                }
            }

            bw.write(answer + "\n");
        }
        bw.flush();
        bw.close();
    }
}
