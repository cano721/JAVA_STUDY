package day220407;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_15686_치킨배달 {

    static class House {
        int r, c;

        public House(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public String toString() {
            return "House{" +
                    "r=" + r +
                    ", c=" + c +
                    '}';
        }
    }

    static int N, M, chickenCnt, answer = Integer.MAX_VALUE;
    static boolean visited[];
    static List<House> houseList = new ArrayList<>();
    static List<House> chickenHouseList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int n = Integer.parseInt(st.nextToken());
                if (n == 1) {
                    houseList.add(new House(i, j));
                } else if (n == 2) {
                    chickenHouseList.add(new House(i, j));
                }
            }
        }
        chickenCnt = chickenHouseList.size();
        visited = new boolean[chickenCnt+1];

        combination(0, 0);
        System.out.println(answer);
    }

    private static void combination(int idx, int cnt) {
        if (idx == chickenCnt+1) {
            return;
        }

        if (cnt == M) {
            //고른 치킨집 조합을 비교
            List<House> nowChickenHouseList = new ArrayList<>();
            for (int i = 0; i < chickenCnt; i++) {
                if (!visited[i]) continue;
                nowChickenHouseList.add(chickenHouseList.get(i));
            }
            int sum = 0;
            for (House house : houseList) {
                int min = Integer.MAX_VALUE;
                for (House chickenHouse : nowChickenHouseList) {
                    int dist = Math.abs(house.r - chickenHouse.r) + Math.abs(house.c - chickenHouse.c);
                    min = Math.min(dist, min);
                }
                sum += min;
            }
            answer = Math.min(sum, answer);
            return;
        }

        visited[idx] = true;
        combination(idx+1, cnt+1);
        visited[idx] = false;
        combination(idx+1, cnt);
    }
}
