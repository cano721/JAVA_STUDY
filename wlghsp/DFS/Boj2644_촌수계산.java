package baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
우리 나라는 가족 혹은 친척들 사이의 관계를 촌수라는 단위로 표현하는 독특한 문화를 가지고 있다. 
이러한 촌수는 다음과 같은 방식으로 계산된다. 
기본적으로 부모와 자식 사이를 1촌으로 정의하고 이로부터 사람들 간의 촌수를 계산한다. 
예를 들면 나와 아버지, 아버지와 할아버지는 각각 1촌으로 나와 할아버지는 2촌이 되고, 
아버지 형제들과 할아버지는 1촌, 나와 아버지 형제들과는 3촌이 된다.
여러 사람들에 대한 부모 자식들 간의 관계가 주어졌을 때, 주어진 두 사람의 촌수를 계산하는 프로그램을 작성하시오.

입력
사람들은 1, 2, 3, …, n (1 ≤ n ≤ 100)의 연속된 번호로 각각 표시된다. 입력 파일의 첫째 줄에는 전체 사람의 수 n이 주어지고, 
둘째 줄에는 촌수를 계산해야 하는 서로 다른 두 사람의 번호가 주어진다. 
그리고 셋째 줄에는 부모 자식들 간의 관계의 개수 m이 주어진다. 
넷째 줄부터는 부모 자식간의 관계를 나타내는 두 번호 x,y가 각 줄에 나온다. 
이때 앞에 나오는 번호 x는 뒤에 나오는 정수 y의 부모 번호를 나타낸다.
각 사람의 부모는 최대 한 명만 주어진다.

출력
입력에서 요구한 두 사람의 촌수를 나타내는 정수를 출력한다. 
어떤 경우에는 두 사람의 친척 관계가 전혀 없어 촌수를 계산할 수 없을 때가 있다. 
이때에는 -1을 출력해야 한다.

9
7 3
7
1 2
1 3
2 7
2 8
2 9
4 5
4 6

3


*/

public class Boj2644_촌수계산 {
    static int ans = -1;

    // 인접 리스트와 DFS 사용
    // 오늘도 https://steady-coding.tistory.com/61 참조하였습니다.
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine()); // 전체 사람의 수
        StringTokenizer st = new StringTokenizer(br.readLine());
        int findX = Integer.parseInt(st.nextToken()); // 서로 다른 두 사람 findX,finxY의 번호
        int finxY = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(br.readLine()); // 관계의 갯수
        ArrayList<ArrayList<Integer>> arr = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            arr.add(new ArrayList<>()); // 빈 배열들을 담음
        }

        // 양방향 인접리스트
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            arr.get(x).add(y); // x에 y값들을 집어넣고
            arr.get(y).add(x); // 반대로 y에 x값도 넣어준다.
        }

        boolean[] visited = new boolean[n + 1];
        DFS(arr, visited, findX, finxY, 0);

        bw.write(ans + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    public static void DFS(ArrayList<ArrayList<Integer>> arr, boolean[] visited, int pos, int find, int cnt) {
        visited[pos] = true;
        for (int i : arr.get(pos)) {
            if (!visited[i]) {
                if (i == find) {
                    ans = cnt + 1;
                    return;
                }
                DFS(arr, visited, i, find, cnt + 1);
            }
        }
    }

}
