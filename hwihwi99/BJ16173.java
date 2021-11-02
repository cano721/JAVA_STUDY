import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.StringTokenizer;

public class BJ16173 {
    static int N;
    static int [][] map;
    static boolean [][] visied;
    static int answer = 0;
    static int [] dx ={0,1};
    static int [] dy ={1,0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int [N][N];
        for(int i = 0; i<N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine()," ");
            for(int j = 0; j<N;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visied = new boolean[N][N];
        dfs(0,0);
        if(answer == 1){
            System.out.println("HaruHaru");
        }else{
            System.out.println("Hing");
        }
    }
    public static void dfs(int Px, int Py){

        // 해당 포인트 방문했다고 표시
        visied[Px][Py] = true;

        // 끝 점에 도달하면?? 종단조건!
        if(map[Px][Py] == -1){
            answer = 1;
            return;
        }

        int go = map[Px][Py]; // 움직여야하는 횟수

        if(Py + go < N && !visied[Px][Py+go])
            dfs(Px,Py+go);
        if(Px + go < N && !visied[Px+go][Py])
            dfs(Px+go,Py);
    }
}
