import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class D20211029_2_BJ_14501_퇴사 {
	static int N, max = 0;
    static int[] Day, Benefit, DP;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        Day = new int[N];
        Benefit = new int[N];
        DP = new int[N*2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            Day[i] = Integer.parseInt(st.nextToken());
            Benefit[i] = Integer.parseInt(st.nextToken());
        }
        dfs(0, 0);
        dp();
        System.out.println(max);
        System.out.println(DP[N]);
	}
	public static void dfs(int date, int pay) {
		if(date == N) {
//			max = Math.max(max, date == N ? pay : pay - Benefit[N-1]); << 무조건 마지막날을 뺴주는게 아니라 재귀 돌릴때 덧셈 X			
			max = Math.max(max, pay);
			return;
		}
		
		if(date+Day[date] > N) dfs(N, pay);
		else dfs(date+Day[date], pay+Benefit[date]);
		dfs(date+1, pay);
	}
	
	public static void dp() {
		for (int i = 0; i < N; i++) {
			if (i + Day[i] <= N) DP[i + Day[i]] = Math.max(DP[i + Day[i]], DP[i] + Benefit[i]);
			DP[i + 1] = Math.max(DP[i + 1], DP[i]);
		}
	}
}
