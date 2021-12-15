package algo.study.sol1976;

import java.io.*;
import java.util.StringTokenizer;

// 유니온 파인드
public class Solv1976 {
    static int N, M;
    static int graph[];


    static int findP(int num) {
        if ( graph[num] == num) {
            return num;
        }
        return graph[num] = findP(graph[num]);
    }

    static void union(int x, int y) {
        x = findP(x);
        y = findP(y);
        if ( x == y)
            return;
        if(x < y){
           graph[y] = x;
        } else {
           graph[x] = y;
        }

    }


    public static void main(String[] args) throws  NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = null;



        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());


        graph = new int[N+1];

        for(int i = 1 ; i <= N ; i++) {
            st = new StringTokenizer(br.readLine());
            graph[i] = i;
            for(int j = 1 ; j <= N ; j++) {
                int connection = Integer.parseInt(st.nextToken());
                if( connection == 1) {
                        union(i,j);
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        int start =  findP(Integer.parseInt(st.nextToken()));
        for( int i =1 ; i < M ; i++) {

            int curPos = Integer.parseInt(st.nextToken());

            if (start != findP(curPos)) {
                bw.write("NO\n");
                bw.flush();
                bw.close();
                br.close();
                return;
            }
        }

        bw.write("YES\n");
        bw.flush();
        bw.close();
        br.close();

    }
}

