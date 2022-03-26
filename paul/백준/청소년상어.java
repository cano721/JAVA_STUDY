import java.io.*;
import java.util.*;

public class Main {

    static int[] dy = { -1, -1, 0, 1, 1, 1, 0, -1 };
    static int[] dx = { 0, -1, -1, -1, 0, 1, 1, 1 };

    static class Fish {
        int d, y, x;
        boolean alive;

        public Fish() {
            d = y = x = -1;
            alive = true;
        }
    };

    static class Shark {
        int d, y, x;

        public Shark(int dy, int dx, int dd) {
            y = dy;
            x = dx;
            d = dd;
        }
    };

    // 상어는 -1, -2 빈공간,
    static int[][] board = new int[4][4];

    static Fish[] fishes = new Fish[16]; //번호별 물고기의 위치. 물고기 잡아 먹히면 first == -1
    static Shark shark = new Shark(0,0,0);

    static int n;
    static int ans;
    static boolean valid(int y, int x) {
        if (y < 0 || y >= 4 || x < 0 || x >= 4) return false;
        return true;
    }
    
    static void movefish() {
    
        for (int i = 0; i < 16; i++) {
            if (fishes[i].alive == false) continue;
    
            for (int j = 0; j < 8; j++) {
                int d = (fishes[i].d + j) % 8;
                int cy = fishes[i].y + dy[d], cx = fishes[i].x + dx[d];
                if (!valid(cy, cx) || board[cy][cx] == -1) continue;
                
    
                //물고기가 있는 곳이면
                if (board[cy][cx] > 0) {
                    int idx = board[cy][cx]; //이미 있는 물고기 번호 받아와서
                    fishes[idx].y = fishes[i].y; //해당 번호의 위치 옮겨주기;
                    fishes[idx].x = fishes[i].x;
                    board[fishes[i].y][fishes[i].x] = idx;
                }
                else {
                    board[fishes[i].y][fishes[i].x] = -2;
                }
                fishes[i].y = cy;
                fishes[i].x = cx;
                board[cy][cx] = i;
                fishes[i].d = d;
                break;
            }
    
        }
    
    }
    
    
    static void saveState(int[][] cboard, int[][] board, Fish[] cf, Fish[] f) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                cboard[i][j] = board[i][j];
            }
        }
    
        for (int j = 0; j < 16; j++) {
            cf[j] = f[j];
        }
    }
    
    static void moveShark(int ate) {
        //상어 이동
        ans = Math.max(ans, ate);
        int[][] cboard = new int[4][4];
        Fish[] cfishes = new Fish[16];
        saveState(cboard, board, cfishes, fishes);
        movefish();
        Shark temp = shark;
        
        for (int i = 1; i <= 3; i++) {
            int cy = shark.y + i*dy[shark.d], cx = shark.x + i*dx[shark.d];
            if (!valid(cy, cx) || board[cy][cx] == -2) continue;
    
            int idx = board[cy][cx];
    
            int value = board[cy][cx] + 1;
    
            //board 내용 변경
            board[shark.y][shark.x] = -2;
            board[cy][cx] = -1;
    
            //상어 변경
            shark.y = cy;
            shark.x = cx;
            shark.d = fishes[idx].d;
            
            //잡아먹힌 물고기죽이고
            fishes[idx].alive = false;
    
            
    
            moveShark(ate +value);
    
            //복원
            shark = temp;
            fishes[idx].alive = true;
            board[shark.y][shark.x] = -1;
            board[cy][cx] = value-1;
            
        }
        saveState(board, cboard, fishes, cfishes);
    }
    
    static void pro() {
        moveShark(n);
        System.out.println(ans);
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                board[i][j] = a - 1;
                fishes[a - 1].alive = true;
                fishes[a - 1].d = b - 1;
                fishes[a - 1].y = i;
                fishes[a - 1].x = j;
            }
        }
        
        fishes[board[0][0]].alive = false;
        shark.d = fishes[board[0][0]].d;
        n = board[0][0]+1;
        board[0][0] = -1;
        
    }

    public static void main(String[] args) throws IOException {
        input();
	    pro();
    }

}