package algo.study.mst4386;




import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;


class Star {
    int order;
    double x;
    double y;

    public Star(int order, double x, double y) {
        this.order = order;
        this.x = x;
        this.y = y;
    }
}

class StarDistance implements Comparable<StarDistance> {
    int startStar;
    int endStar;
    double distance;

    public StarDistance(int start, int end, double dist) {
        this.startStar = start;
        this.endStar = end;
        this.distance = dist;
    }

    @Override
    public int compareTo(StarDistance o) {

        if (distance < o.distance) {
            return -1;
        }
        return 1;

    }
}


public class Solv4386 {
    static int N;
    static final int INF = 100 + 1;
    static ArrayList<Star> list;
    static ArrayList<StarDistance> calList;
    static int graph[];
    static double ans =0;

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
        StringTokenizer st;


        list = new ArrayList<>();
        calList = new ArrayList<>();
        N = Integer.parseInt(br.readLine());

        // input
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            double a = Double.parseDouble(st.nextToken());
            double b = Double.parseDouble(st.nextToken());

            list.add(new Star(i, a, b));
        }



        // calculate
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = i + 1; j < list.size(); j++) {
                double tmp = Math.pow(list.get(j).x - list.get(i).x, 2);
                double tmp2 = Math.pow(list.get(j).y - list.get(i).y, 2);
                double resTmp = Math.sqrt(tmp + tmp2);
                calList.add(new StarDistance(list.get(i).order, list.get(j).order, resTmp));
            }
        }
        Collections.sort(calList);


        // fill graph
        graph = new int[INF];
        for(int i = 1 ; i <= N; i++) {
            graph[i] = i;
        }


        // union & find

        for(int i = 0 ; i < calList.size() ; i++) {
            if(!findUnion(calList.get(i).startStar, calList.get(i).endStar)) {

                ans += calList.get(i).distance;
                union(calList.get(i).startStar, calList.get(i).endStar);

            }
        }

        bw.write(String.format("%.2f", ans) + "\n");
        bw.flush();
        bw.close();
        br.close();


    }
}

