/*
   2차원dp

   dp[가방무게][체크할물품]


   가방무게에따른 담을 수 있는 최대 물품 가치

   * 0  1  2  3  4  5  6  7(가방무게)
   * 0  0  0  0  0  0  0  0 
   * 6  0  0  0  0  0  13 13  1번물품(6무게 13가치)
   * 4  0  0  0  8  8  13 13  2번물품(4무게 8가치)
   * 3  0  0  6  8  8  13 14  3번물품(3무게 6가치)
   * 5  0  0  6  8  12 13 14  4번물품(5무게 12가치)
  (물품무게)

  가방무게에 따른 담을 수 있는 물품의 가치표다.

  3번째 라인을 보면 5무게까진 4무게 8가치를 담다가
  6무게엔 4무게 물품을 빼고 6무게 13가치 물품을 담는다.

  4번째 라인을 보면 가방 3무게엔 물품 3무게 6가치를 담다가
  가방 4무게~5무게엔 3무게물품을 빼고 4무게 8가치 물품을 담다가
  가방 6무게엔 6무게13가치 물품만 담다가 7무게엔
  6무게 13가치와 7에서 현재 물품무게인 3을 뺀
  가방4무게때 최대치인 8+ 현재 물품무게 6을 더해서 14가 나온다.

  이를 토대로 dp를 짜면

  dp[i][j] = Math.max(dp[i][j-1],dp[i-현재물품무게][j-1] + 현재물품가치);

    
*/

import java.util.*;
import java.io.*;

public class BJ12865_평범한배낭 {

    public static Integer[][] dp = new Integer[100_001][101];
    public static int [][] arr = new int[101][2];
    public static int n,k;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 물품수:n 최대 들 수 있는 가방무게: k
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        // 배열 넣기
        for(int i = 1; i <= n; i++){
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            // 물건무게 w
            int w = Integer.parseInt(st2.nextToken());
            // 물건가치 v
            int v = Integer.parseInt(st2.nextToken()); 
            arr[i][0] = w;
            arr[i][1] = v;
        }


        topdown(k,n);
        // 마지막 값 중 최대값 출력
        int answer = dp[k][n];
        bw.write(answer + "\n");
        bw.flush();
        bw.close();
    }

    //탑다운방식
    public static int topdown(int weight,int number){
        // 이미 구한적이 있다면 구한것을 반환
        if(weight <= 0 || number <= 0) return 0;

        if(dp[weight][number] != null){
            return dp[weight][number];
        }
        // 현재 물건 무게
        int cw = arr[number][0];
        // 현재 물건 가치
        int cv = arr[number][1];
        if(cw <= weight){
            dp[weight][number] = Math.max(topdown(weight,number-1), topdown(weight-cw,number-1)+cv);
        }else{
            dp[weight][number] = topdown(weight,number-1);
        }
        return dp[weight][number];
    }

    //바텀업방식
    public static void bottomUp(){
        // 가방 무게 돌기
        for(int i = 0; i <= k; i++){
            // 보석 돌기
            for(int j = 0; j <= n; j++){
                // 둘중에 하나라도 0이면 초기화
                if(i == 0 || j == 0){
                    dp[i][j] = 0;
                }else{
                    // 현재 물건 무게
                    int cw = arr[j][0];
                    // 현재 물건 가치
                    int cv = arr[j][1];
                    // 담을 수 있는 가방무게가 현재 물건 무게보다 같거나 크다면
                    if(i >= cw){
                        dp[i][j] = Math.max(dp[i][j-1],dp[i-cw][j-1]+cv);
                    // 크지않으면 기존 최대값 가져오기(현재 물건을 못담는단 뜻)
                    }else{
                        dp[i][j] = dp[i][j-1];
                    }
                }
            }
        }
    }
}
