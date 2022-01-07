package cindya.bj2015_수들의합_4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()), k = Integer.parseInt(st.nextToken());
        int[] a = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Map<Integer, Integer> map = new HashMap<>();
        int[] sum = new int[n + 1];
        long answer = 0;

        for(int i = 0; i < n; i++)
            sum[i + 1] = a[i] + sum[i];
        map.put(0, 1);
        for(int i = 1; i <= n; i++){
            // sum[i] + sum[i - 1] = k의 식을 충족해야하므로
            // answer에 sum[i] - k == sum[i - 1]번째의 값을 더한다
            answer += map.getOrDefault(sum[i] - k, 0);
            map.put(sum[i], map.getOrDefault(sum[i], 0) + 1);
        }
        System.out.println(answer);
        br.close();
    }
}
