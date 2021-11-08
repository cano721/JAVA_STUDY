import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 마찬가지로,,,구글링으로 공부했습니다...어렵네여...
 * */

public class BJ2665 {
    static int N;
    static int arr[][], map[][];
    static int moveX[] = {0,1,0,-1};
    static int moveY[] = {-1,0,1,0};
    static int maxValue = Integer.MAX_VALUE;
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N+1][N+1];
        map = new int[N+1][N+1];
        for(int i=1; i<=N; i++) {
            String str = br.readLine();
            for(int j=1; j<=N; j++) {
                arr[i][j] = str.charAt(j-1)-'0';
                map[i][j] = Integer.MAX_VALUE;
            }
        }
        bfs(1,1);
        System.out.println(map[N][N]);
    }

    public static void bfs(int x, int y) {
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(x,y));
        map[y][x] = 0;
        while(!queue.isEmpty()){
            Point p = queue.poll();

            for(int d=0; d<4; d++) {
                int newX = p.x + moveX[d];
                int newY = p.y + moveY[d];

                //범위안에 속하면서 다음 좌표가 갱신이 아직 안되어있거나 더 크다면 => 더 작은값으로 갱신할수 있다면
                if(1<=newX && newX<=N && 1<=newY && newY<=N && map[newY][newX] > map[p.y][p.x]) {
                    if(arr[newY][newX] == 1) {
                        queue.add(new Point(newX, newY));
                        map[newY][newX] = map[p.y][p.x];
                    }else {
                        queue.add(new Point(newX, newY));
                        map[newY][newX] = map[p.y][p.x] + 1;
                    }
                }
            }
        }
    }


}

