import java.io.*;
import java.util.*;

public class BOJ_13701 {
    static BitSet bs = new BitSet(33554432);

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int size = st.countTokens();
        int cur;
        for (int i = 0; i < size; i++) {
            cur = Integer.parseInt(st.nextToken());
            if (!bs.get(cur)) {
                bs.set(cur);
                bw.write(cur + " ");
            }
        }
        bw.flush();
        bw.close();
    }
}