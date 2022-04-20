import java.util.*;

class Solution {
    Status[] adjList;
    HashSet<Integer> trapSet;
    int[][] visited;
    int answer = Integer.MAX_VALUE;
    public int solution(int n, int start, int end, int[][] roads, int[] traps) {
        adjList = new Status[n+1];
        trapSet = new HashSet<>();
        visited = new int[n+1][2];
        for(int i=0; i<n+1; i++) Arrays.fill(visited[i],Integer.MAX_VALUE);
        for(int t: traps) trapSet.add(t);
        for(int i=0; i<n+1; i++) adjList[i] = new Status();
        for(int[] road : roads){
            int a = road[0];
            int b = road[1];
            int dist = road[2];
            adjList[a].bList.add(new int[]{b,dist});
            if(trapSet.contains(b)) adjList[b].cList.add(new int[]{a,dist});
        }
        for(int i=1; i<n+1; i++){
            if(!trapSet.contains(i)) adjList[i].cList = adjList[i].bList;
        }
        
        visited[start][0] = 0;
        DFS(start,0, end);
        
        return answer;
    }

    public void DFS(int next, int d, int end){
        // System.out.println(next);
        if(next == end) {answer = Integer.min(answer, d); return;}

        ArrayList<int[]> nextHop = (!adjList[next].activated)? adjList[next].bList : adjList[next].cList;
        for(int[] hop : nextHop){
            int hopNode = hop[0];
            int hopDist = hop[1];
            boolean preStatus = adjList[hopNode].activated;
            int idx = (!preStatus) ? 0 : 1;
            int preDist = visited[hopNode][idx];
            if(visited[hopNode][idx]> hopDist+d){
                adjList[hopNode].activated = !preStatus;
                visited[hopNode][idx] = hopDist+d;
                DFS(hopNode,hopDist+d,end);
                visited[hopNode][idx] = preDist;
                adjList[hopNode].activated = preStatus;
            }

        }

    }
}

class Status{
    boolean activated = false;
    ArrayList<int[]> bList = new ArrayList<>();
    ArrayList<int[]> cList = new ArrayList<>();
    public Status(){}
}