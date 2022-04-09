package day220409;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_17143_낚시왕 {

    static class Shark {
        int num;
        int r, c, s, d, z;

        public Shark(int r, int c, int s, int d, int z) {
            this.r = r;
            this.c = c;
            this.s = s;
            this.d = d;
            this.z = z;
        }
    }

    static int R, C, M, map[][];
    static int dr[] = {0, -1, 1, 0, 0};
    static int dc[] = {0, 0, 0, 1, -1};
    static Shark[] sharks;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[R][C];
        sharks = new Shark[M+1];
        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken())-1;
            int c = Integer.parseInt(st.nextToken())-1;
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            if (d == 1 || d == 2) {
                sharks[i] = new Shark(r, c, s%(2*R-2), d, z);
                sharks[i].num = i;
            } else {
                sharks[i] = new Shark(r, c, s%(2*C-2), d, z);
                sharks[i].num = i;
            }
            map[r][c] = i;
        }
        int answer = 0;
        for (int i = 0; i < C; i++) {
            //i열에서 가장 가까운 상어를 잡는다
            for (int j = 0; j < R; j++) {
                if (map[j][i] > 0) {
                    answer += sharks[map[j][i]].z;
                    sharks[map[j][i]] = null;
                    map[j][i] = 0;
                    break;
                }
            }

            //상어가 이동한다.
            ArrayList<Shark> sharkList = new ArrayList<>();
            for (int j = 1; j <= M; j++) {
                if (sharks[j] == null) continue;
                // 방향이 아래위면 2*r - 2 만큼 나눈 나머지, 양옆이면 2*c-2만큼 나눈 나머지만 이동하면 된다. 생성자로 초기값을 바꿨다.
                //sharks[j].s 만큼 이동시작, 이동한 위치는 리스트에 저장했다가 동시에 이동시킨다.
                map[sharks[j].r][sharks[j].c] = 0;
                for (int s = 0; s < sharks[j].s; s++) {
                    int nr = sharks[j].r + dr[sharks[j].d];
                    int nc = sharks[j].c + dc[sharks[j].d];
                    if (nr < 0 || nc < 0 || nr >= R || nc >= C) {
                        if (sharks[j].d == 1) sharks[j].d = 2;
                        else if (sharks[j].d == 2) sharks[j].d = 1;
                        else if (sharks[j].d == 3) sharks[j].d = 4;
                        else if (sharks[j].d == 4) sharks[j].d = 3;
                        nr = sharks[j].r + dr[sharks[j].d];
                        nc = sharks[j].c + dc[sharks[j].d];
                    }
                    sharks[j].r = nr;
                    sharks[j].c = nc;
                }
                sharkList.add(sharks[j]);
            }
            for (Shark shark : sharkList) {
                if (map[shark.r][shark.c] > 0) { //이미 다른 상어가 있다는 뜻이므로 잡아 먹을지 먹힐지 봐야함
                    if (shark.z < sharks[map[shark.r][shark.c]].z) sharks[shark.num] = null;
                    else {
                        sharks[map[shark.r][shark.c]] = null;
                        map[shark.r][shark.c] = shark.num;
                    }
                } else {
                    map[shark.r][shark.c] = shark.num;
                }
            }
        }
        System.out.println(answer);
    }

    private static void print() {
        for (int j = 0; j < R; j++) {
            for (int k = 0; k < C; k++) {
                System.out.print(map[j][k] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }


}
