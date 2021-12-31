import java.util.*;
import java.io.*;

/**
 * 가수 출연 순서대로 진입차수 설정 및 그래프 설정
 * 스트링 빌더를 사용하여 데이터 쌓기
 * 
 * 작업 도중 순서를 정하는게 불가능 할 경우는
 * 모든 것을 출력하지 않았는데 큐에 담기는게 없는것!
 */
public class BJ2623_음악프로그램 {

    public static int n,m;
    public static int[] degree;
    public static ArrayList<Integer>[] graph;
    public static StringBuilder sb;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        degree = new int[n+1];
        graph = new ArrayList[n+1];
        for(int i = 0; i <= n; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());

            int lineNum = Integer.parseInt(st.nextToken());
            int psinger = Integer.parseInt(st.nextToken());

            while(st.hasMoreTokens()){
                int curSinger = Integer.parseInt(st.nextToken());
                graph[psinger].add(curSinger);
                degree[curSinger]++;
                psinger = curSinger;
            }
        }

        topological_sort();
        System.out.println(sb);
    }

    public static void topological_sort(){
        // 나온개수 체크
        int cnt = 0;

        Queue<Integer> q = new LinkedList<>();
        for(int i =1; i <= n; i++){
            if(degree[i] == 0){
                q.offer(i);
            }
        }

        while(!q.isEmpty()){
            int cur = q.poll();
            cnt++;
            sb.append(cur).append("\n");

            for(int i = 0; i < graph[cur].size(); i++){
                int next = graph[cur].get(i);
                degree[next]--;
                if(degree[next] == 0){ 
                    q.offer(next);
                }
            }
        }

        if(cnt != n){
            sb.setLength(0);
            sb.append(0);
        }
    }
}
