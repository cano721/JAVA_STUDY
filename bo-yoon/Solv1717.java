import java.io.*;
import java.util.StringTokenizer;

public class Solv1717 {
    static int N, M;
    static int graph[];

    static int find(int x) {
        if (graph[x] == x) return x;
        else return graph[x] = find(graph[x]);
    }

    static void union(int a, int b){
        a = find(a);
        b = find(b);
        if(a < b) graph[b] = a;
        else graph[a] = b;
    }

    static boolean findUnion(int a, int b) {
        a = find(a);
        b = find(b);
        if(a == b) return true;
        else return false;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new int[N + 1];
        for(int i = 1 ; i <= N; i++) {
            graph[i] = i;
        }

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            if(a == 1) {
                if(findUnion(b,c)) {
                    bw.write("YES" + "\n");
                } else{
                    bw.write("NO" + "\n");
                }
            } else {
              union(b,c);
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }

}

