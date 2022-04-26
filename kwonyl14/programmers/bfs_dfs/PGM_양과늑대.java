import java.util.*;

class Solution {
    
    static class Node {
        int vertex;
        Node next;
        
        public Node (int vertex, Node next) {
            this.vertex = vertex;
            this.next = next;
        }
        
        @Override
        public String toString() {
            return "vertex = " + vertex + ", next = " + next;
        }
    }
    
    int[] info;
    int answer, size;
    Node[] graph;
    
    public int solution(int[] info, int[][] edges) {
        this.info = info;
        size = info.length;
        graph = new Node[size];
        
        for (int i = 0; i < edges.length; i++) {
            int from = edges[i][0];
            int to = edges[i][1];
            
            graph[from] = new Node(to, graph[from]);
        }
                
        dfs(1 << 0, 1, 0);
        
        return answer;
    }
    
    public void dfs(int bit, int sheep, int wolf) {
        if (sheep == wolf) {
            return;
        }
        
        if (sheep > answer) {
            answer = sheep;
        }
        
        for (int i = 0; i < size; i++) {
            //마킹된 노드들과 연결된 노드들만 탐색함
            if ((bit & (1 << i)) > 0) {
                for (Node temp = graph[i]; temp != null; temp = temp.next) {
                    //연결된 이 노드가 방문된 적 없는 노드여야함
                    if ((bit & 1 << temp.vertex) > 0) continue;
                    
                    //양이면 if조건, 늑대면 else조건으로 dfs
                    if (info[temp.vertex] == 0) dfs(bit | 1 << temp.vertex, sheep + 1, wolf);
                    else dfs(bit | 1 << temp.vertex, sheep, wolf + 1);
                }
            }    
        }
    }
}