package baekjoon.bronzeⅡ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*

문제

어떤 자연수 N이 있을 때, 그 자연수 N의 분해합은 N과 N을 이루는 각 자리수의 합을 의미한다. 
어떤 자연수 M의 분해합이 N인 경우, M을 N의 생성자라 한다. 예를 들어, 245의 분해합은 256(=245+2+4+5)이 된다. 
따라서 245는 256의 생성자가 된다. 물론, 어떤 자연수의 경우에는 생성자가 없을 수도 있다. 반대로, 생성자가 여러 개인 자연수도 있을 수 있다.
자연수 N이 주어졌을 때, N의 가장 작은 생성자를 구해내는 프로그램을 작성하시오.

입력
첫째 줄에 자연수 N(1 ≤ N ≤ 1,000,000)이 주어진다.

출력
첫째 줄에 답을 출력한다. 생성자가 없는 경우에는 0을 출력한다.

216

198

*/


public class Boj2231_분해합 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int num = Integer.parseInt(br.readLine());
        int part;
        int sum;

        // 1부터 그 입력된 숫자까지 각 수와, 수의 각 자리수를 전부 더한 값이 입력값과 같은지 비교함. 

        for (int i = 1; i < num; i++) {
            sum = i; // sum에 i를 넣은 것은, 분해합에서 n을 더하는 것과 같음 
            part = i; // 각 자리수를 구하기 위해 해당 수를 할당. 

            // 어떤 자릿수의 숫자가 들어와도 각 자릿수를 1의 자리부터 더하는 코드 
            while (part != 0) {
                sum += part %10; // %10으로 마지막 자릿수를 구해서 sum과 더한다. 
                part /= 10; // 마지막 자릿수가 빠지고 반복문을 돌게 된다. 
            }

            if (num == sum) {   // 입력된 숫자와 위에서 더한 합이 같다면 
                System.out.println(i);
                return;         // 함수를 빠져 나감. return 자료형이 void형인 메서드만 가능 
            }
        }
        System.out.println("0"); // 생성자가 없는 경우이다. 
        
    }

}