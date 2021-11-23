package BruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Boj7568_덩치 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[][] info = new int[N][N];
        StringTokenizer st;
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            info[i][0] = Integer.parseInt(st.nextToken());
            info[i][1] = Integer.parseInt(st.nextToken());
        }

        for(int i=0; i<N; i++) {
            int cnt = 1;
            for(int j=0; j<N; j++) {

                if(i==j)
                    continue; // 같은 사람 비교 X

                // 키와 몸무게 둘 다 작은 경우에만 cnt++
                if(info[i][0] < info[j][0] && info[i][1] < info[j][1]) {
                    cnt++;
                }
            }
            System.out.print(cnt + " ");
        }
    }
}
