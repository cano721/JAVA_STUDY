/**
 * 1. 트리 dp문제
 * 
 * 2. 두개의 오두막을 골라 가는 길의 다양성의 총 합을 출력
 * 2-1) 예시 1-2-3 이렇게 연결되어 있을때,
 *      1-2 1-3 2-3 오두막을 선택할 수 있고,
 *      각각의 길의 개수는 1 - 2 - 2 개로 총 5이다.
 * 2-2) 내림차순 선택 불가. 2-1
 * 
 * 3. 즉 두개의 오두막을 골라 쓰이는 총 간선의 수를 출력해야한다.
 * 
 * 4. 이걸 간선의 입장으로보면, 각 간선마다 몇번씩 쓰였는지 다 더하면 정답.
 * 
 * 5. 예를 들어 2-6으로 이어지는 간선은 2번을 기준으로 위로 이어지는 간선이다.
 *    2-6으로 이어지는 간선이 쓰인 개수는,
 *    2번을 고르면 나머지 하나는 어떠한 것을 고르든 2-6번은 쓰이게 된다.(정상 오두막을 들려야하므로)
 *    그 뜻은 2번을 안고르면 2-6 간선이 안쓰인다는 뜻이므로,
 *    전체 고를 수 있는 경우의 수 - 2번을 빼고 고른 경우의 수
 *    = 2-6번 간선이 쓰인 총 수가 나온다.
 *    전체 고를 수 있는 경우의 수는 n(오두막수)C2 = n(n-1)/2
 *    2번을 빼고 고른 경우의 수는   n-1C2 = (n-1)(n-2)/2 가 된다.
 * 
 *    하나를 더 봐서, 6-5 간선은
 *    6아래의 서브트리(2,6,8) 중에 어떠한 것을 고르든 쓰인다.
 *    이 외의 것들중에 2개를 골라야 이 간선이 안쓰이므로,
 *    전체 경우의수 - (전체 오두막 수 - 6아래 서브트리)에서 고른 수가 된다.
 * 
 *    루트 정점을 제외한 전체 오두막에서 각 간선별 쓰인 갯수를 다 더하면 정답.
 * 
 * 6. 그럼 이제 서브트리 별 오두막의 수를 구하면 되는데,
 *    각 서브트리 별 오두막의 수는 DP에 저장할 것이다.
 * 
 * 7. 각 서브트리 즉, 이어진 오두막은 DFS를 통해 들릴 것이다.
 *    최종 끝 오두막은 1개이고,
 *    그 위의 오두막들에 그 개수를 더해주면서 최종 정상 오두막까지 구할 것.
 * 
 */

import java.io.*;
import java.util.*;

public class 등산마니아{

    static ArrayList<Integer>[] tree;
    static int[] dp;
    static int n;
    static long result = 0;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());

        tree = new ArrayList[n+1];
        for(int i = 1; i <= n; i++){
            tree[i] = new ArrayList<>();
        }

        dp = new int[n+1];
        
        StringTokenizer st;
        for(int i = 0; i < n-1; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            tree[a].add(b);
            tree[b].add(a);
        }

        dp(1);
        System.out.println(result);
    }

    public static int dp(int now){
        dp[now] = 1;

        for(int next : tree[now]){
            if(dp[next] == 0){
                dp[now] += dp(next);
            }
        }

        //정점을 제외한 각 오두막의 위로 이어진 간선이 쓰인 개수 더하기
        if(now != 1){
            result += comb(n) - comb(n - dp[now]);
        }

        return dp[now];
    }

    public static long comb(int n){
        return (long)n * (long)(n-1)/2;
    }
}