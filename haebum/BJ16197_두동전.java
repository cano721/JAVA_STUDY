/*
    맵의 범위 20*20

    bfs를 통해 4방향 이동 예정.

    동전은 큐에서 좌표이동으로 계속 표기됨.

    맵에서 한개 떨어졌을때 그 이동 숫자를 출력.

    Queue에 넣을것은 동전1의 좌표, 동전2의 좌표, 현재 버튼 클릭 수. 총 5개.

    visited는 4차원으로 만들어서 두동전의 위치를 저장.

    1. bfs를 통해 이동.

    2. 이동할때 체크할것은 맵안에 있는지

    3. 맵안에 있다면 동전 이동 및 횟수 증가

    4. 없다면 동전이 한개만 떨어진건지 체크

    5. 한개만 떨어졌다면 bfs는 너비우선탐색이기에 종료.
    
*/


import java.util.*;
import java.io.*;

public class BJ16197_두동전 {

    public static int n,m,answer;
    public static int[][] arr;
    public static int[][][][] visited;
    public static int[] dirX = {0,1,0,-1};
    public static int[] dirY = {1,0,-1,0};
    public static int[] startPoint = new int[5];

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        
        // 배열크기 생성
        arr = new int[n][m];
        visited = new int[n][m][n][m];

        int idx = 0;

        for(int i = 0; i < n; i++){
            String str = br.readLine();
            for(int j = 0; j < m; j++){
                arr[i][j] = str.charAt(j);
                if(str.charAt(j) == 'o'){
                    arr[i][j] = '.';
                    startPoint[idx++] = i;
                    startPoint[idx++] = j;
                }
            }
        }

        answer = -1;
        bfs();
        System.out.println(answer);
        
    }
    // bfs
    public static void bfs(){
        // 큐생성 및 시작 좌표 담기
        Queue<int[]> q = new LinkedList<>();

        // 시작 두 동전 위치 담기
        
        q.offer(startPoint);
        visited[startPoint[0]][startPoint[1]][startPoint[2]][startPoint[3]] = 1;

        while(!q.isEmpty()){
            int[] cur = q.poll();
            // 좌표와 버튼 클릭 수
            int x1 = cur[0];
            int y1 = cur[1];
            int x2 = cur[2];
            int y2 = cur[3];
            int cnt = cur[4];

            // 두동전의 위치가 같아졌으면 다음꺼 빼내기
            if(x1 == x2 && y1 == y2) continue;

            // 방향 돌기
            for(int num = 0; num < 4; num++){
                int newX1 = x1+dirX[num];
                int newY1 = y1+dirY[num];
                int newX2 = x2+dirX[num];
                int newY2 = y2+dirY[num];

                // 동전 떨어진 개수
                int drop = 0;

                // 큐에 담을 배열
                int[] temp = new int[5];

                // 둘다 벽에 부딪히면 큐에 안넣기위한 변수
                int check = 0;


                // 첫번째 동전이 맵 안에 있으면서
                if(0 <= newX1 && newX1 < n && 0 <= newY1 && newY1 < m){
                    // 빈칸시 이동
                    if(arr[newX1][newY1] == '.'){
                        // 좌표 담기
                        temp[0] = newX1;
                        temp[1] = newY1;
                    // 벽이면 이동 불가
                    }else if(arr[newX1][newY1] == '#'){
                        temp[0] = x1;
                        temp[1] = y1;
                        check++;
                    }
                // 맵에서 떨어졌으면 떨어진 동전개수 증가
                }else drop++;

                // 두번째 동전이 맵 안에 있으면서
                if(0 <= newX2 && newX2 < n && 0 <= newY2 && newY2 < m){
                    // 빈칸시 이동
                    if(arr[newX2][newY2] == '.'){
                        // 좌표 담기
                        temp[2] = newX2;
                        temp[3] = newY2;
                    // 벽이면 이동 불가
                    }else if(arr[newX2][newY2] == '#'){
                        temp[2] = x2;
                        temp[3] = y2;
                        check++;
                    }
                // 맵에서 떨어졌으면 떨어진 동전개수 증가
                }else drop++;

                // 한개만 떨어졌으면
                if(drop == 1){
                    // 정답 변경 후 종료
                    answer = cnt+1;
                    return;
                }else if(drop == 0){
                    // 10번 초과면 종료
                    if(cnt+1 > 10){
                        return;
                    }
                    // 10번 이하면서 둘다 벽을 만나지 않았을때 똑같은 좌표로 방문하지 않았을때 큐에 담아서 이동
                    if(check < 2){
                        if(visited[temp[0]][temp[1]][temp[2]][temp[3]] == 0){
                            visited[temp[0]][temp[1]][temp[2]][temp[3]] = 1;
                            temp[4] = cnt+1;
                            q.offer(temp);
                        }
                    }
                }
            }

        }
    }
}
