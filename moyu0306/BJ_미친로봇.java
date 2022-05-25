import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static double[] probability;
    static int[] dy = new int[]{0,0,1,-1};
    static int[] dx = new int[]{1,-1,0,0};
    static double cnt = 0;
    static boolean[][] visited;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine()," ");
        N = Integer.parseInt(stk.nextToken());
        visited = new boolean[2*N+1][2*N+1];
        probability = new double[4];
        probability[0] = (double)Integer.parseInt(stk.nextToken())/100.d;
        probability[1] = (double)Integer.parseInt(stk.nextToken())/100.d;;
        probability[2] = (double)Integer.parseInt(stk.nextToken())/100.d;;
        probability[3] = (double)Integer.parseInt(stk.nextToken())/100.d;;

        visited[N][N] = true;
        DFS(N,N,0,1.d);
        System.out.println(cnt);
    }
    public static void DFS(int Y,int X, int stage, double p){
        if(stage == N){
            cnt += p;
            return;
        }

        for(int i=0; i<4; i++){
            int posY = Y + dy[i];
            int posX = X + dx[i];
            if(visited[posY][posX]) continue;
            visited[posY][posX] = true;
            DFS(posY,posX,stage+1, p*probability[i]);
            visited[posY][posX] = false;
        }
    }
}