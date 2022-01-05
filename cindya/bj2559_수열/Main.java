package cindya.bj2559_수열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()), k = Integer.parseInt(st.nextToken());
        int[] temperatures = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int start = 0, end = k, sum = 0, max;

        for(int i = 0; i < k; i++)
            sum += temperatures[i];
        max = sum;

        while (end < n){
            sum -= temperatures[start++];
            sum += temperatures[end++];
            max = Math.max(max, sum);
        }

        System.out.println(max);
        br.close();
    }
}
