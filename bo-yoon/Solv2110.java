import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solv2110 {
    static int N, M;
    static int[] houses;

    static int binarySearch() {
        int res = 0;
        int left = 1, right = houses[N - 1] - houses[0];
        // 최소 거리와 최대 거리
        
        while (left <= right) {
            int mid = (left + right) / 2;
            int start = houses[0];
            int router = 1;

            for (int i = 0; i < N; i++) {
                if (houses[i] - start >= mid) {
                    router++;
                    start = houses[i];
                }
            }

            if (router >= M) {
                res = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return res;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        StringBuilder ans = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        houses = new int[N];

        for (int i = 0; i < N; i++) {
            houses[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(houses);

        bw.write(binarySearch() + "\n");
        bw.flush();
        bw.close();
        br.close();
        
    }
}

