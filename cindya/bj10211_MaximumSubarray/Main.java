package cindya.bj10211_MaximumSubarray;

import java.io.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int t = Integer.parseInt(br.readLine());

        while (t-- > 0){
            int n = Integer.parseInt(br.readLine());
            int[] x = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int[] max = new int[n];

            Arrays.fill(max, Integer.MIN_VALUE); // 가장 큰 값이 음수일 수 있으므로 MIN_VALUE로 초기화

            // 시작점 i 루프
            for(int i = 0; i < n; i++){
                int sum = 0;
                // 끝점 j 루프
                for(int j = i; j < n; j++){
                    sum += x[j]; // sum에 j번째 수를 더하고
                    max[i] = Math.max(max[i], sum); // max와 비교 후 더 큰 것을 선택
                }
            }
            // max 중 가장 큰 값을 출력
            bw.write(Arrays.stream(max).max().getAsInt() + "\n");
        }
        br.close();
        bw.close();
    }
}
