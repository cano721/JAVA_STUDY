package 백트래킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj11729_하노이탑이동순서 {
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // 하노이 탑 전체 경우의 수 → 2ⁿ-1
        int K = (int) (Math.pow(2, N) -1);
        sb.append(K + "\n");

        hanoi(N, 1, 2, 3);

        System.out.println(sb);
    }

    public static void hanoi(int n, int from, int by, int to) {
        if(n==1) {
            sb.append( from + " " + to + "\n");
            return;
        }

        /*
            n-1번째까지 돌을 A→B로 옮김
            n번째 돌을 A→C로 옮김
            1에서 빼놓았던 n-1번째가지의 돌을 B→C로 옮김
        */
        hanoi(n-1, from, to, by);
        sb.append( from + " " + to + "\n");
        hanoi(n-1, by, from, to);
    }
}
