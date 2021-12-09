ackage argo.study.sol11677;


import java.io.*;
import java.util.StringTokenizer;

public class Solv11657 {
    static int N, M;
    static Bus[] bus;
    static long[] dist;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder res = new StringBuilder();


        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        bus = new Bus[M];
        dist = new long[N + 1];

        for (int i = 1; i <= N; i++) {
            dist[i] = Integer.MAX_VALUE;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            bus[i] = new Bus(a, b, c);
        }

        if (bellmanFord(1)) {
            res.append("-1\n");
        } else {
            for (int i = 2; i <= N; i++) {
                if (dist[i] == Integer.MAX_VALUE) {
                    res.append("-1\n");
                } else {
                    res.append(dist[i] + "\n");
                }
            }
        }
        bw.write(res.toString());
        bw.flush();
        bw.close();
        br.close();

    }

    static boolean bellmanFord(int start) {
        dist[start] = 0;

        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < M; j++) {
                int curPos = bus[j].start;
                int nextPos = bus[j].end;
                int cost = bus[j].time;

                if (dist[curPos] == Integer.MAX_VALUE) continue;
                if (dist[nextPos] > dist[curPos] + cost) {
                    dist[nextPos] = dist[curPos] + cost;

                    if (i == N) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

}

class Bus {
    int start, end;
    int time;

    public Bus(int start, int end, int time) {
        this.start = start;
        this.end = end;
        this.time = time;
    }

}

