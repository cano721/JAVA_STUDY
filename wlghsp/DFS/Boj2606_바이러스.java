package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
신종 바이러스인 웜 바이러스는 네트워크를 통해 전파된다. 한 컴퓨터가 웜 바이러스에 걸리면 그 컴퓨터와 네트워크 상에서 연결되어 있는 모든 컴퓨터는 웜 바이러스에 걸리게 된다.
예를 들어 7대의 컴퓨터가 <그림 1>과 같이 네트워크 상에서 연결되어 있다고 하자. 
1번 컴퓨터가 웜 바이러스에 걸리면 웜 바이러스는 2번과 5번 컴퓨터를 거쳐 3번과 6번 컴퓨터까지 전파되어 2, 3, 5, 6 네 대의 컴퓨터는 웜 바이러스에 걸리게 된다. 
하지만 4번과 7번 컴퓨터는 1번 컴퓨터와 네트워크상에서 연결되어 있지 않기 때문에 영향을 받지 않는다.

어느 날 1번 컴퓨터가 웜 바이러스에 걸렸다. 
컴퓨터의 수와 네트워크 상에서 서로 연결되어 있는 정보가 주어질 때, 1번 컴퓨터를 통해 웜 바이러스에 걸리게 되는 컴퓨터의 수를 출력하는 프로그램을 작성하시오.


입력
첫째 줄에는 컴퓨터의 수가 주어진다. 컴퓨터의 수는 100 이하이고 각 컴퓨터에는 1번 부터 차례대로 번호가 매겨진다. 둘째 줄에는 네트워크 상에서 직접 연결되어 있는 컴퓨터 쌍의 수가 주어진다. 이어서 그 수만큼 한 줄에 한 쌍씩 네트워크 상에서 직접 연결되어 있는 컴퓨터의 번호 쌍이 주어진다.

출력
1번 컴퓨터가 웜 바이러스에 걸렸을 때, 1번 컴퓨터를 통해 웜 바이러스에 걸리게 되는 컴퓨터의 수를 첫째 줄에 출력한다.

7
6
1 2
2 3
1 5
5 2
5 6
4 7

4

*/


public class Boj2606_바이러스  {

    static ArrayList<Integer>[] a;
    static boolean visit[];     // 정점 탐색여부 체크
    static int n, m, v;     // 정점, 간선, 시작 정점
    static int count = 0;   // 정점과 연결된 바이러스 걸리는 컴퓨터 수


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());    //컴퓨터 수(정점)
        m = Integer.parseInt(br.readLine());    //연결된 컴퓨터 쌍의 수(간선)
        v = 1; // 탐색 시작할 정점의 번호
        a = new ArrayList[n+1]; // 인덱스 편의상 n+1 설정, 0번째 요소는 사용 X
        visit = new boolean[n+1]; // 인덱스 편의상 n+1 설정, 0번째 요소는 사용 X
        for (int i = 0; i <= n; i++) {
            a[i] = new ArrayList<Integer>();
        }

        StringTokenizer st;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int u = Integer.parseInt(st.nextToken());   // 간선으로 이어진 정점1
            int v = Integer.parseInt(st.nextToken());   // 정점1과 간선으로 이어진 정점2
            // 양방향일 경우 양쪽 다 저장해준다.
            a[u].add(v);
            a[v].add(u);
        }



        System.out.println(dfs(v));
        br.close();
    }
    static int dfs(int x) {
        visit[x] = true;
        for (int k : a[x]) {
            if (visit[k] == false) {
                count++; // 감염되는 컴퓨터의 수 증가
                dfs(k); // 깊이 탐색
            }
        }

        return count;
    }
}