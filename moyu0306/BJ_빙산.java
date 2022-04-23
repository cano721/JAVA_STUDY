mport java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] map;
    static int[] dy = new int[]{0,0,-1,1};
    static int[] dx = new int[]{-1,1,0,0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] code = br.readLine().split(" ");
        N = Integer.parseInt(code[0]);
        M = Integer.parseInt(code[1]);
        map = new int[N][M];
        for(int i=0; i<N; i++){
            String[] nums = br.readLine().split(" ");
            for(int j=0; j<M; j++){
                map[i][j] = Integer.parseInt(nums[j]);
            }
        }

        int status = 1;
        int cnt =0;
        while(status == 1){
            melt();
            status = checkStatus();
            cnt++;
        }
        if(status == -1) System.out.println(cnt);
        else System.out.println(0);

    }
    public static void melt(){
        int[][] cmap = new int[N][M];
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(map[i][j]==0) continue;
                int cnt = 0;
                for(int k=0; k<4; k++){
                    int posY = i + dy[k];
                    int posX = j + dx[k];
                    if(posY<0||posY>=N||posX<0||posX>=M) continue;
                    if(map[posY][posX]==0) cnt++;
                }
                cmap[i][j] = Integer.max(0, map[i][j]- cnt);
            }
        }
        map= cmap;
    }

    public static int checkStatus(){
        boolean[][] cmap = new boolean[N][M];
        boolean flag = false;
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
             if(map[i][j] > 0 && !cmap[i][j]){
                 if(!flag){
                     BFS(cmap, i, j);
                     flag = true;
                 }
                 else return -1;// divided into two
             }
            }
        }
        if(!flag) return 0; // non exist
        return 1; // exist one chunk
    }

    public static void BFS(boolean[][] cmap, int a, int b){
       Queue<int[]> q= new LinkedList<>();
       q.offer(new int[]{a,b});
       while(!q.isEmpty()){
           int[] point = q.poll();
           for(int i= 0; i<4; i++){
               int posY = point[0] + dy[i];
               int posX = point[1] + dx[i];
               if(posY<0||posY>=N||posX<0||posX>=M||cmap[posY][posX]||map[posY][posX]==0) continue;
               cmap[posY][posX] = true;
               q.offer(new int[]{posY,posX});
           }
       }
    }

}