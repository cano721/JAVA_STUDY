import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BOJ_2015 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] num = new int[N+1];
        int[] prefix = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            num[i] = Integer.parseInt(st.nextToken());
            prefix[i] = prefix[i - 1] + num[i];
        }
        HashMap<Integer, Integer> hm = new HashMap<>();
        hm.put(0, 1);
        long answer = 0;
        for (int i = 1; i <= N; i++) {

            answer += hm.getOrDefault(prefix[i] - K, 0);
            hm.put(prefix[i], hm.getOrDefault(prefix[i], 0) + 1);
        }
        System.out.println(answer);


    }
}
