import java.util.*;
import java.io.*;

public class BJ11438_LCA2 {

    public static int n,m,k;
    public static ArrayList<Integer>[] tree;
    public static int[][] parent;
    public static int[] depths;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        tree = new ArrayList[n+1];

        for(int i = 0; i<= n; i++){
            tree[i] = new ArrayList<>();
        }

        
        StringTokenizer st;
        for(int i = 0; i < n-1; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            
            tree[a].add(b);
            tree[b].add(a);
        }
        
        // 제일 깊게 들어갈 수 있는 깊이 알아내기 
        int temp = 1;
        k = 0;
        while(temp <= n){
            temp <<= 1;
            k++;
        }

        // 각 정점의 깊이
        depths = new int[n+1];
        // 정점들의 부모를 담을 배열 parent[정점][2배수] = 부모
        parent = new int[n+1][k];
        dfs(1,1);
        fillParents();

        m = Integer.parseInt(br.readLine());

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int result = LCA(a,b);
            bw.write(result + "\n");
        }

        bw.flush();
        bw.close();
    }

    public static int LCA(int a, int b){
        
        // a를 더 깊은것으로 변경해두기 b가 더 낮은것!
        if(depths[a] < depths[b]){
            int temp = a;
            a = b;
            b = temp;
        }

        // 같은 깊이가 될때까지 맞춰주기
        for(int i = k -1; i >= 0; i--){
            if(Math.pow(2, i) <= depths[a] - depths[b]){
                a = parent[a][i];
            }
        }

        if(a == b) return a;


        //같은 깊이인데 상위 부모가 같지 않으면 그 깊이까지 땡기기
        for(int i = k-1; i >= 0; i--){
            if(parent[a][i] != parent[b][i]){
                a = parent[a][i];
                b = parent[b][i];
            }
        }

        // 그럼 그 위의 부모가 공통 조상 노드임!
        return parent[a][0];
    }

    // DP를 이용해 각 노드별 2^K 번 째 부모 노드를 저장한다.
    private static void fillParents() {
        for (int i = 1; i < k; i++) {
            for (int j = 1; j <= n; j++) {
                parent[j][i] = parent[ parent[j][i - 1] ][i - 1];
            }
        }
    }

    public static void dfs(int current, int depth){
        // 현재 깊이 저장
        depths[current] = depth;

        // 현재 정점 아래의 노드 돌기
        for(int next : tree[current]){
            // 아직 깊이가 저장 안되어있다면
            if(depths[next] == 0){
                // 현재 정점을 부모로 저장하고
                parent[next][0] = current;
                // 깊이 저장하러가기
                dfs(next,depth+1);
            }
        }
    }
}
