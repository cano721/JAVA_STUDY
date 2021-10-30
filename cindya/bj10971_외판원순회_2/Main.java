package cindya.bj10971_외판원순회_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 도시마다 연결된 도시들로의 이동 비용을 저장하는 클래스
class City{
    private int cityNumber;
    private Map<Integer, Integer> edges = new HashMap<>();

    public City(int cityNumber) {
        this.cityNumber = cityNumber;
    }

    public void addEdge(int city, int cost){
        edges.put(city, cost);
    }

    public Map<Integer, Integer> getEdges() {
        return edges;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Integer && (int)obj == cityNumber;
    }
}

public class Main {
    private static boolean[] visit;
    private static List<City> cities;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        cities = new ArrayList<>();
        visit = new boolean[n];
        Arrays.fill(visit, false);
        visit[0] = true;

        for(int i = 0; i < n; i++){
            City city = new City(i);
            int[] edges = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            // 길이 없는 경우를 빼고 저장
            for(int j = 0; j < n; j++){
                if(edges[j] != 0)
                    city.addEdge(j, edges[j]);
            }
            cities.add(city);
        }

        br.close();

        int cost = getMinCost(0, 0);
        System.out.println(cost);
    }

    private static int getMinCost(int c, int cost){
        City city = cities.get(c);
        int minCost = Integer.MAX_VALUE;

        for(int edge : city.getEdges().keySet()){ // 연결된 도시들 루프
            if(!visit[edge]){ // 방문하지 않았으면 방문
                visit[edge] = true;
                minCost = Math.min(minCost, getMinCost(edge, cost + city.getEdges().get(edge))); // 더 작은 비용을 선택
                visit[edge] = false;
            }
        }
        if(minCost == Integer.MAX_VALUE && city.getEdges().containsKey(0)){ // 루프를 돌지 않고, 시작점으로의 경로가 있을 때
            for(boolean v : visit){ // 모든 도시를 방문했는지 확인
                if(!v) return minCost;
            }
            minCost = cost + city.getEdges().get(0); // 마지막 도시일 경우 시작 도시로의 이동 비용을 더하여 반환
        }
        return minCost;
    }
}
