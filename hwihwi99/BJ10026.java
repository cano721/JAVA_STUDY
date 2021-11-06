import java.util.*;

public class BJ10026 {

    static int n;
    static String s;
    static char map[][];
    static boolean visits[][];
    static int dx[] = {-1,0,0,1};
    static int dy[] = {0,1,-1,0};

    public static void main(String args[]) {

        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        map = new char[n+1][n+1];
        visits = new boolean[n+1][n+1];

        for (int i=0; i<n; i++){
            s = sc.next(); // RRRBB
            for (int j=0; j<n; j++){
                map[i][j] = s.charAt(j); // R R R B B
            }
        }

        // normal 인 경우
        int cnt = 0;
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(!visits[i][j]){
                    dfs(i,j);
                    cnt++;
                }
            }
        }
        int normal_cnt = cnt;
        cnt=0;
        visits = new boolean[n+1][n+1];

        // dltonism 인 경우
        // G를 R로 바꿔주고 돌린다.

        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(map[i][j]=='G'){
                    map[i][j] = 'R'; // G를 R로 바꿔준다.
                }
            }
        }
        //

        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(!visits[i][j]){
                    dfs(i,j);
                    cnt++;
                }
            }
        }
        int abnormal_cnt = cnt;
        System.out.println(normal_cnt + " " + abnormal_cnt);

    }

    public static void dfs(int x, int y){
        visits[x][y] = true;
        char tmp_char = map[x][y]; // R
        for(int i=0; i<4; i++){
            int new_x = x+dx[i];
            int new_y = y+dy[i];

            if (new_x<0 || new_y<0 || new_x>n || new_y>n){
                continue;
            }

            if (!visits[new_x][new_y] && map[new_x][new_y] == tmp_char){
                dfs(new_x, new_y);
            }
        }
    }

}
