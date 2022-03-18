import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;


public class Main {
    static int[] dice;
    static int[][] map;
    static ArrayList<Integer> avgPops = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int L = Integer.parseInt((input[1]));
        int R = Integer.parseInt(input[2]);
        map = new int[N][N];
        for(int i=0; i< N; i++){
            String[] nums = br.readLine().split(" ");
            int[] row = new int[N];
            for(int j=0; j<N; j++) row[j] = Integer.parseInt(nums[j]);
            map[i] = row;
        }
        int cnt =0;
        while(distribute( bfs(N,L,R) , N))  cnt++;

        System.out.println(cnt);
    }
    public static  boolean  distribute(int[][] check, int N){
        boolean hasChanged =  false;

        for(int i=0; i< N; i++){
            for(int j=0; j<N; j++){
                int val =avgPops.get(check[i][j]);
                if(map[i][j] != val){
                    map[i][j] = val;
                    hasChanged= true;
                }
            }
        }
        return hasChanged;
    }
    public static int[][] bfs(int N,int L, int R){
        int[][] check = new int[N][N];
        int[] dx = new int[]{0,0,-1,1};
        int[] dy = new int[]{-1,1,0,0};
        int color = 1;
        Queue<int[]> q = new LinkedList<>();
        avgPops= new ArrayList<>();
        avgPops.add(0); // for idx

        for(int j=0; j<N; j++){
            for(int k=0; k<N; k++){
                int sum = 1;
                int totPop = map[j][k];
                if(check[j][k] == 0){ q.offer(new int[]{j,k}); check[j][k] = color;}
                while(!q.isEmpty()){
                    int[] pos = q.poll();
                    for(int i=0; i<4; i++){
                        int x = pos[1];
                        int y = pos[0];
                        int posX = x + dx[i];
                        int posY = y + dy[i];
                        if(posX<0||posX>=N||posY<0||posY>=N||check[posY][posX]!=0) continue;
                        int diff =Math.abs(map[y][x]-map[posY][posX]);
                        if(diff<L||diff>R) continue;
                        totPop += map[posY][posX];
                        check[posY][posX] = color;
                        q.offer(new int[]{posY,posX,color});
                        sum++;
                    }
                }
                avgPops.add(totPop/sum);
                color++;
            }
        }

        return check;
    }


}