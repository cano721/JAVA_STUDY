package day220505;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_3020_개똥벌레 {

    static int N, H, bottom[], top[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        bottom = new int[H+1];
        top = new int[H+1];

        for (int i = 0; i < N/2; i++) {
            bottom[Integer.parseInt(br.readLine())]++;
            top[Integer.parseInt(br.readLine())]++;
        }

        for (int i = H-1; i > 0; i--) {
            bottom[i] += bottom[i + 1];
            top[i] += top[i + 1];
        }

        int min = 987654321;
        int[] stone = new int[H + 1];
        for (int i = 1; i <= H; i++) {
            stone[i] = bottom[i] + top[H-i+1];
            min = Math.min(min, stone[i]);
        }

        int cnt = 0;
        for (int i = 1; i <= H; i++) {
            if (min == stone[i]) cnt++;
        }

        System.out.println(min + " " + cnt);
    }
}
