import java.util.*;
import java.io.*;
import java.time.temporal.WeekFields;

public class Main {
    static int m, n;
    static int[] root;

    static class Node {
        int from, to, weight;

        public Node(int f, int t, int w) {
            from = f;
            to = t;
            weight = w;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            m = Integer.parseInt(st.nextToken());
            n = Integer.parseInt(st.nextToken());
            if (m == 0 && n == 0) break;
            root = new int[m];
            for (int i = 0; i < m; i++)
                root[i] = i;
            PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.weight - o2.weight);
            int result = 0;
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                pq.add(new Node(a, b, c));
                result += c;
            }

            int cnt = 0;
            while (!pq.isEmpty()) {
                if (cnt == m - 1)
                    break;
                Node poll = pq.poll();
                if (union(poll.from, poll.to)) {
                    result -= poll.weight;
                    cnt++;
                }
            }
            sb.append(result+"\n");
            
        }
        System.out.println(sb);
    }

    static int find(int n) {
        if (root[n] == n)
            return n;
        return root[n] = find(root[n]);
    }

    static boolean union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a == b)
            return false;

        if (a > b)
            root[b] = a;
        else
            root[a] = b;
        return true;
    }

}
