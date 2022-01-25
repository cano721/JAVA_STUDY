import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
//https://dang2dangdang2.tistory.com/169 블로그 참조하였습니다.
import java.util.ArrayList;
public class Main {
    static boolean visited[];
    static int n, end;
    static int ans = -1;
    static ArrayList<ArrayList<Integer>> list;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());

        
        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        list = new ArrayList<>();
        visited = new boolean[n+1];

        int m = Integer.parseInt(br.readLine()); //부모자식 관계 개수

        for(int i=0;i<=n;i++) list.add(new ArrayList<>());

        for(int i=0;i<m;i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()); //부모
            int b = Integer.parseInt(st.nextToken());//자식

            list.get(a).add(b);
            list.get(b).add(a);
        }

        dfs(start,0);
        System.out.println(ans);

    }

    private static void dfs(int start,int cnt) {
        visited[start] = true;

        for(Integer i : list.get(start)){
            if(!visited[i]){
                if(i == end){
                    ans = cnt+1;
                    return;
                }

                dfs(i,cnt+1);
            }
        }

    }
}