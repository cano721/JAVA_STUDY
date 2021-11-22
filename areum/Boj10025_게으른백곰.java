import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj10025_게으른백곰 {
    // x의 범위 → xi(0 ≤ xi ≤ 1,000,000)
    static final int MAX = 1000001;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] iceInfo = new int[MAX];
        for(int n=0; n<N; n++) {
            st = new StringTokenizer(br.readLine());
            int g = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            iceInfo[x] = g;
        }

        int sum = 0;
        int max = 0;
        int d = K*2+1;

        for(int i=0; i<MAX; i++) {

            if(i>=d)
                sum -= iceInfo[i-d];

            sum += iceInfo[i];

            max = Math.max(max, sum);
        }

        System.out.println(max);
    }
}
