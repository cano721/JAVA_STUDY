import java.util.LinkedList;
import java.util.Queue;

class Solution {

    static boolean[][] visited;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};

    public int solution(int[][] maps) {

        int answer = Integer.MAX_VALUE;

        Queue<Point> q = new LinkedList<>();

        q.add(new Point(maps.length-1, maps[0].length-1, 0));
        visited = new boolean[maps.length][maps[0].length];
        visited[maps.length-1][maps[0].length-1] = true;

        while (!q.isEmpty()) {

            Point p = q.poll();
            //System.out.println(p.x);
            if (p.x == 0 && p.y == 0) {
                answer = Math.min(answer, p.cnt);
                //return p.cnt+1;
            }
            for (int i = 0; i < 4; i++) {

                int tempX = p.x + dx[i];
                int tempY = p.y + dy[i];
                int tempcnt = p.cnt + 1;

                if (tempX < 0 || tempY < 0 || tempX >= maps.length || tempY >= maps[0].length || visited[tempX][tempY] || maps[tempX][tempY] != 1)
                    continue;

                q.add(new Point(tempX, tempY, tempcnt));
                visited[tempX][tempY] = true;
            }
        }
        if (answer == Integer.MAX_VALUE) {
            answer = -1;
        }else{
            answer = answer+1;
        }

        return answer;
    }

    static class Point {

        int x;
        int y;
        int cnt;

        Point(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }

    }
}