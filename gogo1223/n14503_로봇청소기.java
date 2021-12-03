import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class n14503_로봇청소기 {
	static int n, m, r, c, d;
	static int[][] arr;
	static int answer = 0;
    public static int[] dr = {-1, 0, 1, 0}; // 북,동,남,서
    public static int[] dc = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        
        st = new StringTokenizer(br.readLine());
        
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        
        arr = new int[n][m];
        for (int i = 0; i < n; i++) {
        	st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
        
        clean(r, c, d);
        System.out.println(answer);

	}
	private static void clean(int x, int y, int dir) {
        if (arr[x][y] == 0) {
            arr[x][y] = 2; //청소한 곳 표시
            answer++;
        }

        // 왼쪽방향부터 차례대로 탐색을 진행
        boolean flag = false;
        int origin = dir;
        for (int i = 0; i < 4; i++) {
            int next_d = (dir + 3) % 4; //왼쪽으로 돌기
            int next_r = x + dr[next_d];
            int next_c = y + dc[next_d];

            if (next_r > 0 && next_c > 0 && next_r < n && next_c < m) {
                // 아직 청소하지 않은 공간이라면
                if (arr[next_r][next_c] == 0) {
                    clean(next_r, next_c, next_d);
                    flag = true;	//청소 했으면 다음칸으로
                    break;
                }
            }
            dir = (dir + 3) % 4;
        }

        // 네 방향 모두 청소가 되어있거나 벽인 경우
        if (!flag) {
            int next_d = (origin + 2) % 4; //후진 방향
            int next_br = x + dr[next_d];
            int next_bc = y + dc[next_d];

            if (next_br > 0 && next_bc > 0 && next_br < n && next_bc < m) {
                if (arr[next_br][next_bc] != 1) {
                    // 바라보는 방향 유지한 채 후진
                    clean(next_br, next_bc, origin); //바라보는 방향 바꾸지 않기 origin으로
                }
            }
        }
		
	}

}
