package cindya.bj14889_스타트와링크;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Synergy{
    private int[] p = new int[2]; // 사람 두 명
    private int s; // 능력치

    public Synergy(int p1, int p2, int s){
        p[0] = p1;
        p[1] = p2;
        this.s = s;
    }

    public int[] getP() {
        return p;
    }

    public int getS() {
        return s;
    }
}

public class Main {
    private static List<Synergy> synergies = new ArrayList<>();
    private static boolean[] team;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] s = new int[n][n];
        int min;

        team = new boolean[n];
        Arrays.fill(team, false);
        team[0] = true;

        // 능력치 배열 입력받기
        for(int i = 0; i < n; i++){
            s[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        // 능력치 합 저장
        for(int i = 0; i < n - 1; i++){
            for(int j = i + 1; j < n; j++){
                synergies.add(new Synergy(i, j, s[i][j] + s[j][i]));
            }
        }

        min = getMinGap((n / 2) - 1, 1, n);
        System.out.println(min);

        br.close();
    }

    private static int getMinGap(int cnt, int start, int n){
        // 팀을 다 뽑았을 때
        if(cnt == 0){
            List<Integer> t1 = new ArrayList<>(), t2 = new ArrayList<>();
            int t1s = 0, t2s = 0;

            for(int i = 0; i < n; i++){ // 팀 나눠서 저장
                if(team[i]) t1.add(i);
                else t2.add(i);
            }

            for(Synergy synergy : synergies){
                // 능력치에 해당하는 사람이 모두 한 팀에 속했을 경우, 그 팀에 능력치 더함
                if(t1.contains(synergy.getP()[0]) && t1.contains(synergy.getP()[1])) t1s += synergy.getS();
                else if(t2.contains(synergy.getP()[0]) && t2.contains(synergy.getP()[1])) t2s += synergy.getS();
            }

            // 두 팀의 차 반환
            return Math.abs(t1s - t2s);
        }

        // 팀 뽑을 때
        int min = Integer.MAX_VALUE;
        for(int i = start; i < n; i++){
            team[i] = true;
            min = Math.min(min, getMinGap(cnt - 1, i + 1, n)); // 더 작은 값 선택
            team[i] = false;
        }
        return min;
    }
}
