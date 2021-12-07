package cindya.bj1753_최단경로;

import java.io.*;
import java.util.*;

class Connect implements Comparable<Connect>{
    int destination, weight;

    public Connect(int destination, int weight) {
        this.destination = destination;
        this.weight = weight;
    }

    @Override
    public int compareTo(Connect o) {
        return this.weight - o.weight;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int v = Integer.parseInt(st.nextToken(" ")), e = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(br.readLine());
        List<Connect>[] map = new List[v + 1];
        int[] distance = new int[v + 1];
        boolean[] check = new boolean[v + 1];
        PriorityQueue<Connect> pq = new PriorityQueue<>();

        Arrays.fill(distance, Integer.MAX_VALUE); // distance를 가장 큰 값으로 초기화
        for(int i = 0; i <= v; i++)
            map[i] = new ArrayList<>();

        // 입력받은 간선을 map에 저장
        for(int i = 0; i < e; i++){
            st = new StringTokenizer(br.readLine());
            map[Integer.parseInt(st.nextToken(" "))].add(new Connect(Integer.parseInt(st.nextToken(" ")), Integer.parseInt(st.nextToken())));
        }
        br.close();

        // 시작점 weight를 0으로 설정하고 pq에 추가
        distance[k] = 0;
        pq.offer(new Connect(k, distance[k]));

        while (!pq.isEmpty()){  // 모든 간선을 확인할 때까지 루프
            Connect c = pq.poll();
            if(!check[c.destination]){ // 방문하지 않은 정점이면
                check[c.destination] = true; // 방문 표시
                for(Connect nc : map[c.destination]){ // c와 연결된 모든 정점 루프
                    // 기존 거리가 시작점~현재 정점~다음 정점으로 가는 거리보다 크면
                    if(distance[nc.destination] > distance[c.destination] + nc.weight){
                        distance[nc.destination] = distance[c.destination] + nc.weight; // 짧은 값으로 교체
                        pq.offer(new Connect(nc.destination, distance[nc.destination])); // pq에 정점 삽입
                    }
                }
            }
        }
        for(int i = 1; i <= v; i++) // 모든 정점 출력
            if(distance[i] == Integer.MAX_VALUE) bw.write("INF\n");
            else bw.write(distance[i] + "\n");

        bw.close();
    }
}
