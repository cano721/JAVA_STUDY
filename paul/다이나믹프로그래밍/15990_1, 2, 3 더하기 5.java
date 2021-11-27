/**
 *  1부터 5까지 진행해보면 
 *  n의 수는 n-1 에서 1의 값을 제외한 수, n-2 에서 2의값을 제외한 수, n-3에서 3의 값을 
 *  제외한 수들의 합임을 알 수 있다.
 *  dp[n][m] = k, n의 수에서 끝자리가 m인 수들의 총 합은 k개이다.
 */
 
public class Main {
 
    private static final int MOD = 1_000_000_009;
    private static int T, N;
    private static long[][] cache;
 
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
 
        cache = new long[100_001][4];
        cache[1][1] = 1;
        cache[2][2] = 1;
        cache[3][1] = 1;
        cache[3][2] = 1;
        cache[3][3] = 1;
        for (int i = 4; i <= 100000; i++) {
            cache[i][1] = (cache[i - 1][2] + cache[i - 1][3]) % MOD;
            cache[i][2] = (cache[i - 2][1] + cache[i - 2][3]) % MOD;
            cache[i][3] = (cache[i - 3][1] + cache[i - 3][2]) % MOD;
        }
 
        T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            N = Integer.parseInt(br.readLine());
            long result = (cache[N][1] + cache[N][2] + cache[N][3]) % MOD;
            sb.append(result).append("\n");
        }
 
        System.out.println(sb.toString());
    }
}