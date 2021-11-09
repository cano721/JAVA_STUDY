package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

/*
널리 잘 알려진 자료구조 중 최소 힙이 있다. 
최소 힙을 이용하여 다음과 같은 연산을 지원하는 프로그램을 작성하시오.

    배열에 자연수 x를 넣는다.
    배열에서 가장 작은 값을 출력하고, 그 값을 배열에서 제거한다.

프로그램은 처음에 비어있는 배열에서 시작하게 된다.


입력
첫째 줄에 연산의 개수 N(1 ≤ N ≤ 100,000)이 주어진다. 
다음 N개의 줄에는 연산에 대한 정보를 나타내는 정수 x가 주어진다. 
만약 x가 자연수라면 배열에 x라는 값을 넣는(추가하는) 연산이고, 
x가 0이라면 배열에서 가장 작은 값을 출력하고 그 값을 배열에서 제거하는 경우이다. 
x는 231보다 작은 자연수 또는 0이고, 음의 정수는 입력으로 주어지지 않는다.

출력
입력에서 0이 주어진 횟수만큼 답을 출력한다. 
만약 배열이 비어 있는 경우인데 가장 작은 값을 출력하라고 한 경우에는 0을 출력하면 된다.

9
0
12345678
1
2
0
0
0
0
32

0
1
2
12345678
0

*/
public class Boj1927_최소힙 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        // 오름차순 우선순위 큐 (낮은 숫자부터 정렬)
        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.comparingInt(x -> x));
        
        for (int i = 0; i < n; i++) {
            int val = Integer.parseInt(br.readLine()); // 한 줄 씩 입력 숫자 받기 
            if (val == 0) { // 입력값이 0 인 경우 출력
                if (queue.isEmpty()) System.out.println("0"); // 큐가 비어 있으면 0을 출력
                else System.out.println(queue.poll()); // 큐의 첫 번째 값을 반환하고 제거
            } else { // 입력값이 0이 아니면 큐에 값 추가
                queue.add(val); // 큐에 값 추가
            }
        }
    }    
}
