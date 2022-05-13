package studyGroup.may.may12;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.*;

/*

모든 정점을 최소 비용으로 연결 MST(최소 스패닝 트리) 유형
크루스칼 알고리즘

간선의 갯수 : 10만 * (10만 - 1) / 2 = 대략 500억
메모리 초과 발생

필요한 간선만 골라야 한다.
1차원적으로 x,y,z를 오름차순 정렬. 인접해 있는 간선을 구한다.
3 * (10만 - 1) = 30만

간선의 갯수가 적은 경우 크루스칼 알고리즘
간선의 갯수가 많은 경우 정점 위주인 프림 알고리즘


https://steady-coding.tistory.com/117

 */


class Point {
    int num;
    int x;
    int y;
    int z;

    Point(int num, int x, int y, int z)
    {
        this.num = num;
        this.x = x;
        this.y = y;
        this.z = z;
    }
}

class Edge implements Comparable<Edge> {
    int start;
    int end;
    int weight;

    Edge(int start, int end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge o) {
        return weight - o.weight;
    }
}

public class 행성터널2887 {

    static int[] parent;
    static ArrayList<Edge> edgeList;

    public static void main(String[] args) throws NumberFormatException, IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        Point[] points = new Point[n];

        for(int i = 0; i < n; i++)
        {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());

            points[i] = new Point(i, x, y, z);
        }

        edgeList = new ArrayList<>();

        // x, y, z로 정렬해 인접 간선 구하기
        Arrays.sort(points, (p1, p2) -> p1.x - p2.x);
        for(int i = 0; i < n - 1; i++)
        {
            int weight = Math.abs(points[i].x - points[i+1].x);
            edgeList.add(new Edge(points[i].num, points[i + 1].num, weight));
        }

        Arrays.sort(points, Comparator.comparingInt(p -> p.y));
        for(int i = 0; i < n - 1; i++)
        {
            int weight = Math.abs(points[i].y - points[i+1].y);
            edgeList.add(new Edge(points[i].num, points[i + 1].num, weight));
        }

        Arrays.sort(points, Comparator.comparingInt(p -> p.z));
        for(int i = 0; i < n - 1; i++)
        {
            int weight = Math.abs(points[i].z - points[i+1].z);
            edgeList.add(new Edge(points[i].num, points[i + 1].num, weight));
        }

        parent = new int[n];

        for(int i = 0; i < n; i++)
        {
            parent[i] = i;
        }

        Collections.sort(edgeList);

        int answer = 0;

        for(int i = 0; i < edgeList.size(); i++)
        {
            Edge edge = edgeList.get(i);

            if(find(edge.start) != find(edge.end)) {
                answer += edge.weight;
                union(edge.start, edge.end);
            }
        }

        System.out.println(answer);

    }

    public static int find(int x) {
        if (x == parent[x])
        {
            return x;
        }

        return parent[x] = find(parent[x]);
    }

    public static void union(int x, int y)
    {
        x = find(x);
        y = find(y);

        if(x != y) {
            parent[y] = x;
        }
    }






}
