import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N,M;
    static int[][] map;
    static ArrayList<int[]> virus;
    static int[] dy = new int[]{-1,1,0,0};
    static int[] dx = new int[]{0,0,-1,1};
    static int answer = Integer.MAX_VALUE;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        virus = new ArrayList<>();
        map = new int[N][N];

        for(int i=0; i<N; i++){
         String[] nums = br.readLine().split(" ");
         for(int j=0; j<N; j++){
             int num = Integer.parseInt(nums[j]);
             if(num == 2) virus.add(new int[]{i,j,0});
             if(num == 1) {
                 num = -1;
                 map[i][j] = num;
             } else map[i][j] = num;
         }
        }

        comb(0,0,0,M);
        if(answer == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(answer);
    }
    public static boolean test(int[][] cmap){
        boolean flag = true;
        for(int i=0; i<N; i++){
            for(int j=0; j<N;j++){
                if(cmap[i][j]==0){ flag = false; break;}
            }
        }
        return flag;
    }
    public static int BFS(int[][] cmap,Queue<int[]> q){

        int time = 0;
        while(!q.isEmpty()){
            int[] pos = q.poll();
            for(int i= 0; i<4; i++){
                int posY = pos[0]+dy[i];
                int posX = pos[1]+dx[i];
                if(posY<0||posX<0||posY>=N||posX>=N||cmap[posY][posX]<0) continue;
                if(cmap[posY][posX] == 2&&test(cmap)) return time;
                cmap[posY][posX] = -2;
                q.offer(new int[]{posY,posX,pos[2]+1});
                time = pos[2]+1;
            }
        }


        if(test(cmap)) return time;
        else return -1;
    }
    public static void comb(int bitMask, int idx, int cnt, int length){
        if(cnt == length){
            int[][] cmap = copyMap();
            Queue<int[]> q = new LinkedList<>();
            for(int j = 0; j<virus.size(); j++) {
                if ((bitMask & (1 << j)) > 0) {
                    cmap[virus.get(j)[0]][virus.get(j)[1]] = -2;
//                    System.out.println(virus.get(j)[0]+" "+virus.get(j)[1]);
                    q.add(virus.get(j));
                }
            }
            int result = BFS(cmap,q);
//            debug(cmap);
            if(result != -1) answer = Integer.min(answer,result);
            return;
        }
        for(int i=idx; i< virus.size(); i++){
             comb(bitMask|(1<<i),i+1,cnt+1,length);
        }
    }

    public static int[][] copyMap(){
        int[][] cmap = new int[N][N];
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                cmap[i][j] = map[i][j];
            }
        }
        return cmap;
    }

//    public static void debug(int[][] cmap){
//        for(int i =0; i<N; i++){
//            for(int j=0; j<N; j++){
//                System.out.print(cmap[i][j]+" ");
//            }
//            System.out.println("");
//        }
//        System.out.println("");
//    }
}