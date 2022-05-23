package day2205.day23;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_18234_당근훔쳐먹기 {

    static class Pair implements Comparable<Pair> {
        int w, p;

        public Pair(int w, int p) {
            this.w = w;
            this.p = p;
        }

        @Override
        public int compareTo(Pair o) {
            if (this.p != o.p) return this.p - o.p;
            else return this.w - o.w;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());

        Pair[] carrot = new Pair[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            carrot[i] = new Pair(w, p);
        }

        Arrays.sort(carrot);

        long answer = 0;
        for (int i = 0; i < N; i++) {
            int w = carrot[i].w;
            int p = carrot[i].p;

            answer += w + (long) p * (i + T - N);
        }

        System.out.println(answer);
    }
}
