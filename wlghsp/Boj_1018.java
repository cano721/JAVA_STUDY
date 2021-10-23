package baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Boj_1018 {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());        
        int m = Integer.parseInt(st.nextToken());
        

        String[] board = new String[50];
        for (int i = 0; i < n; i++) {
            board[i] = br.readLine();
        }

        
        int ret = 64;
        for (int i = 0; i <= n-8; i++) {
            for (int j = 0; j <= m-8; j++) {
                int cnt = 0;
                for (int r = 0; r < 8; r++) {
                    for (int c = 0; c < 8; c++) {
                        if ((r%2 == c % 2 ? 'W' : 'B') == board[i+r].charAt(j+c)) {
                            cnt++;
                        }
                    
                    }
                    
                }
                ret = Math.min(ret, cnt);
                ret = Math.min(ret, 64-cnt);
            }

        }
        bw.write(ret +"\n");
        bw.flush();
        bw.close();
    }
}


