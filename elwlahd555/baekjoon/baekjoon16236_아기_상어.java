package elwlahd555.baekjoon;


import java.io.IOException;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

/**
 * https://www.acmicpc.net/problem/16236
 * 아기 상어가 물고기에 도달하는 지점까지가 한 번의 BFS. 물고기를 먹으면 그 지점에서 새로운 BFS 시작
 */
public class baekjoon16236_아기_상어 {
    // 사방탐색용 배열
    static int[][] dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    // 공간의 크기 N(2 ≤ N ≤ 20)
    static int N;

    static int[][] map;
    static boolean[][] visited;

    // 정답
    static int moveCnt;

    public static void main(String[] args) throws IOException {
    	Scanner sc = new Scanner(System.in);


        N = sc.nextInt(); // 공간의 크기 N(2 ≤ N ≤ 20)

        map = new int[N][N];

        Shark shark = null;
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                map[r][c] = sc.nextInt();
                // 상어가 있던 지점은 상어를 표시하고 상어는 여기를 다시 지나갈 수 있다.
                if (map[r][c] == 9) {
                    map[r][c] = 0;
                    // 처음 상어의 크기는 2
                    shark = new Shark(r, c, 2, 0, 0);
                }
            }
        }

        // 이 상어를 타고 탐색 시작
        bfs(shark);

        System.out.println(moveCnt);

    }

    // 상어가 범위을 확장해가며 물고기를 찾는다.
    static void bfs(Shark shark) {
        Queue<Shark> queue = new LinkedList<>();
        queue.add(shark);
        // 여기가 새로운 상어의 위치 --> 새로 bfs
        visited = new boolean[N][N];
        visited[shark.row][shark.col] = true;

        // 상어와 가장 가까운 depth에 있는 물고기들
        PriorityQueue<Fish> targetFishList = new PriorityQueue<>();

        Shark front = null;

        // 처음 희생양이 발견된 depth 다음 depth는 돌 필요가 없다.
        findFish: while (!queue.isEmpty()) {
            front = queue.poll();

            for (int d = 0; d < dir.length; d++) {
                int nr = front.row + dir[d][0];
                int nc = front.col + dir[d][1];

                if (isIn(nr, nc) && !visited[nr][nc]) {
                    visited[nr][nc] = true;
                    // 크기가 상어와 같거나 0이면 이동/통과 가능
                    if (map[nr][nc] == 0 || map[nr][nc] == front.size) {
                        queue.add(new Shark(nr, nc, front.size, front.depth + 1, front.eatCnt));
                    }
                    // 먹을 수 있는 녀석이 발견되었다면 해당 물고기를 담아놓자. - 먹이 후보
                    else if (map[nr][nc] < front.size) {
                    	// 처음일 경우 물고기를 입력
                    	if (targetFishList.isEmpty()) {
                            targetFishList.add(new Fish(nr, nc, map[nr][nc], front.depth + 1));
                    	}
                    	else {                    	
                    		Fish first = targetFishList.peek();
                    		if (first.depth < front.depth + 1) {  // 같은 거리가 아니라면...
                    			break findFish;
                    		}
                    		else {
                    			targetFishList.add(new Fish(nr, nc, map[nr][nc], front.depth + 1));
                    		}
                    	}
                    }
                }
            }
        }

        // 먹을 물고기가 없으면 종료
        if (targetFishList.isEmpty()) {
            return;
        }
        // 물고기가 있으면 먹으러 가자.
        else {
            // PriorityQueue이므로 맨 위의 물고기가 목표
            Fish fish = targetFishList.poll();
            // System.out.println("먹은놈: " + fish);
            front.eat();
            // 이제 그 지점에 물고기 없음
            map[fish.row][fish.col] = 0;
            // 이동 회수는 업데이트 해주고
            moveCnt += fish.depth;
            // 이 지점이 새로운 BFS 시점
            bfs(new Shark(fish.row, fish.col, front.size, 0, front.eatCnt));
        }
    }

    static boolean isIn(int r, int c) {
        return 0 <= r && 0 <= c && r < N && c < N;
    }

    static class Fish implements Comparable<Fish> {
        int row, col;
        int size; // 크기
        int depth;// 거리

        public Fish(int row, int col, int size, int depth) {
            super();
            this.row = row;
            this.col = col;
            this.size = size;
            this.depth = depth;
        }

        // 1순위 depth, 2순위 세로 위쪽, 3순위 가로 왼쪽
        @Override
        public int compareTo(Fish o) {
            if (depth == o.depth) {
                if (row == o.row) {
                    return Integer.compare(col, o.col);
                } else {
                    return Integer.compare(row, o.row);
                }
            } else {
                return Integer.compare(depth, o.depth);
            }
        }
    }

    static class Shark {
        int row, col;
        int size; // 상어의 크기
        int depth;// 이동 회수
        int eatCnt;// 먹은 회수

        public Shark(int row, int col, int size, int depth, int eatCnt) {
            super();
            this.row = row;
            this.col = col;
            this.size = size;
            this.depth = depth;
            this.eatCnt = eatCnt;
        }

        // 한번에 한 마리씩 먹고 몸집만큼 먹으면 크기 변경
        public void eat() {
            // System.out.print("변경 전: 먹은 수: "+eatCnt+", 크기: "+size);
            eatCnt++;
            if (eatCnt == size) {
            	size++;
                eatCnt = 0;
            }
        }
    }

    // END:

}