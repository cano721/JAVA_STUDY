import java.io.*;
import java.util.StringTokenizer;



public class Solv11812 {

    static long N;
    static int K, Q;

    static long lca(long x, long y) {
        long cnt = 0;
        while (x != y) {
            while (x > y) {
                cnt++;
                x = (x + K - 2) / K;
            }
            while (x < y) {
                cnt++;
                y = (y + K - 2) / K;
            }
        }

        return cnt;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Long.parseLong(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            long x = Long.parseLong(st.nextToken());
            long y = Long.parseLong(st.nextToken());
            if (K == 1) {
                System.out.println(Math.abs(x - y));
            } else {
                System.out.println(lca(x, y));
            }

        }

    }
}

