import java.io.*;
import java.util.*;

/*
https://velog.io/@hammii/%EB%B0%B1%EC%A4%80-14499-%EC%A3%BC%EC%82%AC%EC%9C%84-%EA%B5%B4%EB%A6%AC%EA%B8%B0-java
블로그 참조햇슴니다..
*/
public class Main {
    static int dice[] = new int[7];
    static int n,m,x,y,k;
    static int arr[][];
    public static int[] dx = {0, 0, -1, 1}; // 동, 서, 북, 남
    public static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
        
        n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		arr = new int [n][m];
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++) {
            int d = Integer.parseInt(st.nextToken());
            int nx = x + dx[d - 1];
            int ny = y + dy[d - 1];
            if (nx >= 0 & ny >= 0 && nx < n && ny < m) {
                change(d);
                
                if (arr[nx][ny] == 0) {
                    arr[nx][ny] = dice[6];
                } else {
                    dice[6] = arr[nx][ny];
                    arr[nx][ny] = 0;
                }
                System.out.println(dice[1]);
        
                x = nx;       
                y = ny;

        }

    }
    }
	public static void change(int d) {
		int[] temp =  dice.clone();
		switch(d) {		
            case 1:
                dice[1] = temp[4];
                dice[3] = temp[1];
                dice[4] = temp[6];
                dice[6] = temp[3];
                break;
            case 2:
                dice[1] = temp[3];
                dice[3] = temp[6];
                dice[4] = temp[1];
                dice[6] = temp[4];
                break;
            case 3:
                dice[1] = temp[5];
                dice[2] = temp[1];
                dice[5] = temp[6];
                dice[6] = temp[2];
                break;
            case 4:
                dice[1] = temp[2];
                dice[2] = temp[6];
                dice[5] = temp[1];
                dice[6] = temp[5];
                break;
		}
    }
}

