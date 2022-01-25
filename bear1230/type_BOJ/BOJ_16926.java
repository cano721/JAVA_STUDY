import java.util.*;
import java.io.*;

public class Main {

	static int n, m, r;
	static int arr[][];
	static int map[][];
	
	static int dx[] = {0, 1, 0, -1}; // 우 하 좌 상
	static int dy[] = {1, 0, -1, 0};
    
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		
		arr = new int[n+1][m+1];
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int num = Math.min(n, m) / 2;
        for(int i =0; i< r; i++){
            for(int j = 0; j < num; j++) {
                int idx = 0;			
                int tmp = arr[j][j];		
                int x = j;
                int y = j;
                while (idx < 4) {				
                    int nx = x + dx[idx];				
                    int ny = y + dy[idx];				
                    if (nx >= j && ny >= j && nx < n - j && ny < m - j) {					
                        arr[x][y] = arr[nx][ny];					
                        x = nx;					
                        y = ny;				
                    } else idx++;			                       
                }			                
                arr[j+1][j] = tmp;	              
            }            
        }

	
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
		
	}

}
