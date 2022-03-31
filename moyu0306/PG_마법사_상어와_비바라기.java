import java.io.BufferedReader;
import java.io.InputStreamReader;


public class Main {

    static int N, M;
    static int[][] map;
    static int[][] cloud;
    static int[] dx = new int[]{-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = new int[]{0, -1, -1, -1, 0, 1, 1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        map = new int[N][N];
        cloud = new int[N][N];
        cloud[N - 1][0] = 1;
        cloud[N - 2][0] = 1;
        cloud[N - 1][1] = 1;
        cloud[N - 2][1] = 1;
        for (int i = 0; i < N; i++) {
            String[] nums = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(nums[j]);
            }
        }

        for (int i = 0; i < M; i++) {
            String[] order = br.readLine().split(" ");
            int d = Integer.parseInt(order[0]);
            int s  = Integer.parseInt(order[1]);

            for(int j=0; j<N;j++){
                for(int k=0; k<N; k++){
                    moveCloud(j,k,d,s);
                }
            }
//            debug();
            for(int j=0; j<N;j++){
                for(int k=0; k<N; k++){
                    copyWater(j,k);
                }
            }
//            debug();
            for(int j=0; j<N;j++){
                for(int k=0; k<N; k++){
                    makeCloud(j,k);
                }
            }
//            debug();
        }
        System.out.println(sum());
    }


    public static void moveCloud(int r, int c,int d, int s) { //cloud 기준
        if(cloud[r][c]== 1 || cloud[r][c] ==3){
            int dir = d - 1;
            int posY = (r + dy[dir]*s + N*s)%N;
            int posX = (c + dx[dir]*s + N*s)%N;
            cloud[r][c]--;
            cloud[posY][posX] += 2;
            map[posY][posX]++;
        }

    }

    public static void copyWater(int r, int c){ // cloud 기준
        if(cloud[r][c]!=2) return;

        int cnt =0;
        for(int i= 1; i<8; i+=2){
            int posY = (r+dy[i]);
            int posX = (c+dx[i]);
            if(posY<0||posX<0||posX>=N||posY>=N) continue;
            if(map[posY][posX]>0) cnt++;
        }

        map[r][c] += cnt;
    }

    public static void makeCloud(int r, int c){// map 기준
        if(map[r][c]>=2 && cloud[r][c]<1) {
            map[r][c] -= 2;
            cloud[r][c] = 1;
        }else cloud[r][c] =0;

    }

    public static int sum(){
        int sum =0;
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                sum+= map[i][j];
            }
        }
        return sum;
    }

    public static void debug(){
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                System.out.print(map[i][j]+" ");
            }
                System.out.print("   ");
            for(int j=0; j<N; j++){
                System.out.print(cloud[i][j]+" ");
            }

            System.out.println("");
        }
        System.out.println("");

    }
}