import java.util.*;
import java.io.*;

/**
 * LCA를 통해서 공통 조상 찾기
 * 루트 노드를 먼저 찾은 후 이후 LCA와 같이 진행
 */
public class BJ3584_가장가까운공통조상 {

    public static int n,k;
    public static ArrayList<Integer>[] trees;
    public static int[][] parent;
    public static int[] depths,rootCheck;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for(int tc = 0; tc < t; tc++){
            n = Integer.parseInt(br.readLine());

            trees = new ArrayList[n+1];
            for(int i = 0; i <= n; i++){
                trees[i] = new ArrayList<>();
            }

            rootCheck = new int[n+1];
            for(int i = 0; i < n-1; i++){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                trees[a].add(b);
                rootCheck[b] = 1;
            }

            int root = 0;
            for(int i = 1; i < rootCheck.length; i++){
                if(rootCheck[i] == 0){
                    root = i;
                }
            }

            int temp = 1;
            k = 0;
            while(temp <= n){
                temp<<=1;
                k++;
            }

            parent = new int[n+1][k];
            depths = new int[n+1];
            dfs(root,1);
            fillParents();

            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            bw.write(lca(a,b) +"\n");

        }
        bw.flush();
        bw.close();
    }

    public static int lca(int a, int b){

        // a를 더 깊은것으로 변경해두기 b가 더 낮은것!
        if(depths[a] < depths[b]){
            int temp = a;
            a = b;
            b = temp;
        }

        // 같은 깊이가 될때까지 맞춰주기
        for(int i = k-1; i >= 0; i--){
            if(Math.pow(2, i) <= depths[a] - depths[b]){
                a = parent[a][i];
            }
        }

        // 같은 깊이일때 두개가 같다면 리턴
        if(a == b) return a;

        //같은 깊이인데 같지 않으면 상위 부모가 같아질때까지의 깊이까지 땡기기
        for(int i = k-1; i >= 0; i--){
            if(parent[a][i] != parent[b][i]){
                a = parent[a][i];
                b = parent[b][i];
            }
        }

        return parent[a][0];
    }

    // dp 이용한 각 노드별 2^k번째 부모 노드 저장
    public static void fillParents(){
        for(int i = 1; i < k; i++){
            for(int j = 1; j <= n; j++){
                parent[j][i] = parent[parent[j][i-1]][i-1];
            }
        }
    }

    public static void dfs(int node, int depth){
        // 현재 깊이 저장
        depths[node] = depth;
        // 현재 정점 아래의 노드 돌기
        for(int next: trees[node]){
            // 아직 깊이가 저장 안되어있다면
            if(depths[next] == 0){
                // 현재 정점을 부모로 저장하고
                parent[next][0] = node;
                // 깊이 저장하러가기
                dfs(next,depth+1);
            }
        }
    }
}
