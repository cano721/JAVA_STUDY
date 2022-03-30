package 전체유형문제풀이;

import java.util.*;

/*
 * <완전탐색>
 * 백트래킹  - 미니맥스 트리 (https://m.blog.naver.com/PostView.naver?isHttpsRedirect=true&blogId=jerrypoiu&logNo=221280459884)
 * 
 * 1. a의 턴일때
 *  1-2.a위치를 기준으로 상하좌우를 확인
 *  1-3. a가 이기는 경우가 있을 경우, 계산된 값 중에 최솟값을 반환
 *  1-4. a가 이기는 경우가 없는 경우, 계산된 값 중에 최댓값을 반환
 * 
 * 
 * 2. b의 턴일때
 * 	2-2. b위치를 기준으로 상하좌우를 확인
 *  2-3. b가 이기는 경우가 있을 경우, 계산된 값 중에 최솟값을 반환
 *  2-4. b가 이기는 경우가 없는 경우, 계산된 값 중에 최댓값을 반환
 *  
 *  
 * */


public class PG92345_사라지는발판 {

	public static void main(String[] args) {

		int[][] board = {{1,1,1},{1,1,1},{1,1,1}};
		int[] aloc = {1,0};
		int[] bloc = {1,2};

		int result = solution(board, aloc, bloc);

		System.out.println(result);

	}

	private static int solution(int[][] board, int[] aloc, int[] bloc) {
		int answer = getMoveCnt(board, aloc[0], aloc[1], bloc[0], bloc[1]);
        
        return answer;
	}
	
	static int[] dx = {-1,1,0,0}; 
    static int[] dy = {0,0,-1,1}; 

    static boolean visited[][] = new boolean[5][5];
    
    
    //**최적의 플레이를 할 때 남은 이동횟수 구하기
    //반환값이 짝수 : 승리
    //반환값이 홀수 : 패배
    public static int getMoveCnt(int[][] board, int x, int y, int oX, int oY){
        
        //발판이 사라진 경우 종료
        if(visited[x][y]) return 0;
        int ans = 0;
        
        for(int i = 0; i<4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            
            //범위를 벗어나는 경우, 방문한 곳인 경우, 발판이 없는 경우 무시
            if(nx < 0 || nx >= board.length || ny < 0 || ny >= board[0].length) continue;
            if(visited[nx][ny] || board[nx][ny] == 0) continue;
            
            //이동시킬 때마다 턴 수 증가
            visited[x][y] = true;
            int val = getMoveCnt(board, oX, oY, nx, ny) + 1;
            visited[x][y] = false;
            
            
            // 현재 저장된 턴은 패배, 새로 계산된 턴이 승리인 경우
            if(ans % 2 ==0 && val % 2 != 0){
                ans = val;
            }
            
            //현재 저장된 턴과 새로 계산된 턴도 패배인 경우 => 최댓값
            else if(ans % 2 ==0 && val % 2 == 0){
                ans = Math.max(ans, val);
            }
            
            //현재 저장된 턴과 새로 계산된 턴 모두 승리인 경우 => 최소값
            else if(ans % 2 !=0 && val % 2 != 0){
                ans = Math.min(ans, val);
            }
        }
        
        return ans;
    }
	
}