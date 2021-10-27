package baekjoon.silverⅡ;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/*
세 개의 장대가 있고 첫 번째 장대에는 반경이 서로 다른 n개의 원판이 쌓여 있다. 각 원판은 반경이 큰 순서대로 쌓여있다. 이제 수도승들이 다음 규칙에 따라 첫 번째 장대에서 세 번째 장대로 옮기려 한다.

    한 번에 한 개의 원판만을 다른 탑으로 옮길 수 있다.
    쌓아 놓은 원판은 항상 위의 것이 아래의 것보다 작아야 한다.

이 작업을 수행하는데 필요한 이동 순서를 출력하는 프로그램을 작성하라. 단, 이동 횟수는 최소가 되어야 한다.

아래 그림은 원판이 5개인 경우의 예시이다.


입력
첫째 줄에 첫 번째 장대에 쌓인 원판의 개수 N (1 ≤ N ≤ 20)이 주어진다.

출력
첫째 줄에 옮긴 횟수 K를 출력한다.
두 번째 줄부터 수행 과정을 출력한다. 두 번째 줄부터 K개의 줄에 걸쳐 두 정수 A B를 빈칸을 사이에 두고 출력하는데, 
이는 A번째 탑의 가장 위에 있는 원판을 B번째 탑의 가장 위로 옮긴다는 뜻이다.

3

7
1 3
1 2
3 2
1 3
2 1
2 3
1 3
*/

public class Boj11729_하노이탑이동순서  {
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        bw.write((((int)Math.pow(2, n))-1) + "\n");
        hanoi(n,1,2,3);
        bw.write(sb + "\n");
        bw.flush();
        bw.close();

    }

    static void hanoi(int n, int from, int aux, int to) {
        if (n == 1) {
            sb.append(from + " " + to).append("\n");
            return;           
        }
        hanoi(n-1, from, to, aux);
        sb.append(from + " " + to).append("\n");
        hanoi(n-1, aux, from, to);
    }
}