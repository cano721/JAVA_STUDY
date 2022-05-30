import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static int N, M;
    static int[][] map;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        int max = 0;
        map = new int[N][M];
        for(int i=0; i<N; i++){
            char[] num = br.readLine().toCharArray();
            for(int j=0; j<M; j++){
                map[i][j] = num[j] -'0';
            }
        }

        for(int i=0; i< N; i++){
            for(int j= 0 ;j <M; j++){

                if(map[i][j]>=1){
                    if(i>=1 && j>=1){
                        int minSquare = Integer.min(Integer.min(map[i-1][j-1],map[i][j-1]),map[i-1][j]);
                        map[i][j]  = minSquare + 1;
                    }
                    max = Integer.max(max,map[i][j]);
                }

            }
        }

        System.out.println(max*max);
    }
}