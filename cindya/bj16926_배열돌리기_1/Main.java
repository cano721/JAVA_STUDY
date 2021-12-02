package cindya.bj16926_배열돌리기_1;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static int[] colMove = {1, 0, -1, 0}, rowMove = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken(" ")), m = Integer.parseInt(st.nextToken(" ")), r = Integer.parseInt(st.nextToken());
        int[][] arr = new int[n][];

        for(int i = 0; i < n; i++)
            arr[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        for(int i = 0; i < r; i++){ // 회전 횟수만큼 루프
            for(int j = 0; j < Math.min(n, m) / 2; j++){ // 깊이만큼 루프 (개별로 도는 배열의 개수)
                int first = arr[j][j]; // 첫번째 값
                int x = j, y = j, move = 0;
                while (move < 4){ // 4면을 다 돌 때까지
                    // 옮길 값이 있는 다음 인덱스
                    int nx = x + rowMove[move];
                    int ny = y + colMove[move];

                    if(j <= nx && nx < n - j && j <= ny && ny < m - j){ // 다음 칸의 인덱스가 범위 내에 있으면
                        arr[x][y] = arr[nx][ny]; // 값 이동
                        // 다음 칸으로 이동
                        x = nx;
                        y = ny;
                    }
                    else{ // 범위 밖에 있으면
                        move++; // 방향 전환
                    }
                }
                arr[j + 1][j] = first; // 마지막 칸에 첫번째 값을 삽입
            }
        }

        // 출력
        for(int[] a : arr){
            for(int i : a)
                bw.write(i + " ");
            bw.write("\n");
        }
        br.close();
        bw.close();
    }
}
