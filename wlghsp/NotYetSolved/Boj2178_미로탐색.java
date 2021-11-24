package baekjoon;

/*
N×M크기의 배열로 표현되는 미로가 있다.
미로에서 1은 이동할 수 있는 칸을 나타내고, 0은 이동할 수 없는 칸을 나타낸다. 이러한 미로가 주어졌을 때, 
(1, 1)에서 출발하여 (N, M)의 위치로 이동할 때 지나야 하는 최소의 칸 수를 구하는 프로그램을 작성하시오. 
한 칸에서 다른 칸으로 이동할 때, 서로 인접한 칸으로만 이동할 수 있다.
위의 예에서는 15칸을 지나야 (N, M)의 위치로 이동할 수 있다. 칸을 셀 때에는 시작 위치와 도착 위치도 포함한다.


입력
첫째 줄에 두 정수 N, M(2 ≤ N, M ≤ 100)이 주어진다. 다음 N개의 줄에는 M개의 정수로 미로가 주어진다. 각각의 수들은 붙어서 입력으로 주어진다.

출력
첫째 줄에 지나야 하는 최소의 칸 수를 출력한다. 항상 도착위치로 이동할 수 있는 경우만 입력으로 주어진다.

4 6
101111
101010
101011
111011

15

4 6
110110
110110
111111
111101

9

*/

// *** 요약 *** 
// 1은 이동 가능 칸, 0은 이동할 수 없는 칸
// (1,1)에서 추울발~~ 하여 (N,M) 의 위치로 이동할 때 최소 칸의 수 구하기 
// 칸을 셀 때는 시작위치와 도착위치도 포함 
// 첫째 줄 두 정수 N,M이 주어지고, 다음 N개의 줄에 M개의 정수로 미로가 주어짐 , 수들은 붙어서 입력됨
// 최소 칸의 수 출력, 도착위치로 이동 가능한 경우만 입력으로 주어짐 


import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.IOException;


public class Boj2178_미로탐색 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] arr = new int[n][m];
        
    }
}
