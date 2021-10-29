package baekjoon;

import java.util.Scanner;

/*
브루트 포스 

상근이는 어렸을 적에 "봄보니 (Bomboni)" 게임을 즐겨했다.
가장 처음에 N×N크기에 사탕을 채워 놓는다. 사탕의 색은 모두 같지 않을 수도 있다. 상근이는 사탕의 색이 다른 인접한 두 칸을 고른다. 
그 다음 고른 칸에 들어있는 사탕을 서로 교환한다. 
이제, 모두 같은 색으로 이루어져 있는 가장 긴 연속 부분(행 또는 열)을 고른 다음 그 사탕을 모두 먹는다.
사탕이 채워진 상태가 주어졌을 때, 상근이가 먹을 수 있는 사탕의 최대 개수를 구하는 프로그램을 작성하시오.


입력
첫째 줄에 보드의 크기 N이 주어진다. (3 ≤ N ≤ 50)
다음 N개 줄에는 보드에 채워져 있는 사탕의 색상이 주어진다. 빨간색은 C, 파란색은 P, 초록색은 Z, 노란색은 Y로 주어진다.
사탕의 색이 다른 인접한 두 칸이 존재하는 입력만 주어진다.

출력
첫째 줄에 상근이가 먹을 수 있는 사탕의 최대 개수를 출력한다.

3
CCP
CCP
PPC

3

4
PPPP
CYZY
CCPY
PPCC

4


*/


public class Boj3085_사탕게임 {
    public static char[][] board; //맵 크기
    public static int N; //맵 크기
    public static int max =0; // 먹을수 있는 최대 사탕의 개수
    
    

   
public static void main(String[] args) {
    
    Scanner scan = new Scanner(System.in);
    N = scan.nextInt();// 가로세로 길이가 N 인 사탕판 입력 
    board = new char[N][N]; // 정사각형 모양이므로 가로세로 길이 동일
    
    // N*N 사탕입력 받기 
    for (int i = 0 ; i < N ; i++) {
        String str = scan.next(); 
        for(int j =0; j <board[i].length;j++) {
            board[i][j] =str.charAt(j);
        }
    }
        
         // 가로로 인접한 두 사탕 교환하고 최대 사탕 개수 찾고 원위치 
        for (int i = 0 ; i < N;i++) {
            for (int j = 0 ; j <N-1 ;j++) {
            // 가로로 인접한 두 문자 교환 - 가로로 인접한 모든 두 문자들 
            //swap(board[i][j], board[i][j+1]
            char temp = board[i][j];
            board[i][j]= board[i][j+1];
            board[i][j+1]= temp;
            
            //한번 두 문자의 자리를 바꾸는 경우마다 가로세로 체크 
            // 한 페이스마다 가로세로 체크 
            // 이 메소드를 여기로 불러와 적용하는것 !!!! (중요 ) 가로의 두 문자의 위치를 바꾸었어도  반드시 가로 세로 모두  반복되는 문자의 개수를 체크해야함
            //(가로줄의 문자 순서 바꾸었을때도 세로줄 개수세는것에 영향주기때문에 )
            arrCheck(); 
            // 이전에 교환한 문자 복구  // 다음 페이스에서 또 이어서 해야하기 때문에 
            
            temp = board[i][j];
            board[i][j] = board[i][j+1];
            board[i][j+1]= temp;
            
            }
        }
    
        // 세로로 인접한 두 사탕 교환하고 최대 사탕 개수 찾고 원위치 
        for(int i  = 0 ; i < N ; i++) {
            for (int j = 0 ; j < N-1 ;j++) {
                // swap(board[j][i],board[j+1][i]);
                char temp = board[j][i];
                board[j][i]= board[j+1][i];
                board[j+1][i] =temp;
                // 가로 세로 체크 
                arrCheck();
                
                // 이전에 교환한 문자 복구 
                
                temp = board[j][i];
                board[j][i]= board[j+1][i];
                board[j+1][i]= temp;
            }
        }
        System.out.println(max); // 가로 세로 두 경우에서  max 구해서 출력함 
        scan.close();
}

 // 가로로 세로로 비교하면서 먹을수 있는 사탕의 최대 갯수 찾기 
 public static void arrCheck() { // 두문자가 바뀌는 모든 경우의 수마다 각각의 사탕판을 체크함 
    // -> 가로로 체크 
    for(int i = 0 ; i < N ; i++) {
        int count = 1; 
        for (int j = 0 ; j < N-1 ; j++) {
            // 이전 사탕과 동일한 경우 - > 계속 만든다 
            if (board[i][j]==board[i][j+1])
                count++;
            // 이전과 다른 사탕인 경우 - > 새로 먹어야하므로 1로 초기화 
            //왜 1로 초기화 하는거지 ? 
            // 하나부터 세기 시작하니까 ~~
            else 
                count = 1;
            // 사탕 최대 개수 저장 
            // Math.max(a,b) : a와 b중 더 큰수를 반환함 
            // max는 처음에는 0 이지만 i=0일때 세트의 count는 1이상 이기 때문에 max<count이므로
            //max에는 count 값이 대입되고 이 값을 지닌채로 i=1페이스에서 이떄의 count값과 max의 값을 서로 비교함 
            // i가 N-1 인 페이스까지 이 과정이 반복되고 결국 max에는 최대값이 들어가게된다 
            max = Math.max(max, count);
        }
    }
    
    // 세로로 체크 
    for (int i = 0 ; i < N ; i++) {
        int count = 1;
        for (int j = 0 ; j < N-1 ; j++) {
            if (board[j][i]==board[j+1][i])
                count++;
            else count=1;
            max = Math.max(max, count);
        }
    }
}
}