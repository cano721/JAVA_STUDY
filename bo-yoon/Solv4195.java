import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

public class Solv4195 {
    static int T, F;
    static int level[];
    static int graph[];

    static int find(int x) {
        if (graph[x] == x) return x;
        else return graph[x] = find(graph[x]);
    }

    static int union(int a, int b){
        a = find(a);
        b = find(b);
        if(a != b)  {
            graph[b] = a;
            level[a] += level[b];
            level[b] = 1;
        }
        return level[a];
    }



    public static void main(String[] args)  throws NoSuchElementException, NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        StringBuilder ans = new StringBuilder();
        T = Integer.parseInt(br.readLine());




        while (T --> 0) {
            F = Integer.parseInt(br.readLine());
            graph = new int[F*2 +1];
            level = new int[F*2+1];
            Map<String,Integer> map = new HashMap<>();

            for(int i = 1; i < F*2 +1 ; i++){
                graph[i] = i;
                level[i] = 1;
            }
            int idx = 1;

            for(int i = 0; i < F; i++) {
                st = new StringTokenizer(br.readLine());
                String a = st.nextToken();

                String b = st.nextToken();

                if(!map.containsKey(a)) {
                    map.put(a,idx++);
                }
                if(!map.containsKey(b)) {
                    map.put(b,idx++);
                }

                ans.append(union(map.get(a), map.get(b)) + "\n");

            }

        }

        bw.write(ans.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}

