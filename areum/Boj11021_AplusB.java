import java.io.*;
import java.util.StringTokenizer;

public class Boj11021_AplusB {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine()); // 테스트 케이스 입력받기

        StringTokenizer st;
        int sum = 0;
        for(int i=1; i<=T; i++) {
            st = new StringTokenizer(br.readLine());
            sum = Integer.parseInt(st.nextToken()) + Integer.parseInt(st.nextToken()); // A + B
            bw.write("Case #" + i + ": " + sum + "\n"); // 출력
        }
        bw.flush();
        bw.close();
    }
}
