package elwlahd555.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon14500_테트로미노 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		int N= Integer.parseInt(st.nextToken());
		int M= Integer.parseInt(st.nextToken());
		int map[][]=new int[N][M];
		int answer=0;
		for (int i = 0; i < N; i++) {
			st=new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				//바
				if(j+4<=M) {
					answer=Math.max(answer, map[i][j]+map[i][j+1]+map[i][j+2]+map[i][j+3]);
				}
				if(i+4<=N) {
					answer=Math.max(answer, map[i][j]+map[i+1][j]+map[i+2][j]+map[i+3][j]);
				}
				//네모
				if(i<N-1&&j<M-1) {
					answer=Math.max(answer, map[i][j]+map[i+1][j]+map[i][j+1]+map[i+1][j+1]);
				}
				//Z
				if(i<N-2&&j<M-1) {
					answer=Math.max(answer, map[i][j]+map[i+1][j]+map[i+1][j+1]+map[i+2][j+1]);
				}
				if(i>0&&j<M-2) {
					answer=Math.max(answer, map[i][j]+map[i][j+1]+map[i-1][j+1]+map[i-1][j+2]);
				}
				if(i<N-1&&j<M-2) {
					answer=Math.max(answer, map[i][j]+map[i][j+1]+map[i+1][j+1]+map[i+1][j+2]);
				}
				if(i<N-2&&j>0) {
					answer=Math.max(answer, map[i][j]+map[i+1][j]+map[i+1][j-1]+map[i+2][j-1]);
				}
				//L
				if(i<N-2&&j<M-1) {
					answer=Math.max(answer, map[i][j]+map[i+1][j]+map[i+2][j]+map[i+2][j+1]);
					answer=Math.max(answer, map[i][j]+map[i+1][j]+map[i+2][j]+map[i][j+1]);
					answer=Math.max(answer, map[i][j]+map[i][j+1]+map[i+1][j+1]+map[i+2][j+1]);
				}
				if(i<N-1&&j<M-2) {
					answer=Math.max(answer, map[i][j]+map[i+1][j]+map[i][j+1]+map[i][j+2]);
					answer=Math.max(answer, map[i][j]+map[i][j+1]+map[i][j+2]+map[i+1][j+2]);
				}
				if(i>0&&j<M-2) {
					answer=Math.max(answer, map[i][j]+map[i-1][j]+map[i][j+1]+map[i][j+2]);
					answer=Math.max(answer, map[i][j]+map[i][j+1]+map[i][j+2]+map[i-1][j+2]);
				}
				if(i<N-2&&j>0) {
					answer=Math.max(answer, map[i][j]+map[i+1][j]+map[i+2][j]+map[i+2][j-1]);
				}
				//ㅜ
				if(i<N-1&&j<M-2) {
					answer=Math.max(answer, map[i][j]+map[i][j+1]+map[i+1][j+1]+map[i][j+2]);
				}
				if(i<N-2&&j<M-1) {
					answer=Math.max(answer, map[i][j]+map[i+1][j]+map[i+2][j]+map[i+1][j+1]);
				}
				if(i>0&&j<M-2) {
					answer=Math.max(answer, map[i][j]+map[i][j+1]+map[i][j+2]+map[i-1][j+1]);
				}
				if(i<N-2&&j>0) {
					answer=Math.max(answer, map[i][j]+map[i+1][j]+map[i+2][j]+map[i+1][j-1]);
				}
			}
		}
		System.out.println(answer);
	}
}
