package cindya.bj1916_최소비용구하기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 버스의 정보를 담은 클래스
class Bus implements Comparable<Bus>{
    int destination, fee;

    public Bus(int destination, int fee) {
        this.destination = destination;
        this.fee = fee;
    }

    @Override
    public int compareTo(Bus bus) {
        return this.fee - bus.fee;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()), m = Integer.parseInt(br.readLine());
        List<Bus>[] buses = new List[n + 1];
        StringTokenizer st;
        PriorityQueue<Bus> pq = new PriorityQueue<>();
        int[] cost = new int[n + 1];
        boolean[] visit = new boolean[n + 1];

        Arrays.fill(cost, Integer.MAX_VALUE); // cost를 MaxValue로 세팅
        for(int i = 0; i <= n; i++)
            buses[i] = new ArrayList<>();

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            buses[Integer.parseInt(st.nextToken(" "))].add(new Bus(Integer.parseInt(st.nextToken(" ")), Integer.parseInt(st.nextToken())));
        }
        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken(" ")), end = Integer.parseInt(st.nextToken(" "));

        // 시작지점 비용을 0으로 하고 pq에 삽입
        cost[start] = 0;
        pq.offer(new Bus(start, cost[start]));

        while (!pq.isEmpty()){
            Bus bus = pq.poll();
            if(!visit[bus.destination]){ // 방문하지 않은 도시면
                visit[bus.destination] = true; // 방문 표시
                for(Bus nb : buses[bus.destination]){ // 연결된 도시를 돌며
                    if(cost[nb.destination] > cost[bus.destination] + nb.fee){ // 현재 도시를 거치는 것이 더 적은 비용이 들면
                        cost[nb.destination] = cost[bus.destination] + nb.fee; // 적은 비용으로 바꾸고
                        pq.offer(new Bus(nb.destination, cost[nb.destination])); // pq에 삽입
                    }
                }
            }
        }

        System.out.println(cost[end]);

        br.close();
    }
}
