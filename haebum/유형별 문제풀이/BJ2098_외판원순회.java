/**
 * 
 * 실패 ㅠ
 * 
 * 본인에서 시작해서 본인으로 돌아오는 가장 적은 비용을 쓴 경로
 * 
 * 외판원 순회 알고리즘 사용..
 * 
 * DP + dfs + 비트마스킹
 * 
 * DP[현재도시][방문했던도시정보(bit)] = 이후에 방문하는것들 포함한 최소비용
 * 
 * 어느 도시에서 출발했던지 최소비용 사이클을 찾는거기때문에
 * 1번 도시 시작으로 하겠음
 * 
 * dfs를 돌면서 어떤 도시를 방문할건지 선택
 * 
 * 최종 다돌았을때, 마지막에서 처음으로 돌아오는 값을 더해줌
 * 
 * 
 */
import java.util.*;
import java.io.*;

public class BJ2098_외판원순회 {
    
    public static int n;
    public static int[][] arr, dp;
    public static int INF = 1_000_000*16;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        
        arr = new int[n][n];
        dp = new int[n][(1<<n)-1];
        for(int i = 0; i < n; i++){
            Arrays.fill(dp[i],INF);
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //첫번째도시 방문
        bw.write(dfs(0,1) +"\n");
        bw.flush();
        bw.close();
    }

    // 최종 비용 반환 dfs
    public static int dfs(int city, int visited){

        // 전체 다 방문했을 때
        if(visited == (1<<n) -1){
            // 마지막도착지점에서 스타트지점 확인
            // 0이면 불가이므로 inf 반환
            if(arr[city][0] == 0){
                return INF;
            }
            return arr[city][0];
        }

        // 저장된 dp면 반환
        if(dp[city][visited] != INF){
            return dp[city][visited];
        }

        // 다음 도시 방문하기
        for(int i = 0; i < n; i++){
            // 아직 방문하지않았고 길이 있으면 방문
            if((visited&(1<<i)) == 0 && arr[city][i] != 0){
                // 이후 방문비용 담기
                dp[city][visited] = Math.min(dp[city][visited],dfs(i,visited|(1<<i)) + arr[city][i]);
            }
        }

        return dp[city][visited];
    }


}
