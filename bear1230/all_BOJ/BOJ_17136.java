// 블로그 참조 https://code-y.tistory.com/67
import java.io.*;
import java.util.*;

public class Main{
	
	static int[][] arr;
	static int ans = 987654321;
	static int[] pages = {0,5,5,5,5,5};
    
    public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		arr = new int[10][10];
		
		for(int i = 0 ; i < 10 ; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < 10 ; j++)
				arr[i][j] = Integer.parseInt(st.nextToken());
		}
		
		backtracking(0);
		
		System.out.println(ans == 987654321 ? -1 : ans);
	}
    
	static void backtracking(int cnt) {	
		int sr = -1, sc = -1;	
		out:for(int i = 0 ; i < 10 ; i++) {
			for(int j = 0 ; j < 10 ; j++) {
				if(arr[i][j] == 1) {
					sr = i; sc = j;
					break out;
				}
			}
		}
		
		if(sr == -1 && sc == -1) {
			ans = Math.min(ans, cnt);
			return;
		}
		
		int max = 5;
      
		while(max > 0) {
			boolean flag = true;
			out:for(int i = 0 ; i < max ; i++) {
				for(int j = 0 ; j < max ; j++) {
					if( sr + i >= 10 || sc + j >= 10 || arr[sr + i][sc + j] == 0) {
						flag = false;
						break out;
					}
				}
			}
			
			if( flag ) break;
			
			max--;
		}
		for(int i = 1 ; i <= max ; i++) {
			if(pages[i] == 0) continue;
			for(int r = sr ; r < sr + i ; r++) {
				for(int c = sc ; c < sc + i ; c++) {
					arr[r][c] = 0;
				}
			}
			pages[i]--;
			backtracking(cnt + 1);
			for(int r = sr ; r < sr + i ; r++) {
				for(int c = sc ; c < sc + i ; c++) {
					arr[r][c] = 1;
				}
			}
			pages[i]++;
		}
		
	}
	
	
}