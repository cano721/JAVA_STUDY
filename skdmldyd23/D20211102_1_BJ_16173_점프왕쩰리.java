import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class D20211102_1_BJ_16173_점프왕쩰리 {

	static int[][] arr;
    static boolean chk;

	static void dfs(int x, int y, int n, int n2) {
		if (arr[x][y] == 0) return;

		if (arr[x][y] == -1) {
			chk = true;
			return;
		}

		if (arr[x][y] < n) dfs(arr[x][y] + x, y, n - arr[x][y], n2);
		if (arr[x][y] < n2) dfs(x, arr[x][y] + y, n, n2 - arr[x][y]);
	}
	
    public static void main(String[] args) throws Exception {
        
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        
        arr = new int[n+1][n+1];
        chk = false;
        
        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) arr[i][j] = Integer.parseInt(st.nextToken());
        }
        
        dfs(1,1,n, n);
        System.out.println(chk ? "HaruHaru" : "Hing");
    }
}
