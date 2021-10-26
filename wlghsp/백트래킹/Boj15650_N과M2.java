package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*

문제
자연수 N과 M이 주어졌을 때, 아래 조건을 만족하는 길이가 M인 수열을 모두 구하는 프로그램을 작성하시오.

    1부터 N까지 자연수 중에서 중복 없이 M개를 고른 수열
    고른 수열은 오름차순이어야 한다.

입력
첫째 줄에 자연수 N과 M이 주어진다. (1 ≤ M ≤ N ≤ 8)

출력
한 줄에 하나씩 문제의 조건을 만족하는 수열을 출력한다. 중복되는 수열을 여러 번 출력하면 안되며, 각 수열은 공백으로 구분해서 출력해야 한다. 수열은 사전 순으로 증가하는 순서로 출력해야 한다.

3 1

1
2
3

4 2

1 2
1 3
1 4
2 3
2 4
3 4

*/


public class Boj15650_N과M2 {

    static int N, M;
    static int[] arr;
    

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[M];

        dfs(1, 0);

    }
    

    // dfs 깊이 우선 탐색 
    // **** jeeyani  님 소스와 주석 참고함. ******
    static void dfs(int start, int loc) {

        //m 갯수에 맞게 배열을 모두 채웠으면 출력
        if (loc == M) {
            for (int val : arr) {
                System.out.print(val +" ");
            }
            System.out.println();
            return;
        } 

        //시작값을 정해줌으로써 visited처리를 확인하지 않아도 가능!
        for (int i = start; i <= N; i++) {
            arr[loc] = i;
            dfs(i + 1, loc + 1);
        }
    }
}
