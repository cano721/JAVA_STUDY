import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj10026_적록색약 {
    static int N;
    static int[] dx = {0, -1, 0, 1}; // 상하좌우 x
    static int[] dy = {-1, 0, 1, 0};
    static char map[][];
    static boolean visited[][];
    static int xRGB=0, RGB=0; // 적록색약 o, x

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new char[N][N];
        visited = new boolean[N][N];

        for(int i=0; i<N; i++) {
            String str = br.readLine();
            for(int j=0; j<N; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        // 적록색약이 아닌 사람이 봤을때의 구역 갯수
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(!visited[i][j]) {
                    dfs(i, j);
                    xRGB ++;
                }
            }
        }

        // 적록색약인 사람의 경우
        // → 빨간색과 초록색의 차이를 느끼지 못하므로 동일하게 취급
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(map[i][j] == 'G')
                    map[i][j] = 'R';
            }
        }

        // 적록색약인 사람이 봤을 때의 구역 갯수
        visited = new boolean[N][N]; // 방문표시 초기화
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(!visited[i][j]) {
                    dfs(i, j);
                    RGB ++;
                }
            }
        }

        System.out.println(xRGB + " " + RGB);
    }

    public static void dfs(int x, int y) {
        visited[x][y] = true;
        char color = map[x][y];

        for(int i=0; i<4; i++) { // 상하좌우 뱡항 체크
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx>=0 && ny>=0 && nx<N && ny<N) {
                if(map[nx][ny] == color && !visited[nx][ny]) {
                    dfs(nx, ny);
                }
            }
        }
    }
}
