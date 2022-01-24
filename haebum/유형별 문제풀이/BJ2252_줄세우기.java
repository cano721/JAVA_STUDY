import java.util.*;
import java.io.*;

/**
 * 위상정렬을 통해 주어진 친구사이의 키 순을
 * 진입차수로 저장하고 큐를 통해 나온 숫자 출력
 */
public class BJ2252_줄세우기 {
    public static int n,m;
    public static int[] degree;
    public static ArrayList<Integer>[] graph;
    public static BufferedWriter bw;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        degree = new int[n+1];
        graph = new ArrayList[n+1];
        for(int i = 0; i <= n; i++){
            graph[i]= new ArrayList<>();
        }
        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            degree[b]++;
            graph[a].add(b);
        }
        topological_sort();
        bw.flush();
        bw.close();
    }

    public static void topological_sort() throws IOException{
        Queue<Integer> q = new LinkedList<>();

        for(int i = 1; i <= n; i++){
            if(degree[i] == 0){
                q.offer(i);
            }
        }

        while(!q.isEmpty()){
            int cur = q.poll();

            bw.write(cur + " ");

            for(int i = 0; i < graph[cur].size(); i++){
                int next = graph[cur].get(i);
                degree[next]--;
                if(degree[next] == 0){
                    q.offer(next);
                }
            }
        }
    }

}
