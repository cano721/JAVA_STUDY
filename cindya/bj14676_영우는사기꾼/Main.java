package cindya.bj14676_영우는사기꾼;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 건물 클래스
class Building{
    int number, cnt;
    List<Integer> next = new ArrayList<>();

    public Building(int number) {
        this.number = number;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken()), k = Integer.parseInt(st.nextToken());
        Building[] buildings = new Building[n + 1];
        int[] numberOfBuilding = new int[n + 1];
        boolean isLier = false;

        for (int i = 0; i <= n; i++)
            buildings[i] = new Building(i);

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()), y = Integer.parseInt(st.nextToken());
            buildings[x].next.add(y);
            buildings[y].cnt++;
        }

        for(int i = 0; i < k; i++){
            st = new StringTokenizer(br.readLine());
            if(isLier) continue; // 영우가 사기꾼일 경우 입력만 받고 스킵
            int op = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken());
            switch (op){
                case 1 : // 건설할 때
                    // b번 건물의 cnt가 0보다 크다면 사기꾼
                    if(buildings[b].cnt > 0){
                        isLier = true;
                        continue;
                    }
                    // cnt가 0이라면 건물 수 증가
                    numberOfBuilding[b]++;
                    //다음 건물 cnt 감소
                    for(int nxt : buildings[b].next)
                        buildings[nxt].cnt--;
                    break;
                case 2 : // 파괴할 때
                    // 건물이 없으면 사기꾼
                    if(numberOfBuilding[b] == 0)
                        isLier = true;
                    else { // 있으면 건물 수를 감소
                        numberOfBuilding[b]--;
                        // 감소해서 없어졌다면
                        if(numberOfBuilding[b] == 0){
                            // 다음 건물들 cnt 증가
                            for(int nxt : buildings[b].next)
                                buildings[nxt].cnt++;
                        }
                    }
                    break;
            }
        }
        if(isLier) System.out.println("Lier!");
        else System.out.println("King-God-Emperor");
        br.close();
    }
}
