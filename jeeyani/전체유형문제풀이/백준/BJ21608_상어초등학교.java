package 전체유형문제풀이.백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


/*
 * 구현
 * 
 * 1. 조건에 따른 학생 배치하기
 * 	1-2. 각 조건에 따른 자리를 점수를 주어 높은 점수를 가지는 자리에 배치시키기 (인접한 곳에 친구가 배치되어 있다면 10포인트 추가로 주기)
 * 	1-3. 행 -> 열 조건은, 자리 탐색 순서와 동일 함으로 크게 신경쓰지 않는다.
 * 
 * 2. 배치완료 이후, 모든 학생의 호감도 계산하기
 * 
 * https://www.youtube.com/watch?v=EVhFxSMtetc
 * */

public class BJ21608_상어초등학교 {

	static int n, result = 0;
    static int[][] seat; 
    static Student[] students; 
    
    static int[] likeable = {0, 1, 10, 100, 1000}; //호감도 체크
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		
		seat = new int[n+1][n+1];
		students = new Student[n*n];
		
		for (int i = 0; i < n*n; i++) {
			st = new StringTokenizer(br.readLine());
			int studentNo = Integer.parseInt(st.nextToken());
			
			int[] tempFriend = new int[4];
			for (int j = 0; j < 4; j++) {
				tempFriend[j] = Integer.parseInt(st.nextToken());
			}
			
			students[i] = new Student(studentNo, tempFriend, 0, 0);
			
			//입력받은 순서대로 학생들 자리 배치하기
			seating(i);
		}
		
		//학생들 호감도 계산하기
		getLikeable();
		
	    System.out.println(result);
		
	}


	public static void getLikeable() {
		
		for (int i = 0; i < n*n; i++) {
			int cnt = 0;
			int x = students[i].x;
			int y = students[i].y;
			
			for (int j = 0; j < 4; j++) {
				int nx = x +dx[j];
				int ny = y +dy[j];
				
				if(nx < 0 || nx >= n || ny < 0 || ny >=n ) continue;
				
				for (int k = 0; k < 4; k++) {
					if(seat[nx][ny] == students[i].friend[k]) {
						cnt++;
					}
				}
			}
			
			result += likeable[cnt];
		}
		
	}

	//1.비어있는 칸 중에서 좋아하는 학생이 인접한 칸에 가장 많은 칸으로 자리를 정한다.
	//2. 1을 만족하는 칸이 여러 개이면, 인접한 칸 중에서 비어있는 칸이 가장 많은 칸으로 자리를 정한다.
	
	//1-2. 각 조건에 따른 자리를 점수를 주어 높은 점수를 가지는 자리에 배치시키기 (인접한 곳에 친구가 배치되어 있다면 10포인트 추가로 주기)
	//1-3. 행 -> 열 조건은, 자리 탐색 순서와 동일 함으로 크게 신경쓰지 않는다.
	public static void seating(int idx) {

		int tempX = -1;
		int tempY = -1;
		int scores = -1;

		for (int j = 0; j < n; j++) {
			for (int k = 0; k < n; k++) {
				//해당 위치가 비어져 있는지 확인하기
				if (seat[j][k] == 0) {
					//해당 위치에 대한 점수 계산하기
					int tempScore = getScore(j, k, idx);

					//점수가 제일 높은 곳이 최종적인 위치임
					if (scores < tempScore) {
						tempX = j;
						tempY = k;
						scores = tempScore;
					}
				}
			}
		}

		//학생 좌석 배치하기
		//학생이 배치된 좌석으로 위치 갱신
		students[idx].x = tempX;
		students[idx].y = tempY;

		seat[tempX][tempY] = students[idx].myNo;

	}

	public static int getScore(int x, int y, int idx) {
		
		int score = 0;
		
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(nx < 0 || nx >= n || ny < 0 || ny >=n ) continue;
			
			//빈칸이라면 점수 1점
			if(seat[nx][ny] == 0) {
				score++;
			}
			else {
				
				//해당 위치에 좋아하는 친구가 있다면 10점
				for (int j = 0; j < 4; j++) {
					if(seat[nx][ny] == students[idx].friend[j]) {
						score += 10;
					}
				}
			}
		}
		return score;
	}

	//학생 정보 저장하기
	public static class Student{
		int myNo;
		int[] friend = new int[4];
		int x;
		int y;
		
		public Student(int myNo, int[] friend, int x, int y) {
			this.myNo = myNo;
			this.friend = friend;
			this.x = x;
			this.y = y;
		}
		
		
	}
}
