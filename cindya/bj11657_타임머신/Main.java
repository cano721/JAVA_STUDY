package cindya.bj11657_타임머신;

import java.io.*;
import java.util.*;

// 버스 정보를 담은 클래스
class Bus{
    int destination;
    long time;

    public Bus(int destination, long time) {
        this.destination = destination;
        this.time = time;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken(" ")), m = Integer.parseInt(st.nextToken());
        List<Bus>[] buses = new List[n + 1];
        long[] times = new long[n + 1];
        int[] checkCycle = new int[n + 1];
        boolean[] visit = new boolean[n + 1];
        Queue<Bus> q = new LinkedList<>();

        for(int i = 0; i <= n; i++)
            buses[i] = new ArrayList<>();
        Arrays.fill(times, Integer.MAX_VALUE); // 시간을 가장 큰 정수 값으로 세팅

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            buses[Integer.parseInt(st.nextToken(" "))].add(new Bus(Integer.parseInt(st.nextToken(" ")), Integer.parseInt(st.nextToken())));
        }
        br.close();

        // 시작 지점 시간을 0으로 세팅하고 q에 삽입
        times[1] = 0;
        q.offer(new Bus(1, times[1]));

        while (!q.isEmpty()){
            Bus bus = q.poll();
            visit[bus.destination] = false; // q에서 뺐음을 표시
            // 버스의 종착지에서 탈 수 있는 버스들 루프
            for(Bus nb : buses[bus.destination]) {
                // 직행보다 경유가 빠르면
                if (times[nb.destination] > times[bus.destination] + nb.time) {
                    times[nb.destination] = times[bus.destination] + nb.time; // 짧은 시간으로 변경
                    // 다음 종착지가 q에 없으면
                    if(!visit[nb.destination]) {
                        checkCycle[nb.destination]++; // cycle을 체크하기 위해 1 증가
                        if (checkCycle[nb.destination] == n) { // n번 이상 방문했다면
                            visit[0] = true; // negative cycle이 발생함을 표시
                            break; // 루프 중단
                        }
                        visit[nb.destination] = true; // q에 삽입했음을 표시
                        q.offer(new Bus(nb.destination, times[nb.destination])); // q에 삽입
                    }
                }
            }
        }

        if(visit[0]) bw.write("-1"); // cycle이 발생했으면 -1 출력
        else { // 이상이 없으면
            for (int i = 2; i <= n; i++) // 2번째 도시부터
                if (times[i] == Integer.MAX_VALUE) // 1번 도시에서 갈 수 없는 경우
                    bw.write(-1 + "\n"); // -1 출력
                else // 갈 수 있는 경우
                    bw.write(times[i] + "\n"); // 최단거리 출력
        }
        bw.close();
    }
}
