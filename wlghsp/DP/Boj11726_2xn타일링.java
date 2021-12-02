package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj11726_2xn타일링 {
    static long tile[];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        tile = new long[n + 1];

        tile[1] = 1;
        if (n > 1)
            tile[2] = 2;

        for (int i = 3; i <= n; i++) {
            tile[i] = (tile[i - 2] + tile[i - 1]) % 10007;
        }

        System.out.println(tile[n]);

    }

}