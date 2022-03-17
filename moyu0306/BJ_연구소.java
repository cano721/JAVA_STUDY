import java.io.*;
import java.util.LinkedList;
import java.util.Queue;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);

        char[][] map = new char[N][M];
        for(int i=0; i< N; i++){
            map[i] = br.readLine().replace(" ","").toCharArray();
        }
        int totSize = N*M;
        int min = totSize;

        for(int i=0; i<totSize; i++) {
            int irow = i/M, icol = i%M;
            if(map[irow][icol]== '0') map[irow][icol] = '1';
            else continue;
            for(int j=i+1; j<totSize; j++){
                int jrow = j/M, jcol = j%M;
                if(map[jrow][jcol]== '0') map[jrow][jcol] = '1';
                else continue;
                for(int k=j+1; k<totSize; k++){
                    int krow = k/M, kcol = k%M;
                    if(map[krow][kcol]== '0') map[krow][kcol] = '1';
                    else continue;
                    min =Integer.min(BFS(clone(map),N,M),min);
                    map[krow][kcol] = '0';
                }
                map[jrow][jcol] = '0';

            }
            map[irow][icol] = '0';
        }

        System.out.println(totSize-min);
    }
    public static char[][] clone(char[][] origin){
        char[][] cloned = new char[origin.length][origin[0].length];
        for(int i=0; i<origin.length;i++){
            cloned[i] = origin[i].clone();
        }
        return cloned;
    }
    public static int BFS(char[][]map, int N, int M){
        int[] dx = new int[]{-1,1, 0,0};
        int[] dy = new int[]{0,0,-1,1};
        Queue<int[]> q= new LinkedList<>();
        boolean [][] isVisited = new boolean[N][M];
        int cnt =0;


        for(int l =0; l< N; l++){
            for(int k=0; k<M; k++){
                if(map[l][k]=='1') cnt++;
                if(map[l][k]=='2'&&!isVisited[l][k]){
                    q.offer(new int[]{l,k});
                    cnt++;
                    isVisited[l][k] = true;
                }
                while(!q.isEmpty()){
                    int[] virus = q.poll();
                    int row = virus[0];
                    int col = virus[1];
                    for(int i=0; i<4;i++){
                        int r = row+dy[i];
                        int c = col+dx[i];
                        if(r<0||r>=N||c<0||c>=M||map[r][c] != '0') continue;
                        map[r][c] ='2';
                        cnt++;
                        isVisited[r][c] = true;
                        q.offer(new int[]{r,c});
                    }


                }
            }
        }
        return cnt;

    }
}