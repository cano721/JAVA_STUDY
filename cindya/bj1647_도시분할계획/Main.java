package cindya.bj1647_도시분할계획;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 도로 클래스
class Road implements Comparable<Road>{
    int house1, house2, cost;

    public Road(int house1, int house2, int cost) {
        this.house1 = house1;
        this.house2 = house2;
        this.cost = cost;
    }

    // 비용을 기준으로 비교
    @Override
    public int compareTo(Road o) {
        return this.cost - o.cost;
    }
}

public class Main {
    private static int[] parent, child;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken(" ")), m = Integer.parseInt(st.nextToken());
        PriorityQueue<Road> roads = new PriorityQueue<>();
        Road last = null;
        int totalCost = 0;
        parent = new int[n + 1];
        child = new int[n + 1];

        // 자기자신을 부모로 초기화
        for(int i = 0; i < n; i++)
            parent[i] = i;
        Arrays.fill(child, 1); // 자식 수를 1로 초기화

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken(" ")), b = Integer.parseInt(st.nextToken(" ")), c = Integer.parseInt(st.nextToken());
            roads.offer(new Road(a, b, c));
        }
        br.close();

        while (!roads.isEmpty()){
            Road road = roads.poll();
            int h1 = findParent(road.house1), h2 = findParent(road.house2); // 도로로 이어져있는 두 집의 최상위 부모를 탐색
            if(h1 != h2){ // 최상위 부모가 같지 않으면
                // 자식 수가 많은 쪽에 합치기
                if(child[h1] < child[h2]){
                    parent[h1] = h2;
                    child[h2] += child[h1];
                }
                else{
                    parent[h2] = h1;
                    child[h1] += child[h2];
                }
                totalCost += road.cost; // 비용을 더함
                last = road;
            }
        }
        if(last != null) totalCost -= last.cost; // 이어진 도로 중 마지막 도로의 비용을 뻄 (마을을 분할하기 위해)
        System.out.println(totalCost); // 출력
    }

    // 최상위 부모를 찾는 함수
    private static int findParent(int child){
        if(parent[child] != child)
            parent[child] = findParent(parent[child]);
        return parent[child];
    }
}
