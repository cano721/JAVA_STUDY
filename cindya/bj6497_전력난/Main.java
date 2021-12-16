package cindya.bj6497_전력난;

import java.io.*;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 도로 클래스
class Road implements Comparable<Road>{
    int house1, house2, distance;

    public Road(int house1, int house2, int distance) {
        this.house1 = house1;
        this.house2 = house2;
        this.distance = distance;
    }

    // 거리를 기준으로 비교
    @Override
    public int compareTo(Road o) {
        return this.distance - o.distance;
    }
}

public class Main {
    private static int[] parents, children;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken(" ")), n = Integer.parseInt(st.nextToken());
            if(m == 0 && n == 0) break; // 입력이 0 0이면 루프 중지

            PriorityQueue<Road> roads = new PriorityQueue<>();
            parents = new int[m];
            children = new int[m];
            int cost = 0;

            // 자기 자신을 부모로 설정
            for(int i = 0; i < m; i++)
                parents[i] = i;
            Arrays.fill(children, 1); // 자식 수를 1로 초기화

            while (n-- > 0) {
                st = new StringTokenizer(br.readLine());
                roads.offer(new Road(Integer.parseInt(st.nextToken(" ")), Integer.parseInt(st.nextToken(" ")), Integer.parseInt(st.nextToken()))); // 우선순위 큐에 도로 객체 삽입
            }

            while (!roads.isEmpty()){
                Road road = roads.poll();
                int h1 = findParent(road.house1), h2 = findParent(road.house2); // 최상위 부모 찾기
                if(h1 != h2){ // 최상위 부모가 같지 않으면
                    // 자식 수가 많은 쪽에 붙이기
                    if(children[h1] < children[h2]){
                        parents[h1] = h2;
                        children[h2] += children[h1];
                    }
                    else{
                        parents[h2] = h1;
                        children[h1] += children[h2];
                    }
                }
                else // 최상위 부모가 같으면
                    cost += road.distance; // 비용 더하기
            }
            bw.write(cost + "\n"); // 출력
        }
        br.close();
        bw.close();
    }

    // 최상위 부모 탐색 함수
    private static int findParent(int child){
        if(child != parents[child]) // 자기자신이 부모가 아니면
            parents[child] = findParent(parents[child]); // 최상위 부모를 찾아 삽입
        return parents[child]; // 부모 반환
    }
}
