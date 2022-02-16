import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

// bfs + 조합

public class PG_카드짝맞추기 {
	static int[][] board = { { 1, 0, 0, 3 }, { 2, 0, 0, 0 }, { 0, 0, 0, 2 }, { 3, 0, 1, 0 } };
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
    static ArrayList<int[]> orders;
	public static void main(String[] args) {
		int r = 1, c = 0;

		int n = 0;
		for (int row[] : board) {
			for (int k : row) {
				if (k != 0)
					n++; // 개수 구하기
			}
		}
        n /= 2;//짝 개수
        int[] temp = new int[n];
        for (int i = 0; i < n; i++) {
            temp[i] = i + 1;
        }
        orders = new ArrayList<>();
        permutation(n, n, new int[n], temp, 0, 0);

        int answer = 0;
		for (int[] order : orders) {
            int total = 0;
            int[] point = new int[2];
            point[0] = r;
            point[1] = c;
            int[][] cBoard = new int[4][4];
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    cBoard[i][j] = board[i][j];
                }
            }
            for (int card : order) {
                int cnt = 0;
                //목표 카드 찾기
                cnt += bfs(cBoard, card, point) + 1;
                //목표 카드 선택
                cBoard[point[0]][point[1]] = 0;
                System.out.println(point[0] + "," + point[1]);
                //짝 찾기
                cnt += bfs(cBoard, card, point) + 1;
                //짝 찾기 성공
                cBoard[point[0]][point[1]] = 0;
                System.out.println(point[0] + "," + point[1]);

                total += cnt;
            }
            System.out.println("total : " +total);
            System.out.println("---");
            answer = Math.min(total, answer);
        }

        System.out.println(answer);
	}

	static int bfs(int[][] board, int target, int[] point) {
		int r = point[0];
		int c = point[1];
		Queue<int[]> que = new LinkedList<>();
		boolean[][] visited = new boolean[4][4];

		que.offer(new int[] { r, c, 0 });
		visited[r][c] = true;

		while (!que.isEmpty()) {
			int[] p = que.poll();
			if (board[p[0]][p[1]] == target) {
				point[0] = p[0];
				point[1] = p[1];
				return p[2];
			}
			// 사방 탐색
			for (int d = 0; d < 4; d++) {
				int nr = p[0] + dx[d];
				int nc = p[1] + dy[d];
				if (nr >= 0 && nr < 4 && nc >= 0 && nc < 4 && !visited[nr][nc]) {
					visited[nr][nc] = true;
					que.offer(new int[] { nr, nc, p[2] + 1 });
				}
			}

			// ctrl + 사방 탐색
			for (int d = 0; d < 4; d++) {
				int[] result = findCard(board, p[0], p[1], d);
				if ((result[0] != p[0] || result[1] != p[1]) && !visited[result[0]][result[1]]) {
					visited[result[0]][result[1]] = true;
					que.offer(new int[] { result[0], result[1], p[2] + 1 });
				}
			}
		}
		return 0;
	}

    static int[] findCard(int[][] board, int r, int c, int d) {
        r += dx[d];
        c += dy[d];
        while (r >= 0 && r < 4 && c >= 0 && c < 4) {
            if (board[r][c] != 0) {
                return new int[]{r, c};
            }
            r += dx[d];
            c += dy[d];
        }
        return new int[]{r - dx[d], c - dy[d]};
    }
    static void permutation(int n, int r, int[] perms, int[] input, int depth, int flag) {
        if (depth == r) {
            int[] temp = new int[n];
            System.arraycopy(perms, 0, temp, 0, n);
            orders.add(temp);
            return;
        }
        
        for (int i = 0; i < n; i++) {
            if ((flag & (1 << i)) == 0) {
                perms[depth] = input[i];
                permutation(n, r, perms, input, depth + 1, flag | (1 << i));
            }
        }
    }
}
