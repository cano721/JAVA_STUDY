import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class n14499_주사위굴리기 {
	static int[] dx = {0, 0, -1, 1}, dy = {-1, 1, 0, 0};
	static int[] dice = new int[7];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        
        int[][] board = new int[n][2];
        
        for (int i = 0; i < n; i++) {
        	st = new StringTokenizer(br.readLine());
        	for(int j = 0; j < m; j++) {
        		board[i][j] = Integer.parseInt(st.nextToken());
        	}
		}
        int[] arr = new int[k];
        
        st = new StringTokenizer(br.readLine());
        for(int i =0; i<k; i++){
            arr[i] = Integer.parseInt(st.nextToken());
            int nx = x + dx[arr[i]-1];
            int ny = y + dy[arr[i]-1];
            
            if(nx >= 0 && ny >= 0 && nx < n && ny < m) {
            	change(arr[i]);
            	if(board[nx][ny] == 0) {
            		board[nx][ny] = dice[6];
            	}else {
            		dice[6] = board[nx][ny];
            		board[nx][ny] = 0;
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
