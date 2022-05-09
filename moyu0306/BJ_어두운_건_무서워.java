import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static int R;
    static int C;
    static int Q;
    static int[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input= br.readLine().split(" ");
        R = Integer.parseInt(input[0]);
        C = Integer.parseInt(input[1]);
        Q = Integer.parseInt(input[2]);

        map = new int[R][C];
        for(int i=0; i<R; i++){
            String[] nums = br.readLine().split(" ");
            for(int j=0; j<C; j++){
                if(i>=1) map[i][j]+= map[i-1][j];
                if(j>=1) map[i][j]+= map[i][j-1];
                if(i>=1&&j>=1) map[i][j] -= map[i-1][j-1];
                map[i][j]+= Integer.parseInt(nums[j]);
            }
        }
        for(int i=0; i<Q; i++){
            String[] query = br.readLine().split(" ");
            System.out.println(getAverage(
                    Integer.parseInt(query[0])-1,
                    Integer.parseInt(query[1])-1,
                    Integer.parseInt(query[2])-1,
                    Integer.parseInt(query[3])-1
            ));
        }
    }
    public static int getAverage(int r1, int c1, int r2, int c2){
        int num = (r2-r1+1)*(c2-c1+1);
        int sum = map[r2][c2];
        if(r1>=1) sum -= map[r1-1][c2];
        if(c1>=1) sum -= map[r2][c1-1];
        if(r1>=1 &&c1>=1) sum+= map[r1-1][c1-1];
        return sum/num;
    }
}