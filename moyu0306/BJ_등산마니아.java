import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class Main {
        static ArrayList<ArrayList<Integer>> adjList;
        static long tot;
    public static void main(String[] args) throws IOException {
        // write your code here
        InputStreamReader ir = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(ir);
        int N = Integer.parseInt(br.readLine());
        adjList = new ArrayList<>();
        for(int i=0; i<N+1;i++) adjList.add(new ArrayList<Integer>());
        for(int i=0 ; i<N-1;i++){
            String[] splits= br.readLine().split(" ");
            adjList.get(Integer.parseInt(splits[0])).add(Integer.parseInt(splits[1]));
            adjList.get(Integer.parseInt(splits[1])).add(Integer.parseInt(splits[0]));
        }
        DFS(1,-1,N);
        System.out.println(tot);
    }

    public static long DFS(int node, int parent ,long N){
        long cnt =1;
        for(int child :adjList.get(node)){
            if(child == parent) continue;
            cnt+=DFS(child,node, N);
        }
        if(node !=1) tot += (cnt*(cnt-1)/2 + (N-cnt)*cnt);
        return cnt;
    }
}