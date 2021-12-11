package cindya.bj1865_웜홀;

import java.io.*;
import java.util.*;

class Connect{
    int destination, time;

    public Connect(int destination, int time) {
        this.destination = destination;
        this.time = time;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int tc = Integer.parseInt(br.readLine());

        // test case loop
        while (tc-- > 0){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken(" ")), m = Integer.parseInt(st.nextToken(" ")), w = Integer.parseInt(st.nextToken());
            List<Connect>[] connects = new List[n + 1];
            Queue<Connect> q = new LinkedList<>();
            int[] times = new int[n + 1], cntVisit = new int[n + 1];
            boolean[] checkQ = new boolean[n + 1];

            Arrays.fill(times, Integer.MAX_VALUE); // 걸리는 시간을 가장 큰 정수로 세팅
            for(int i = 0; i <= n; i++) {
                connects[i] = new ArrayList<>();
                connects[0].add(new Connect(i, 0)); // 시작점 0번 지점에서 다른 지점들로 가는 도로를 생성
            }

            // 도로 생성
            for(int i = 0; i < m; i++){
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken(" ")), e = Integer.parseInt(st.nextToken(" ")), t = Integer.parseInt(st.nextToken());
                connects[s].add(new Connect(e, t));
                connects[e].add(new Connect(s, t));
            }

            // 웜홀 생성
            for(int i = 0; i < w; i++){
                st = new StringTokenizer(br.readLine());
                connects[Integer.parseInt(st.nextToken(" "))].add(new Connect(Integer.parseInt(st.nextToken(" ")), (Integer.parseInt(st.nextToken()) * -1)));
            }


            // 0번 지점을 시작점으로 세팅
            times[0] = 0;
            q.offer(new Connect(0, times[0]));

            Loop:
            while (!q.isEmpty()) {
                Connect connect = q.poll();
                checkQ[connect.destination] = false;

                // connect의 도착 지점에서 갈 수 있는 도로와 웜홀들을 확인
                for (Connect nc : connects[connect.destination]) {
                    // 직행보다 connect를 경유하는 게 빠르면
                    if (times[nc.destination] > times[connect.destination] + nc.time) {
                        // 짧은 시간을 선택
                        times[nc.destination] = times[connect.destination] + nc.time;
                        if (!checkQ[nc.destination]) { // q에 이 지점이 들어있지 않으면
                            cntVisit[nc.destination]++; // 방문 횟수 증가
                            if (cntVisit[nc.destination] == n) { // 방문 횟수가 n번이면
                                checkQ[0] = true; // 사이클이 생겼음을 표시
                                break Loop; // 루프 중단
                            }
                            // 방문 횟수가 n번 이하이면
                            checkQ[nc.destination] = true; // q에 삽입 표시
                            q.offer(new Connect(nc.destination, times[nc.destination])); // q에 삽입
                        }
                    }
                }
            }
            if(checkQ[0]) bw.write("YES\n"); // 사이클이 있으면 YES 출력
            else bw.write("NO\n"); // 없으면 NO 출력
        }
        br.close();
        bw.close();
    }
}
