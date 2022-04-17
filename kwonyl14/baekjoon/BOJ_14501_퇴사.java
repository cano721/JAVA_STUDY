package day220417;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14501_퇴사 {
    static int N, time[], pay[];


    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in))	;
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        time = new int[N+2];
        pay = new int[N+2];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            time[i] = Integer.parseInt(st.nextToken());
            pay[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = N; i > 0; i--) {
            if(i+time[i] > N+1 ) {
                pay[i] = pay[i+1];
                continue;
            }
            pay[i] = Math.max(pay[i+1], pay[i]+pay[i+time[i]]);
        }
        int ans = 0;
        ans = Math.max(ans, pay[1]);
        System.out.println(ans);
    }
}
