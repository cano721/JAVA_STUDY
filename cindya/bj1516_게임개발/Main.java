package cindya.bj1516_게임개발;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

// 건물 클래스
class Building{
    int number, time = 0, cnt = 0;
    List<Building> next = new ArrayList<>();

    public Building(int number) {
        this.number = number;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        Building[] buildings = new Building[n + 1];
        Queue<Building> q;
        int[] total = new int[n + 1];

        for(int i = 0; i <= n; i++)
            buildings[i] = new Building(i);

        for(int i = 1; i <= n; i++){
            StringTokenizer st =  new StringTokenizer(br.readLine());
            buildings[i].time = Integer.parseInt(st.nextToken());
            int nb;
            while ((nb = Integer.parseInt(st.nextToken())) != -1) {
                buildings[nb].next.add(buildings[i]);
                buildings[i].cnt++;
            }
        }

        // cnt가 0인 값만 q에 저장
        q = Arrays.stream(buildings).filter(b -> b.cnt == 0).collect(Collectors.toCollection(LinkedList::new));
        while (!q.isEmpty()){
            Building building = q.poll();
            total[building.number] += building.time; // total에 time 값 더하기
            for(Building nb : building.next){
                // 다음 건물이 건설되기 이전 건설 되는 건물의 건설 시간 중 가장 긴 것이 현재 건물의 건설 시간보다 짧다면 값 변경
                total[nb.number] = Math.max(total[nb.number], total[building.number]);
                // cnt를 감소하고 그 값이 0이라면 q에 삽입
                if(--nb.cnt == 0)
                    q.offer(nb);
            }
        }

        // 답 출력
        for(int i = 1; i <= n; i++)
            bw.write(total[i] + "\n");
        br.close();
        bw.close();
    }
}
