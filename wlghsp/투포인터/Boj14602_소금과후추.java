package baekjoon.silverⅣ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    입력으로 사진을 나타내는 MxN 행렬 A 가 주어진다. 행렬의 각 원소는 사진 속 한 픽셀의 밝기를 나타내는데, 0이면 가장 어두운 것을 의미하고, K 면 가장 밝은 것을 의미한다.
    또 다른 입력으로 정수 W 가 주어진다. W 를 이용해 행렬 B 를 아래와 같이 정의한다.
        B[i][j] = median(A[i+x][j+y]), 
        where 1 ≤ i ≤ M-W+1, 1 ≤ j ≤ N-W+1, 0 ≤ x, y < W
        (A[i+x][j+y]  는 행렬 A 의 i+x 행 j+y열에 위치한 원소, median 은 중앙값*)
    주어진 행렬 A와 정수 W를 이용하여 행렬 B를 구한다.

을은 위의 문제를 컴퓨터 프로그램으로 해결하기 위해 가방에서 노트북을 꺼내 식탁 위에 펼쳤습니다. 
을은 프로그램을 작성하기 시작했습니다. 하지만 잘 되지 않았고 스파게티는 불기 시작했습니다. 
스파게티가 모두 불어 버리기 전에, 을을 위해 위의 문제를 해결하는 프로그램을 작성해주세요!

(중앙값*: 중앙값은 어떤 주어진 값들을 크기의 순서대로 정렬했을 때 가장 중앙에 위치하는 값을 의미합니다. 
예를 들어, 1, 2, 3, 3, 100의 다섯 값이 있을 때, 3이 가장 중앙에 있기 때문에 3이 중앙값입니다.)


입력
첫 번째 줄에는 행렬의 크기를 나타내는 정수 M 과 N(1 ≤ M, N ≤ 30), 최고 밝기 K(1 ≤ K ≤ 10,000), 
정수 W(1 ≤ W ≤ min(M, N))가 공백으로 구분되어 차례로 주어진다. 
(단, W는 홀수) 두 번째 줄부터 1+M 번째 줄까지, 각 줄마다 N 개의 0이상 K 이하 정수가 주어진다. 
입력의 1+i번째 줄의 j번째 정수는 행렬 A 의 i번째 행, j번째 열에 위치한 원소를 의미한다.

출력
M-W+1줄에 걸쳐 정답 행렬 B를 출력한다.


3 3 10 1
1 2 3
4 5 6
7 8 9

1 2 3
4 5 6
7 8 9

3 3 10 3
1 2 3
4 5 6
7 8 9

5

5 5 20 3
5 1 2 8 10
12 10 3 20 7
8 12 19 18 15
17 19 2 5 13
11 2 4 14 16

8 10 10
12 12 13
11 12 14


힌트

예제1에서, w = 1, median(1) = 1, median(2) = 2, …, median(9) = 9 이므로 입력된 행렬과 동일한 행렬이 답입니다.
예제2에서 w = 3 이므로 median(1, 2, 3, 4, 5, 6, 7, 8, 9) = 5가 답입니다.
예제3 정답 행렬의 1행 1열에 위치한 8은 median(5, 1, 2, 12, 10, 3, 8, 12, 19) = 8 연산 과정을 통해 얻어졌습니다.

*/

// paul 님 코드 참조. 
public class Boj14602_소금과후추 {

    static int n, m, k, w;
    static int[][] arr = new int[31][31];
    static int[][] b = new int[31][31];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        StringBuilder sb = new StringBuilder();

    }
}
