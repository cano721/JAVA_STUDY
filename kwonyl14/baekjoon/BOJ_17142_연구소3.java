package day220416;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_17142_연구소3 {

    static int N, M, map[][], binCanCnt, virusCnt, answer = Integer.MAX_VALUE;
    static int dr[] = {-1, 1, 0, 0}, dc[] = {0, 0, -1, 1};
    static boolean visited[][];
    static List<Virus> virusList = new ArrayList<>();
    static Queue<Virus> virusQueue = new LinkedList<>();
    private static boolean[] checked;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 0) binCanCnt++;
                else if (map[i][j] == 2) {
                    virusList.add(new Virus(i, j));
                    virusCnt++;
                }
            }
        }

        //예외 처리
        //1. 바이러스로 꽉차서 빈칸이 없을 때
        if (virusCnt > 0 && binCanCnt == 0) {
            System.out.println(0);
            return;
        }

        checked = new boolean[virusCnt+1];

        comb(0, 0);//M개의 바이러스를 선택한다.

        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }

    private static void comb(int idx, int cnt) {
        if (idx == virusCnt + 1) {
            return;
        }

        if (cnt == M) {
            virusQueue.clear();
            visited = new boolean[N][N];
            for (int i = 0; i < virusCnt; i++) {
                if (checked[i]) {
                    virusQueue.offer(virusList.get(i));
                    visited[virusList.get(i).r][virusList.get(i).c] = true;
                }
            }

            spread();
            return;
        }

        checked[idx] = true;
        comb(idx + 1, cnt + 1);
        checked[idx] = false;
        comb(idx + 1, cnt);
    }

    //현재 뽑힌 바이러스들로 퍼지기를 시작
    private static void spread() {
        int roomCnt = 0;
        int time = 0;
        int[][] tmp = new int[N][N];
        while (!virusQueue.isEmpty()) {
            int size = virusQueue.size();
            time++;
            while (size --> 0) {
                Virus v = virusQueue.poll();

                for (int i = 0; i < 4; i++) {
                    int nr = v.r + dr[i];
                    int nc = v.c + dc[i];

                    if (nr < 0 || nc < 0 || nr >= N || nc >= N) continue;
                    if (visited[nr][nc]) continue;
                    if (map[nr][nc] == 1) continue;
                    //빈칸일 때만 카운트
                    if (map[nr][nc] == 0) {
                        roomCnt++;
                    }
                    visited[nr][nc] = true;
                    tmp[nr][nc] = time;
                    virusQueue.offer(new Virus(nr, nc));
                }
            }
            if (roomCnt == binCanCnt) break;
        }
        //모두 퍼뜨리고 나왔을 때 더이상 남은 칸이 없을 때만 정답 처리
        if (roomCnt == binCanCnt) {
            answer = Math.min(answer, time);
        }
    }

    private static class Virus {
        int r, c;

        public Virus(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public String toString() {
            return "Virus{" +
                    "r=" + r +
                    ", c=" + c +
                    '}';
        }
    }
}
