import java.io.*;
import java.util.*;

/**
 * 모든 집이 최소 거리로 연결되어 있어야 가장 적은 돈이 들어감.
 * mst 알고리즘(프림 버전)
 */
public class BJ6497_전력난_프림 {

    public static int m,n,allE,e;
    public static ArrayList<Node>[] a;
    public static boolean[] visited;

    static class Node{
        int idx;
        int next;
        int cost;
        Node(int idx, int next, int cost){
            this.idx = idx;
            this.next = next;
            this.cost = cost;
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st;
        while(true){
            st = new StringTokenizer(br.readLine());

            int m = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());
            if(m == 0 && n == 0){
                break;
            }

            a = new ArrayList[m];
            for(int i = 0; i < m; i++){
                a[i] = new ArrayList<Node>();
            }

            visited = new boolean[m];
            allE = 0;
            e = 0;
            
            for(int i = 0; i < n; i++){
                st = new StringTokenizer(br.readLine());
                
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int z = Integer.parseInt(st.nextToken());

                a[x].add(new Node(x,y,z));
                a[y].add(new Node(y,x,z));

                allE += z;
            }

            prim();
            bw.write(allE-e +"\n");
        }
        bw.flush();
        bw.close();
    }

    public static void prim(){
        PriorityQueue<Node> q = new PriorityQueue<>((o1,o2) -> o1.cost - o2.cost);
        Deque<Integer> dq = new ArrayDeque<>();

        dq.offer(0);
        visited[0] = true;
        while(!dq.isEmpty()){
            int nodeIdx = dq.poll();
            for(int i = 0; i < a[nodeIdx].size(); i++){
                Node curNode = a[nodeIdx].get(i);

                if(visited[curNode.next] == false){
                    q.offer(curNode);
                }
            }

            while(!q.isEmpty()){
                Node curNode = q.poll();

                if(visited[curNode.next] == false){
                    visited[curNode.next] = true;
                    e += curNode.cost;
                    dq.offer(curNode.next);
                    break;
                }
            }
        }
    }
}
