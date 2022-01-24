import java.util.*;
import java.io.*;

/**
 * 도시들의 연결여부가 주어지고
 * 마지막줄에 들려야하는 도시들이 주어짐.
 * 유니온파인드를 이용해서 다 연결되어있는지 확인!
 */
public class BJ1976_여행가자 {

    public static int[] parent;
    public static int[] rank;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        parent = new int[n+1];
        rank = new int[n+1];
        for(int i = 1; i <= n; i++){
            parent[i] = i;
            rank[i] = 0;
        }
        
        int m = Integer.parseInt(br.readLine());
        StringTokenizer st;
        // 유니온합치기
        for(int i = 1; i <= n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <=n; j++){
                int num = Integer.parseInt(st.nextToken());
                if(num == 1){
                    union(i,j);
                }
            }
        }

        //정답
        boolean answer = true;

        // 여행도시
        st = new StringTokenizer(br.readLine());
        // 맨 처음 도시
        int check = find(Integer.parseInt(st.nextToken()));
        while(st.hasMoreTokens()){
            int city = Integer.parseInt(st.nextToken());
            // 연결여부 체크
            if(find(city) != check){
                answer = false;
                break;
            }
        }

        bw.write(answer ? "YES\n" : "NO\n");
        
        // 다 출력 후 종료
        bw.flush();
        bw.close();
    }

    public static int find(int x){
        if(parent[x] == x){
            return x;
        }

        return parent[x] = find(parent[x]);
    }

    public static void union(int x, int y){
        x = find(x);
        y = find(y);

        if(x == y){
            return;
        }

        if(rank[x] < rank[y]){
            parent[x] = y;
        }else{
            parent[y] = x;
            if(rank[x] == rank[y]){
                rank[x]++;
            }
        }
    }
}