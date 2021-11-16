package baekjoon;

/*

10,000 이하의 자연수로 이루어진 길이 N짜리 수열이 주어진다.
이 수열에서 연속된 수들의 부분합 중에 그 합이 S 이상이 되는 것 중, 가장 짧은 것의 길이를 구하는 프로그램을 작성하시오.

입력
첫째 줄에 N (10 ≤ N < 100,000)과 S (0 < S ≤ 100,000,000)가 주어진다. 둘째 줄에는 수열이 주어진다. 수열의 각 원소는 공백으로 구분되어져 있으며, 10,000이하의 자연수이다.

출력
첫째 줄에 구하고자 하는 최소의 길이를 출력한다. 만일 그러한 합을 만드는 것이 불가능하다면 0을 출력하면 된다.

10 15
5 1 3 5 10 7 4 9 2 8

2

자세한 설명 : https://wellohorld.tistory.com/28

정렬해야하는 줄 알았는데 정렬도 필요없네.. 

*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj1806_부분합 {

    static int N, S;
    static int[] arr;
    // 투포인터
    // 두 개의 포인터를 같은 위치인 0에서 시작
    // second를 증가시키면서 부분합을 구함. second가 배열의 마지막에 도달하면 종료
    // 숫자들의 합과 크거나 같을 경우는 first를 증가시키면서 합에서 빼주고, 길이의 최소값여부를 확인한다. 
    // 숫자들의 합이 S 보다 작은 경우는 second를 증가시켜 다음 인덱스의 수를 sum에 더해준다. 
    static int solution() {
        int first = 0;
        int second = 0;
        int sum = 0;
        int ans = 100001;
        while (true) {
            if (sum >= S) { // 문제의 조건: S 이상! 
                sum -= arr[first++];
                ans = Math.min(ans, (second-first) + 1);
            } 
            else if (second == N) break; // first를 오른쪽으로 이동이 아직 가능하지만, 부분합은 더 작아질 뿐 커지지는 않으므로 반복을 벗어나준다. 
            else sum += arr[second++];
        }
        return ans == 100001 ? 0 : ans;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        System.out.println(solution());
    }
   
}