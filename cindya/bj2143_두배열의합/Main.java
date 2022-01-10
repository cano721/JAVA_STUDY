package cindya.bj2143_두배열의합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        int n = Integer.parseInt(br.readLine()), m;
        int[] sumA = new int[n + 1], sumB;
        long answer = 0;
        Map<Integer, Integer> map = new HashMap<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++) {
            sumA[i] = sumA[i - 1] + Integer.parseInt(st.nextToken()); // a의 누적합
            for(int j = 0; j < i; j++){
                int subA = sumA[i] - sumA[j]; // a[i]까지의 구간합
                map.put(subA, map.getOrDefault(subA, 0) + 1); // map에 구간합의 값 개수 저장
            }
        }

        m = Integer.parseInt(br.readLine());
        sumB = new int[m + 1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= m; i++) {
            sumB[i] = sumB[i - 1] + Integer.parseInt(st.nextToken()); // b의 누적합
            for(int j = 0; j < i; j++){
                int subB = sumB[i] - sumB[j]; // b[i]까지의 구간합
                int std = t - subB; // a의 구간합과 비교할 값 (a의 구간합 + b의 구간합 = t)
                answer += map.getOrDefault(std, 0); // std가 있으면 개수를, 없으면 0을 더함
            }
        }
        System.out.println(answer);
        br.close();
    }
}
