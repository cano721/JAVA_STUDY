import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solv1939 {

    static ArrayList<Node> [] lands;
    static boolean[] visited;
    static int N, M;
    static int start, destination;



    static int binarySearch(int max) {
        int left = 1, right = max;
        // 최소 거리와 최대 거리

        while (left <= right) {
            int mid = (left + right) / 2;
            visited = new boolean[N + 1];

            if (bfs(mid)) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return right;
    }

    static boolean bfs(int mid) {
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        visited[start] = true;

        while(! q.isEmpty()) {
            int land = q.poll();

            if(land == destination) {
                return true;
            }
            for(int i = 0; i < lands[land].size() ; i++) {
                if(!visited[lands[land].get(i).end] && mid <= lands[land].get(i).cost) {
                    visited[lands[land].get(i).end] = true;
                    q.add(lands[land].get(i).end);
                }
            }
        }

        return false;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        lands  = new ArrayList[N+1];

        for(int i = 0; i <= N; i++) {
            lands[i] = new ArrayList<>();
        }

        int max = 0;

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            lands[a].add(new Node(b,c));
            lands[b].add(new Node(a,c));
            max = Math.max(max,c );

        }

        st = new StringTokenizer(br.readLine());
        start =  Integer.parseInt(st.nextToken());
        destination =  Integer.parseInt(st.nextToken());

        bw.write(binarySearch(max) + "\n");
        bw.flush();
        bw.close();
        br.close();


    }

}

class Node {
    int end;
    int cost;

    Node(int end, int cost) {
        this.cost = cost;
        this.end = end;
    }
}

