package cindya.bj16234_인구이동;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static final int[] moveRow = {-1, 1, 0, 0}, moveCol = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken(" ")), l = Integer.parseInt(st.nextToken(" ")), r = Integer.parseInt(st.nextToken());
        int[][] a = new int[n][];
        Boolean[][] visit = new Boolean[n][n];
        int cnt = 0;

        for(int i = 0; i < n; i++)
            a[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        while (true) {
            boolean hasMove = false; // 인구 이동이 있었는지 확인하는 플래그
            // 방문 체크 배열 초기화
            for(int i = 0; i < n; i++){
                Arrays.fill(visit[i], false);
            }
            // a 전체 탐색
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (!visit[i][j]) { // 방문한 적 없는 나라이면
                        visit[i][j] = true; // 방문 표시
                        Queue<int[]> q = new LinkedList<>();
                        List<int[]> list = new ArrayList<>();
                        q.offer(new int[]{i, j}); // q에 좌표를 삽입
                        int sum = a[i][j], cityCnt = 1;

                        while (!q.isEmpty()){ // 더이상 조건을 만족하는 인접한 나라가 없을 때까지
                            int[] xy = q.poll();
                            // 좌표의 사방 탐색
                            for(int t = 0; t < 4; t++){
                                int row = xy[0] + moveRow[t], col = xy[1] + moveCol[t];
                                if(0 <= row && row < n && 0 <= col && col < n && !visit[row][col] // 좌표가 범위 내이고, 방문한 적 없는 나라이며
                                && l <= Math.abs(a[xy[0]][xy[1]] - a[row][col]) && Math.abs(a[xy[0]][xy[1]] - a[row][col]) <= r){ // 조건을 만족하면
                                    visit[row][col] = true; // 방문 표시
                                    q.offer(new int[]{row, col}); // q에 좌표를 삽입
                                    sum += a[row][col]; // 인구 수를 합침
                                    cityCnt++; // 나라 수 증가
                                }
                            }
                            list.add(xy); // 좌표 저장
                        }
                        if(list.size() > 1) hasMove = true; // 조건을 만족하는 인접한 나라가 있다면 이동이 있음을 표시
                        for(int[] xy : list){ // 연합된 나라들의 인구수를
                            a[xy[0]][xy[1]] = sum / cityCnt; // 연합의 평균으로 변경
                        }
                    }
                }
            }
            if(!hasMove) break; // 인구 이동이 없었으면 중지
            cnt++; // 있었다면 카운트 증가
        }
        System.out.println(cnt);
        br.close();
    }
}
