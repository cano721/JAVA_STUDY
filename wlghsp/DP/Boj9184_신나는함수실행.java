package baekjoon;


/*

재귀 호출만 생각하면 신이 난다! 아닌가요?
다음과 같은 재귀함수 w(a, b, c)가 있다.

if a <= 0 or b <= 0 or c <= 0, then w(a, b, c) returns:
    1

if a > 20 or b > 20 or c > 20, then w(a, b, c) returns:
    w(20, 20, 20)

if a < b and b < c, then w(a, b, c) returns:
    w(a, b, c-1) + w(a, b-1, c-1) - w(a, b-1, c)

otherwise it returns:
    w(a-1, b, c) + w(a-1, b-1, c) + w(a-1, b, c-1) - w(a-1, b-1, c-1)

위의 함수를 구현하는 것은 매우 쉽다. 하지만, 그대로 구현하면 값을 구하는데 매우 오랜 시간이 걸린다. (예를 들면, a=15, b=15, c=15)

a, b, c가 주어졌을 때, w(a, b, c)를 출력하는 프로그램을 작성하시오.

입력
입력은 세 정수 a, b, c로 이루어져 있으며, 한 줄에 하나씩 주어진다. 입력의 마지막은 -1 -1 -1로 나타내며, 세 정수가 모두 -1인 경우는 입력의 마지막을 제외하면 없다.

출력
입력으로 주어진 각각의 a, b, c에 대해서, w(a, b, c)를 출력한다.

제한

    -50 ≤ a, b, c ≤ 50


1 1 1
2 2 2
10 4 6
50 50 50
-1 7 18
-1 -1 -1

w(1, 1, 1) = 2
w(2, 2, 2) = 4
w(10, 4, 6) = 523
w(50, 50, 50) = 1048576
w(-1, 7, 18) = 1


*/

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Boj9184_신나는함수실행 {
    // cindya님 코드 참조합니다.

    // Map의 key와 value에 계산결과 저장하고 containsKey로 key를 확인하고 get으로 결과를 반환하는 방법
    private static Map<String, Integer> memory = new HashMap<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        while (true) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            if (a == -1 && b == -1 && c == -1) break; // 입력이 -1 -1 -1 이면 멈춤

            int res = w(a, b, c);
            bw.write(String.format("w(%d, %d, %d) = %d\n", a, b, c, res)); // 출력
        }
        br.close();
        bw.close();

    }

    private static int w(int a, int b, int c) {
        int res;

        String key = String.format("%d %d %d", a, b, c); // a, b, c를 문자열 키로 만듬
        if (memory.containsKey(key)) // 이전에 같은 a b c 연산의 결과가 있으면
            return memory.get(key); // 그 결과를 반환


        if (a <= 0 || b <= 0 || c <= 0)
            res = 1;
        else if (a > 20 || b > 20  || c > 20)
            res = w(20, 20, 20);
        else if (a < b && b < c)
            res = w(a, b, c - 1) + w(a, b -1, c -1 ) - w(a, b-1, c);
        else
            res = w(a-1, b, c) + w(a-1, b-1, c) + w(a-1, b, c-1) - w(a-1, b-1, c-1);

        memory.put(key, res); // 위 연산 결과를 저장

        return res; // 결과 반환
    }
}
