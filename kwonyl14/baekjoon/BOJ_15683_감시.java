import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static class CCTV {
        int r, c, num;

        public CCTV(int r, int c, int num) {
            this.r = r;
            this.c = c;
            this.num = num;
        }
    }

    static int N, M, map[][], CCTVCnt, CCTVcomb[] = {0, 0, 0, 0, 0, 0};
    static int answer = Integer.MAX_VALUE;
    static int[][][] CCTVr = {{{}},
            {{-1}, {0}, {1}, {0}},
            {{0, 0}, {-1, 1}},
            {{-1, 0}, {0, 1}, {1, 0}, {0, -1}},
            {{0, -1, 0}, {-1, 0, 1}, {0, 1, 0}, {1, 0, -1}},
            {{0, 1, 0, -1}}
    };
    static int[][][] CCTVc = {{{}},
            {{0}, {1}, {0}, {-1}},
            {{1, -1}, {0, 0}},
            {{0, 1}, {1, 0}, {0, -1}, {-1, 0}},
            {{-1, 0, 1}, {0, 1, 0}, {-1, 0, 1}, {0, -1, 0}},
            {{-1, 0, 1, 0}}
    };

    static ArrayList<CCTV> CCTVList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        CCTVList = new ArrayList();
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int num = Integer.parseInt(st.nextToken());
                map[i][j] = num;
                if (num == 0 || num == 6) continue;
                else {
                    CCTVList.add(new CCTV(i, j, num));
                }
            }
        }
        CCTVCnt = CCTVList.size();
        CCTVcomb = new int[CCTVCnt]; //1번부터 CCTVNum번까지 어디방향으로 틀건지를 담는 배열

        dfs(0);
        System.out.println(answer);
    }

    private static void dfs(int cnt) {
        if (cnt == CCTVCnt) {
            int sum = turnOnCCTV();
            answer = Math.min(answer, sum);
            return;
        }

        int nowCCTVNum = CCTVList.get(cnt).num;
        for (int i = 0; i < CCTVr[nowCCTVNum].length; i++) {
            CCTVcomb[cnt] = i;
            dfs(cnt+1);
        }

    }

    private static int turnOnCCTV() {
        for (int i = 0; i < CCTVCnt; i++) {
            CCTV cctv = CCTVList.get(i);
            // CCTVcomb[i] 방향으로 해당 씨씨티비를 켠다.
            int nowD = CCTVcomb[i];
            //1번 cctv는 4방향 돌리게 되고 한 방향당 1번밖에 비출곳이 없다.
            //하지만 3번 cctv는 4방향을 돌리게 되면서 두방향씩 비춰야한다.
            for (int d = 0; d < CCTVr[cctv.num][nowD].length; d++) {
                int nr = cctv.r + CCTVr[cctv.num][nowD][d];
                int nc = cctv.c + CCTVc[cctv.num][nowD][d];

                while (nr >= 0 && nc >= 0 && nr < N && nc < M) {
                    if (map[nr][nc] == 6) break;
                    if (map[nr][nc] == 0) map[nr][nc] = 7;
                    nr += CCTVr[cctv.num][nowD][d];
                    nc += CCTVc[cctv.num][nowD][d];
                }
            }
            print();
        }
        // 모든 cctv감시가 끝났으므로 사각지대를 계산해주고 리턴
        // 7(감시지대)로 만든건 다른 조건으로 재탐색을 위해 되돌려준다.
        int sum = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) sum++;
                else if (map[i][j] == 7) map[i][j] = 0;
            }
        }
        return sum;
    }

    private static void print() {
//        for (int i = 0; i < N; i++) {
//            for (int j = 0; j < M; j++) {
//                System.out.print(map[i][j] + " ");
//            }
//            System.out.println();
//        }
//        System.out.println();
    }
}
