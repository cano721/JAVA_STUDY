import java.util.*;
import java.io.*;

/**
 * 최소비용으로 모든 컴퓨터 연결하기 MST
 * 프림버전
 */
public class BJ1922_네트워크연결_프림 {
    public static int n,m;
    public static int[] parent,rank;
    public static long answer;
    public static ArrayList<Node>[] nodeList;
    static boolean visited[];

    static class Node{
        int x;
        int y;
        int cost;
        Node(int x,int y,int cost){
            this.x = x;
            this.y = y;
            this.cost = cost;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        visited = new boolean[n+1];
        //각 노드에 연결되어있는 간선 확인 배열
        //nodelist[노드].get(간선);
        nodeList = new ArrayList[n+1];
        for(int i = 1; i <=n; i++){
            nodeList[i] = new ArrayList<Node>();
        }

        StringTokenizer st;
        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            nodeList[start].add(new Node(start,end,cost));
            nodeList[end].add(new Node(end,start,cost));
        }
        prim();
        System.out.println(answer);


    }

    public static void prim(){
        //간선 담을 우선순위 큐
        PriorityQueue<Node> q = new PriorityQueue<>((o1,o2) -> o1.cost - o2.cost);
        //노드 담을 디큐
        Deque<Integer> dq = new ArrayDeque<>();
        // 아무 노드 한개 추가
        dq.add(1);

        //노드에 연결된 간선 추가할거임
        while(!dq.isEmpty()){
            int curNodeIdx = dq.poll();
            //현재노드는 방문한것으로 표기
            visited[curNodeIdx] = true;
            //노드와 연결된 간선리스트 가져오기
            ArrayList<Node> tempList = nodeList[curNodeIdx];
            //간선돌기
            for(int i = 0; i < tempList.size(); i++){
                //간선의 끝이 방문하지 않은 노드면 추가
                if(visited[tempList.get(i).y] == false){
                    q.offer(tempList.get(i));
                }
            }
            //현재 추가된 간선 중 가장 적은 비용의 간선 뽑아서 추가
            while(!q.isEmpty()){
                Node nextNode = q.poll();
                if(visited[nextNode.y] == false){
                    visited[nextNode.y] = true;
                    answer += nextNode.cost;
                    // 노드리스트에 연결된 노드 추가
                    dq.offer(nextNode.y);
                    break;
                }
            }
        }
        
    }
}
