package cindya.bj14499_주사위굴리기;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static final int VERTICAL = 0, HORIZONTAL = 1;
    private static final int[] UPDOWN = {0, 0, -1, 1}, LEFTRIGHT = {1, -1, 0, 0};
//    private static final String[] directions = {"→", "←", "↑", "↓"};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken(" ")), m = Integer.parseInt(st.nextToken(" "));
        int x = Integer.parseInt(st.nextToken(" ")), y = Integer.parseInt(st.nextToken(" "));
        int k = Integer.parseInt(st.nextToken()), bottom = 0;
        int[][] map = new int[n][], dice = {{0, 0, 0}, {0, 0, 0}};
        int[] moves;

        for(int i = 0; i < n; i++)
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        moves = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).map(i -> i - 1).toArray();
        br.close();

        for(int move : moves){
            x += UPDOWN[move];
            y += LEFTRIGHT[move];
            if(0 <= x && x < n && 0 <= y && y < m){ // 움직인 좌표가 지도 안이면
                int i = 1, j, tmp, moveD = -1, term = 0;
                // 움직일 방향으로 변수 세팅
                switch (move){
                    case 0: case 1:
                        moveD = HORIZONTAL;
                        term = LEFTRIGHT[(move + 1) % 2];
                        break;
                    case 2: case 3:
                        moveD = VERTICAL;
                        term = UPDOWN[((move + 1) % 2) + 2];
                }
                j = i + UPDOWN[move] + LEFTRIGHT[move]; // i번째 부분의 값이 들어갈 위치
                tmp = dice[moveD][j]; // bottom에 들어갈 값
                // 값 옮기기
                for(; 0 <= i && i < dice[moveD].length; j = i, i += term)
                    dice[moveD][j] = dice[moveD][i];
                dice[moveD][j] = bottom; // 마지막 위치에 bottom 값 넣기
                bottom = tmp;
                dice[(moveD + 1) % 2][1] = dice[moveD][1]; // 가로, 세로의 가운데 값이 같도록 변경

                if(map[x][y] == 0) map[x][y] = bottom; // 움직인 위치의 값이 0이면 주사위의 bottom 값을 복사
                else { // 0이 아니면
                    bottom = map[x][y]; // 바닥의 값을 bottom으로 옮기고
                    map[x][y] = 0; // 그 위치를 0으로 변경
                }
                // 중간 확인 용
//                System.out.println("direction : " + directions[move]);
//                System.out.println(String.format("%4d", dice[VERTICAL][0]));
//                System.out.println(String.format("%2d%2d%2d (B: %d)", dice[HORIZONTAL][0], dice[HORIZONTAL][1], dice[HORIZONTAL][2], bottom));
//                System.out.println(String.format("%4d", dice[VERTICAL][2]) + "\n");
//
//                for(int[] line : map) {
//                    for (int s : line)
//                        System.out.print(s + " ");
//                    System.out.println();
//                }
//                System.out.println();
                bw.write(dice[VERTICAL][1] + "\n"); // 윗면 값 출력
            }
            else{ // 움직인 좌표가 지도 밖이면 원복
                x -= UPDOWN[move];
                y -= LEFTRIGHT[move];
            }
        }

        bw.close();
    }
}
