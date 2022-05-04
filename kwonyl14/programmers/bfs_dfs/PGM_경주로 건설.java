import java.util.*;

class Solution {
    static class Point {
        int r, c, cost, dir;
        
        public Point(int r, int c, int cost, int dir) {
            this.r = r;
            this.c = c;
            this.cost = cost;
            this.dir = dir;
        }
    }
    
    private final int INF = Integer.MAX_VALUE;
    private final int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};
    
    private int n;
    private int board[][], memo[][][];
    
    public int solution(int[][] board) {
        this.board = board;
        n = board.length;
        this.memo = new int[4][n][n];
        for (int d = 0; d < 4; d++) {
            for (int i = 0; i < n; i++) {
                Arrays.fill(memo[d][i], INF);
            }
        }
        
        int answer = bfs();
        return answer;
    }
    
    private int bfs() {
        PriorityQueue<Point> pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
        pq.offer(new Point(0, 0, -500, -1));
        
        while (!pq.isEmpty()) {
            Point p = pq.poll();
            
            if (p.r == n-1 && p.c == n-1) {
                return p.cost;
            }
            
            for (int i = 0; i < 4; i++) {
                int nr = p.r + dr[i];
                int nc = p.c + dc[i];
                
                if (nr < 0 || nc < 0 || nr >= n || nc >= n) continue;
                if (board[nr][nc] == 1) continue;
                int cost = p.cost + 100;
                if (p.dir != i) cost += 500;
                if (memo[i][nr][nc] > cost) {
                    memo[i][nr][nc] = cost;
                    pq.offer(new Point(nr, nc, cost, i));
                }
            }
        }
        System.out.println("노답");
        return -1;
    }
}