package day220404;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2293_동전1 {

    static int dp[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        dp = new int[K+1];
        dp[0] = 1;
        for (int i = 0; i < N; i++) {
            int won = Integer.parseInt(br.readLine());
            for (int j = won; j <= K; j++) {
                dp[j] = dp[j-won] + dp[j];
            }
        }

        System.out.println(dp[K]);

        //f(n) : n원을 만들 때 만들 수 있는 경우의 수
        //접근방법 : 1, 2, 5 원일때, 먼저 dp[0]에 1을 채운다. 테이블을 채우기 위해 필요하기 때문..
        //이후 1원만을 이용해 n원을 만들때의 경우의 수는, 1원을 이용하거나(f(n-1)) 1원을 이용하지 않거나(f(n))를 고려해서 채운다.
        //위의 방법이 가능한 이유는, f(n)에는 아직 1원을 이용하지 않은 경우의 수가 입력되어있기 때문이라 그렇다.
        //초기에는 아무 동전도 이용하지 않았기 때문에, f(1)은 0이 들어가있을 것이다. 그렇기 때문에 f(0) + f(1)은 1이 될것이다. 이게 성립하려면 f(0)을 1로 채워야 하는것이다.
        //(사실 그래야 하는 이유가 납득이 잘 되진 않지만 이래서 dp테이블이 정삭적으로 채워짐...)
        //아무튼 그 다음으로 2원을 사용해 k원까지 채워본다.(아직 5원은 사용해보지 않았다.)
        //그러면 i원을 채운다 했을 때, 아직 f(i)원은 1원만 사용해 만들은 경우의 수이고 f(i-2)원이 i원을 2원으로 채운 경우의 수랑 같으므로 (1 2),(1 1 1) -> (1 2 2),(1 1 1 2)
        //f(i) = f(i) + f(i-2)가 되는것이다.
        //이것을 5원까지 차례로 진행하게되면 1차원 dp배열이 완성되고 f(k)가 답이 된다.
    }
}
