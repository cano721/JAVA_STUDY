package baekjoon.silverⅠ;
/*

문제

N개의 수로 이루어진 수열 A1, A2, ..., AN이 주어진다. 또, 수와 수 사이에 끼워넣을 수 있는 N-1개의 연산자가 주어진다. 
연산자는 덧셈(+), 뺄셈(-), 곱셈(×), 나눗셈(÷)으로만 이루어져 있다.
우리는 수와 수 사이에 연산자를 하나씩 넣어서, 수식을 하나 만들 수 있다. 이때, 주어진 수의 순서를 바꾸면 안 된다.


예를 들어, 6개의 수로 이루어진 수열이 1, 2, 3, 4, 5, 6이고, 주어진 연산자가 덧셈(+) 2개, 뺄셈(-) 1개, 곱셈(×) 1개, 나눗셈(÷) 1개인 경우에는 총 60가지의 식을 만들 수 있다. 예를 들어, 아래와 같은 식을 만들 수 있다.

    1+2+3-4×5÷6
    1÷2+3+4-5×6
    1+2÷3×4-5+6
    1÷2×3-4+5+6

식의 계산은 연산자 우선 순위를 무시하고 앞에서부터 진행해야 한다. 또, 나눗셈은 정수 나눗셈으로 몫만 취한다. 음수를 양수로 나눌 때는 C++14의 기준을 따른다. 즉, 양수로 바꾼 뒤 몫을 취하고, 그 몫을 음수로 바꾼 것과 같다. 이에 따라서, 위의 식 4개의 결과를 계산해보면 아래와 같다.

    1+2+3-4×5÷6 = 1
    1÷2+3+4-5×6 = 12
    1+2÷3×4-5+6 = 5
    1÷2×3-4+5+6 = 7

N개의 수와 N-1개의 연산자가 주어졌을 때, 만들 수 있는 식의 결과가 최대인 것과 최소인 것을 구하는 프로그램을 작성하시오.

입력
첫째 줄에 수의 개수 N(2 ≤ N ≤ 11)가 주어진다. 둘째 줄에는 A1, A2, ..., AN이 주어진다. (1 ≤ Ai ≤ 100) 셋째 줄에는 합이 N-1인 4개의 정수가 주어지는데, 차례대로 덧셈(+)의 개수, 뺄셈(-)의 개수, 곱셈(×)의 개수, 나눗셈(÷)의 개수이다. 

출력
첫째 줄에 만들 수 있는 식의 결과의 최댓값을, 둘째 줄에는 최솟값을 출력한다. 연산자를 어떻게 끼워넣어도 항상 -10억보다 크거나 같고, 10억보다 작거나 같은 결과가 나오는 입력만 주어진다. 또한, 앞에서부터 계산했을 때, 중간에 계산되는 식의 결과도 항상 -10억보다 크거나 같고, 10억보다 작거나 같다.

2
5 6
0 0 1 0

30
30

3
3 4 5
1 0 1 0

35
17

6
1 2 3 4 5 6
2 1 1 1

54
-24


*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj14888_연산자끼워넣기 {

    static int n; // n개의 숫자 입력
    static int check[], cal[]; // 0: 덧셈, 1:뺄셈, 2:곱셈, 3:나눗셈
    static int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE; // 최댓값과 최솟값을 저장 할 변수


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        check = new int[n]; // 숫자가 저장 될 배열을 할당
        cal = new int[4]; // 연산자가 저장 될 배열을 할당

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            check[i] = Integer.parseInt(st.nextToken());
        }
        StringTokenizer st1 = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < 4; i++) { // 연산자의 갯수는 4개
            cal[i] = Integer.parseInt(st1.nextToken());
        }

        dfs(check[0], 1);   // DFS 호출
        System.out.println(max);
        System.out.println(min);

    }

    static void dfs(int num, int idx){  // 최초 첫 번째 숫자, 1번 인덱스가 전달

        if (idx == n) { // DFS 종료 부, 마지막 인덱스에서 재귀호출을 실행하면 이 부분에서 걸린다.
            if (num>max) { // 최종적인 결과 같이 num값에 저장되므로 최댓값과 최솟값을 찾는다.
                max = num;
            }
            if (num < min) {
                min = num;
            }
            return;
        }
        
        int result = 0; // 한 번의 연산자를 사용한 결과가 저장 될 변수
        for (int j = 0; j < 4; j++) {   // 연산자 배열을 탐색
            if (cal[j] != 0) { // 연산자가 존재하는 경우
                cal[j]--;      // 해당 연산자의 값을 -1 해준다.

                switch (j) {   // 각 연산자에 맞는 케이스로 이동
                    case 0:
                        result = num + check[idx];
                        break;
                    case 1:
                        result = num - check[idx];
                        break;
                    case 2:
                        result = num * check[idx];
                        break;
                    case 3:
                        if (num < 0 && check[idx]>0) {  // 음수를 양수로 나누는 경우에는 
                            num *= -1;  //음수를 양수로 바꾼 뒤
                            result = num / check[idx]; // 몫을 취하고
                            result *= -1;                // 해당 몫을 음수화
                        } else {
                            result = num / check[idx];  // 일반적인 경우에는 몫만 취하게 된다.
                            break;
                        }
                }
                dfs(result, idx+1); // 연산의 결과와 인덱스를 증가시켜 전달
                cal[j]++; // 한 번의 경우를 모두 탐색한 후에는 다시 감소시켰던 연산자 값을 + 1, 백트래킹
            }
        }
        
    }



}
