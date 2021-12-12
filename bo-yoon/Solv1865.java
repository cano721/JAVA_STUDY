package argo.study.sol1865;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

class Road {
    int start, end, time;
    Road(int start, int end, int time) {
        this.start = start;
        this.end = end;
        this.time = time;
    }
}

public class Solv1865 {
    static int TC;
    static int N, M, W;
    static int[] dist;
    static ArrayList<Road> arr;
    static final int INF = Integer.MAX_VALUE;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder res = new StringBuilder();



        TC = Integer.parseInt(st.nextToken());
        while (TC-- > 0) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());

            arr = new ArrayList<>();
            // 도로의 개수
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int S = Integer.parseInt(st.nextToken());
                int E = Integer.parseInt(st.nextToken());
                int T = Integer.parseInt(st.nextToken());

                arr.add(new Road(S,E,T));
                arr.add(new Road(E,S,T));
            }

            // 웜홀 입력
            for( int i =0; i< W ; i++) {
                st = new StringTokenizer(br.readLine());
                int S = Integer.parseInt(st.nextToken());
                int E = Integer.parseInt(st.nextToken());
                int T = Integer.parseInt(st.nextToken());

                arr.add(new Road(S,E, -1 * T));
            }

            if ( bellmanFord() ) {
                res.append("YES" + "\n");
            } else {
                res.append("NO" + "\n");
            }

        }

        bw.write(res + "\n");
        bw.flush();
        bw.close();
        br.close();
    }


    public static boolean bellmanFord(){
        dist = new int[N + 1];
        Arrays.fill(dist,INF);
        dist[1] = 0;


        for (int i = 0; i < N; i++) {
            for ( Road road : arr) {
                if( dist[road.start] != INF && dist[road.end] > dist[road.start] + road.time) {
                    dist[road.end] = dist[road.start] + road.time;
                }
            }
        }

        for (Road road: arr) {
            if (dist[road.start] != INF && dist[road.start] + road.time < dist[road.end])
                return true;
        }

        return false;

    }


}

