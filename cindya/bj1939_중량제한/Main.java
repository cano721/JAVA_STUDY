package cindya.bj1939_중량제한;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 다리 클래스
class Bridge{
    int destination, limit;

    public Bridge(int destination, int limit) {
        this.destination = destination;
        this.limit = limit;
    }
}

public class Main {
    private static List<Bridge>[] bridges;
    private static boolean[] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken());
        int start, end, min = 0, max = 0;
        bridges = new List[n + 1];
        visit = new boolean[n + 1];

        for(int i = 0; i <= n; i++)
            bridges[i] = new ArrayList<>();

        // 양방향으로 저장
        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken()), c = Integer.parseInt(st.nextToken());
            bridges[a].add(new Bridge(b, c));
            bridges[b].add(new Bridge(a, c));
            max = Math.max(max, c);
        }
        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        while (min <= max){
            int mid = (max + min) / 2;
            // mid보다 크거나 같은 중량을 옮길 수 있는 길이 있으면
            if (keepLimit(start, end, mid))
                min = mid + 1; // 최소값을 올림
            else // 없으면
                max = mid - 1; // 최대값을 내림
        }

        System.out.println(max);
    }

    // limit만큼의 중량을 견딜 수 있는 경로가 있는지 반환하는 함수
    private static boolean keepLimit(int start, int end, int limit){
        Queue<Integer> q = new LinkedList<>();
        Arrays.fill(visit, false);

        // 시작점을 방문처리하고 큐에 삽입
        visit[start] = true;
        q.offer(start);

        while (!q.isEmpty()){
            int stop = q.poll();

            if(stop == end) return true; // end에 도착하면 true 반환

            // 다음으로 갈 수 있는 섬들 루프
            for(Bridge bridge : bridges[stop]){
                // 아직 방문하지 않았고, 중량 제한이 limit 이상이면
                if(!visit[bridge.destination] && limit <= bridge.limit){
                    visit[bridge.destination] = true; // 방문처리하고
                    q.offer(bridge.destination); // q에 삽입
                }
            }
        }
        return false;
    }
}
