package baekjoon.silverⅡ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*

문제

N개의 정수로 이루어진 수열이 있을 때, 크기가 양수인 부분수열 중에서 그 수열의 원소를 다 더한 값이 S가 되는 경우의 수를 구하는 프로그램을 작성하시오.

입력
첫째 줄에 정수의 개수를 나타내는 N과 정수 S가 주어진다. (1 ≤ N ≤ 20, |S| ≤ 1,000,000) 둘째 줄에 N개의 정수가 빈 칸을 사이에 두고 주어진다. 주어지는 정수의 절댓값은 100,000을 넘지 않는다.

출력
첫째 줄에 합이 S가 되는 부분수열의 개수를 출력한다.

5 0
-7 -3 -2 5 8

1


*/

public class Boj1182_부분수열의합 {

    static int N;  // 정수의 개수
    static int S;  // 부분수열 더한 값 
    static int cnt=0; // 부분수열 더한 값이 S와 같은 부분수열의 갯수 
    static int[] arr; // 정수로 이루어진 수열

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        arr = new int[N];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        dfs(0,0);

        
        System.out.println(cnt);
        
        // S가 0인 경우에는 1이 올라가 있으므로 1을 빼준다. 
        // System.out.println(S==0 ? cnt -1: cnt);
    }

    public static void dfs(int depth, int sum) {
        // 노드의 끝에 도달 
        if (depth == N) {
            // if (sum == S) { 
            //     cnt++;
            // }    
            return;
        }
        if (sum+arr[depth] == S) {   
            cnt++;
        }
        // 트리구조의 분기점을 생각하면 이해가 감. 
        // 현재 깊이의 값을 포함하는 경우
        dfs(depth+1, sum+arr[depth]);
        // 현재 깊이의 값을 포함하지 않는 경우 
        dfs(depth+1, sum);
    }
}
