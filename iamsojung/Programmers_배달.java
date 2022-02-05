import java.util.*;

class Solution {
    static ArrayList<Node>[] arrayList;
    static int[] distance;

    static public int solution(int N, int[][] road, int K) {

        int answer = 0;

        arrayList = new ArrayList[N + 1];
        distance = new int[N + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        for (int i = 1; i <= N; i++) {
            arrayList[i] = new ArrayList<>();
        }
        for (int i = 0; i < road.length; i++) {
            arrayList[road[i][0]].add(new Node(road[i][1], road[i][2]));

            arrayList[road[i][1]].add(new Node(road[i][0], road[i][2]));
        }
        distance[1] = 0;
        
        dijkstra();
        for (int cost : distance) {
            if (cost <= K) {
                answer++;
            }
        }

        return answer;
    }

    private static void dijkstra() {

        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.add(new Node(1,0));

        while (!queue.isEmpty()){

            Node node = queue.poll();

            int v = node.v;
            int weight = node.weight;

            if(distance[v]<weight){
                continue;
            }

            for(int i=0;i<arrayList[v].size();i++){
                int v2 = arrayList[v].get(i).v;
                int weight2 = arrayList[v].get(i).weight+weight;

                if(distance[v2]>weight2){
                    distance[v2]=weight2;
                    queue.add(new Node(v2,weight2));
                }
            }
        }
    }


    static class Node implements Comparable<Node> {
        int v;
        int weight;

        public Node(int v, int weight) {
            this.v = v;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return weight - o.weight;
        }

    }
}