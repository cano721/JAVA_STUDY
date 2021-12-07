package cindya.bj1753_최단경로;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

class Connect{
    int destination, weight;

    public Connect(int destination, int weight) {
        this.destination = destination;
        this.weight = weight;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int v = Integer.parseInt(st.nextToken(" ")), e = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(br.readLine());
        List<Connect>[] map = new List[v + 1];
        int[] distance = new int[v + 1];
        boolean[] check = new boolean[v + 1];

        for(int i = 0; i <= v; i++)
            map[i] = new ArrayList<>();

        for(int i = 0; i <= v; i++){
            st = new StringTokenizer(br.readLine());
            map[Integer.parseInt(st.nextToken(" "))].add(new Connect(Integer.parseInt(st.nextToken(" ")), Integer.parseInt(st.nextToken())));
        }
        br.close();


    }
}
