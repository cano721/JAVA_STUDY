package baekjoon.silverⅠ;

/*
상근이와 선영이가 다른 사람들이 남매간의 대화를 듣는 것을 방지하기 위해서 대화를 서로 암호화 하기로 했다. 그래서 다음과 같은 대화를 했다.

    상근: 그냥 간단히 암호화 하자. A를 1이라고 하고, B는 2로, 그리고 Z는 26으로 하는거야.
    선영: 그럼 안돼. 만약, "BEAN"을 암호화하면 25114가 나오는데, 이걸 다시 글자로 바꾸는 방법은 여러 가지가 있어.
    상근: 그렇네. 25114를 다시 영어로 바꾸면, "BEAAD", "YAAD", "YAN", "YKD", "BEKD", "BEAN" 총 6가지가 나오는데, BEAN이 맞는 단어라는건 쉽게 알수 있잖아?
    선영: 예가 적절하지 않았네 ㅠㅠ 만약 내가 500자리 글자를 암호화 했다고 해봐. 그 때는 나올 수 있는 해석이 정말 많은데, 그걸 언제 다해봐?
    상근: 얼마나 많은데?
    선영: 구해보자!

어떤 암호가 주어졌을 때, 그 암호의 해석이 몇 가지가 나올 수 있는지 구하는 프로그램을 작성하시오.

입력
첫째 줄에 5000자리 이하의 암호가 주어진다. 암호는 숫자로 이루어져 있다.

출력
나올 수 있는 해석의 가짓수를 구하시오. 정답이 매우 클 수 있으므로, 1000000으로 나눈 나머지를 출력한다.

암호가 잘못되어 암호를 해석할 수 없는 경우에는 0을 출력한다.

25114
`
6

1111111111

89



문제를 꼼꼼히 읽자! ‘암호를 해석할 수 없는 경우에는 0을 출력한다.’ 에서 암호를 해석할 수 없는 경우는 입력값이 0으로 시작하거나 마지막값이 0인데 그 앞에 값이 1이나 2가 아닐 경우이다.
왜냐하면 10, 20은 알파벳이 있으나 30, 40, …은 알파벳이 없기 때문이다. 이부분을 if문으로 예외처리를 해줬다.
i번째 숫자까지의 경우의 수 dp[i]는 한자리 수일 때 이전값 dp[i-1]과 경우의 수가 동일하다.
\1일 때 해석할 수 있는 경우의 수는 1이고, 1과 3일 때 역시 1가지 이기 때문이다.
하지만, 만약 i번째 자리 수가 i-1번째 자리 수와 합쳐서 10이상 26 이하의 값이 되면, 경우의 수가 늘어난다.
입력값이 1,2일 때 1/2로 해석할 수도 있지만 12로 해석할 수도 있기 때문이다.
따라서 i번째와 i-1번째 수를 합치면 바로 앞 단계가 dp[i-2]가 되므로 i-2번째 수에 십의 자리 수를 하나 붙인 것과 같은 과정이다.
dp[i] = dp[i] (한 자리 수라고 가정했을 때의 경우의 수) + dp[i-2]

 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj2011_암호코드 {

    // https://geehye.github.io/baekjoon-2011/ 참조함.
    public static void main(String[] args) throws IOException {
        String str = new BufferedReader(new InputStreamReader(System.in)).readLine();
        int length = str.length();
        long[] dp = new long[length + 1];
        dp[0] = dp[1] = 1;

        if (str.charAt(0) == '0')
            System.out.print(0);
        else if (str.charAt(length - 1) == '0' && str.charAt(length - 2) != '1' && str.charAt(length - 2) != '2')
            System.out.print(0);
        else {
            for (int i = 2; i <= length; i++) {
                int tmp = Integer.parseInt(str.charAt(i - 1) + "");
                if (tmp > 0)
                    dp[i] = dp[i - 1] % 1000000;

                tmp += Integer.parseInt(str.charAt(i - 2) + "") * 10;
                if (10 <= tmp && tmp <= 26)
                    dp[i] = (dp[i] + dp[i - 2]) % 1000000;
            }
            System.out.print(dp[length]);
        }
    }
}
