package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
백트래킹 문제 

상담원으로 일하고 있는 백준이는 퇴사를 하려고 한다.
오늘부터 N+1일째 되는 날 퇴사를 하기 위해서, 남은 N일 동안 최대한 많은 상담을 하려고 한다.
백준이는 비서에게 최대한 많은 상담을 잡으라고 부탁을 했고, 비서는 하루에 하나씩 서로 다른 사람의 상담을 잡아놓았다.
각각의 상담은 상담을 완료하는데 걸리는 기간 Ti와 상담을 했을 때 받을 수 있는 금액 Pi로 이루어져 있다.
N = 7인 경우에 다음과 같은 상담 일정표를 보자.

입력
첫째 줄에 N (1 ≤ N ≤ 15)이 주어진다.
둘째 줄부터 N개의 줄에 Ti와 Pi가 공백으로 구분되어서 주어지며, 1일부터 N일까지 순서대로 주어진다. (1 ≤ Ti ≤ 5, 1 ≤ Pi ≤ 1,000)

출력
첫째 줄에 백준이가 얻을 수 있는 최대 이익을 출력한다.

7
3 10
5 20
1 10
1 20
2 15
4 40
2 200

45

10
1 1
1 2
1 3
1 4
1 5
1 6
1 7
1 8
1 9
1 10

55

*/

/*
남은기간 N일
Ti 상담 완료 기간
Pi 상담 금액

dfs 로 풀이 

*/

public class Boj14501_퇴사 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

    }
}
