   import java.io.BufferedReader;
    import java.io.InputStreamReader;

public class Main {
        static int N ;
        static int[] left;
        static int[] right;
        static int DP[][];
        public static void main(String[] args) throws Exception {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            N = Integer.parseInt(br.readLine());
            left = new int[N+1];
            right = new int[N+1];
            DP = new int[N+1][N+1];
            for(int i=1; i<N+1; i++){
                left[i] = Integer.parseInt(br.readLine());
            }
            for(int j=1; j<N+1; j++){
                right[j] = Integer.parseInt(br.readLine());
            }
            for(int i=1; i<N+1; i++){
                for(int j=1; j<N+1; j++){
                    DP[i][j] = Integer.max(DP[i-1][j],DP[i][j-1]);
                    if(Math.abs(left[i]-right[j])<=4) DP[i][j] = DP[i-1][j-1] + 1;
                }
            }
            System.out.println(DP[N][N]);
        }
}