import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solv2056 {

    private static int N;
    private static ArrayList<Integer>[] nodeList;
    static int[] edge, time, res;
    static Queue<Integer> q;
    static int maxiam;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        nodeList = new ArrayList[N + 1];

        for (int i = 0; i <= N; i++) {
            nodeList[i] = new ArrayList<>();
        }

        edge = new int[N + 1];
        time = new int[N + 1];
        res = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());

            time[i] = Integer.parseInt(st.nextToken());
            int num = Integer.parseInt(st.nextToken());

            for (int j = 0; j < num; j++) {
                int tmp = Integer.parseInt(st.nextToken());
                nodeList[tmp].add(i);
                edge[i]++;
            }
        }
        q = new LinkedList<>();

        for (int i = 1; i < edge.length; i++) {
            res[i] = time[i];
            if (edge[i] == 0) {
                q.offer(i);
            }
        }


        while (!q.isEmpty()) {
            int p1 = q.poll();
            for (Integer i : nodeList[p1]) {
                edge[i]--;

                res[i] = Math.max(res[i], res[p1] + time[i]);
                if (edge[i] == 0) {
                    q.offer(i);
                }
            }
        }

        maxiam =0;

        for(int i = 1 ;i <= N ; i++) {
            maxiam = Math.max(maxiam, res[i]);
        }

        System.out.println(maxiam);

    }
}

