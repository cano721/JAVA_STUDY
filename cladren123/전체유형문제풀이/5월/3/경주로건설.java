package studyGroup.may.may3;

// bfs, dfs, dp....
// bfs

// 1차 제출
// 96점 테스트25

// 2차 제출 100점
// 이동의 방문여부를 판단하는 3차원 배열로 루트간 겹치지 않게 처리한다.
// 정답 처리를 DP 마지막 지점을 찍는게 아니라 실시간으로 업데이트 하도록 바꾸었다.



import java.util.*;

class 경주로건설 {

    static int[][] moneyboard;
    static int n;
    static int m;

    static int[] dx = {1,0,-1,0};
    static int[] dy = {0,-1,0,1};
    static boolean[][][] visited;

    static int answer;


    public int solution(int[][] board) {
        answer = Integer.MAX_VALUE;

        n = board.length; // 세로
        m = board[0].length;
        visited = new boolean[n][m][4];

        Queue<Dot> que = new LinkedList();
        que.add(new Dot(0,0,0,0));

        while(!que.isEmpty())
        {
            Dot d = que.poll();

            if(d.y == n-1 && d.x == m-1)
            {
                answer = Math.min(answer, d.cost);
                continue;
            }

            for(int i = 0; i < 4; i++)
            {
                int ny = d.y + dy[i];
                int nx = d.x + dx[i];

                int ndirect = 0; // 1이면 가로, -1이면 세로 방향
                if(i % 2 == 0)
                    ndirect = 1;
                else
                    ndirect = -1;

                // 범위 안에 있는지 확인
                if(ny >= 0 && ny < n && nx >= 0 && nx < m)
                {
                    // 벽이 아니면 탐색 시작
                    if(board[ny][nx] != 1)
                    {
                        // 비용 계산
                        int ncost = d.cost;
                        ncost += 100;
                        if(d.direct != 0 && d.direct != ndirect)
                            ncost += 500;
                        // 비용 계산

                        if(board[ny][nx] == 0)
                        {
                            board[ny][nx] = ncost;
                            que.add(new Dot(ny,nx,ncost,ndirect));
                        }
                        else if(board[ny][nx] != 0)
                        {
                            if(ncost <= board[ny][nx] || !visited[ny][nx][i])
                            {
                                board[ny][nx] = ncost;
                                visited[ny][nx][i] = true;
                                que.add(new Dot(ny,nx,ncost,ndirect));
                            }
                        }
                    }
                }
            }
        }

        for(int i = 0; i < n; i++)
            System.out.println(Arrays.toString(board[i]));


        return answer;
    }

    public class Dot
    {
        int y;
        int x;
        int cost;
        int direct;

        public Dot(int y, int x, int cost, int direct)
        {
            this.y = y;
            this.x = x;
            this.cost = cost;
            this.direct = direct;
        }



    }


}
