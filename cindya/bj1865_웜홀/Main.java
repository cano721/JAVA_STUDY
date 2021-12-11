package cindya.bj1865_웜홀;

import java.io.*;
import java.util.*;

class Connect{
    int destination, time;

    public Connect(int destination, int time) {
        this.destination = destination;
        this.time = time;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int tc = Integer.parseInt(br.readLine());

        while (tc-- > 0){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken(" ")), m = Integer.parseInt(st.nextToken(" ")), w = Integer.parseInt(st.nextToken());
            List<Connect>[] connects = new List[n + 1];
            Queue<Connect> q = new LinkedList<>();
            int[] times = new int[n + 1], cntVisit = new int[n + 1];
            boolean[] checkQ = new boolean[n + 1];

            Arrays.fill(times, Integer.MAX_VALUE);
            for(int i = 0; i <= n; i++)
                connects[i] = new ArrayList<>();

            for(int i = 0; i < m; i++){
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken(" ")), e = Integer.parseInt(st.nextToken(" ")), t = Integer.parseInt(st.nextToken());
                connects[s].add(new Connect(e, t));
                connects[e].add(new Connect(s, t));
            }

            for(int i = 0; i < w; i++){
                st = new StringTokenizer(br.readLine());
                connects[Integer.parseInt(st.nextToken(" "))].add(new Connect(Integer.parseInt(st.nextToken(" ")), (Integer.parseInt(st.nextToken()) * -1)));
            }

            for(int i = 1; i <= n && !checkQ[0]; i++) {
                times[i] = 0;
                q.offer(new Connect(i, times[i]));

                WhileLoop:
                while (!q.isEmpty()) {
                    Connect connect = q.poll();
                    checkQ[connect.destination] = false;

                    for (Connect nc : connects[connect.destination]) {
                        if (times[nc.destination] > times[connect.destination] + nc.time) {
                            times[nc.destination] = times[connect.destination] + nc.time;
                            if (!checkQ[nc.destination]) {
                                cntVisit[nc.destination]++;
                                if (cntVisit[nc.destination] == n) {
                                    checkQ[0] = true;
                                    break WhileLoop;
                                }
                                checkQ[nc.destination] = true;
                                q.offer(new Connect(nc.destination, times[nc.destination]));
                            }
                        }
                    }
                }
            }
            if(checkQ[0]) bw.write("YES\n");
            else bw.write("NO\n");
        }
        br.close();
        bw.close();
    }
}
