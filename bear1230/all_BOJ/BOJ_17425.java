import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int MAX = 1000000;
        long[] f = new long[MAX+1];
        for (int i = 1; i <= MAX; i++) {
            for (int j = 1; i * j <= MAX; j++) {
                f[i * j] += i;
            }
        }
        long[] g = new long[MAX + 1];
        for (int i = 1; i <= MAX; i++) {
            g[i] = f[i] + g[i-1];
        }

        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            bw.write(g[n]+"\n");
        }
        bw.flush();
    }
}