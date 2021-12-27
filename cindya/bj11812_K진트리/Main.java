package cindya.bj11812_K진트리;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long n = Long.parseLong(st.nextToken()), k = Long.parseLong(st.nextToken()), q = Long.parseLong(st.nextToken());

        for(int i = 0; i < q; i++){
            st = new StringTokenizer(br.readLine());
            long a = Long.parseLong(st.nextToken()), b = Long.parseLong(st.nextToken());
            int cnt = 0;
            if(k == 1) {
                bw.write((a > b ? a - b : b - a) + "\n");
                continue;
            }
            while (a != b){
                if(a > b)
                    a = ((a - 2) / k) + 1;
                else
                    b = ((b - 2) / k) + 1;
                cnt++;
            }
            bw.write(cnt + "\n");
        }
        br.close();
        bw.close();
    }
}
