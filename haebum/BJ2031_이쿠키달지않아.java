import java.util.*;
import java.io.*;

/**
 * 차의 개수(k)와 몇분적용(d)되는지 나오고
 * 
 * 음식의 개수 n개와 음식 먹을 시간(t)가 존재 
 * 
 * 음식리스트를 시간으로 정렬
 * 
 * 9 15 7 12 14 9 3 ->
 * 3 7 9 9 12 14 15
 * 
 * 현재 음식이 마지막으로 영향 받는 음식일때,
 * 최대 몇개의 음식이 영향 받았는지 개수 파악(어느음식부터 먹었을때, 현재음식까지 영향받은 개수가 나옴)
 * 구하는 과정은 이분탐색으로 적용가능 갯수를 mid값으로 지정해두고 구하기
 * tea_effect = [0,1,2,2,3,3,2,3]
 * 
 * 그다음 dp를 통해 주어진 차의 개수를 적용했을 때 최대값 구하기(냅색개념)
 * 
 * 
 *    0  3  7  9  9 12 14 15
 * 0  0  0  0  0  0  0  0  0
 * 1  0  2  3  3  3  3  3  3
 * 2  0  2  4  5  5  6  6  6
 * 
 * dp[음식idx][차의개수] = Math.max(이전음식까지 적용되게 마셨을때, 현재음식까지 최대 적용되는 음식 수를 뺀 과거의 마신거 + 현재 음식까지 적용되게 마셨을때)
 * 
 * 이 뜻은
 * 이전음식까지 적용되게 마신 최대값과
 * 현재음식까지 적용되게 마신 차 + 이 차를 마시기전까지의 최대값
 * 둘중의 최대값을 반환
 * 
 * for i(음식 idx)
 *  for j(차 idx)
 *     int t = tea_effect[i]; // 현재음식까지 적용되게 마신 차의 최대 음식 수
 *     dp[i][j] = Math.max(dp[i-1][j], dp[i-t][j-1] + t);
 * 
 * 
 */
public class BJ2031_이쿠키달지않아 {

    public static int t,n,d,k;
    public static int[] food,tea_effect;
    public static int[][] dp;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        t = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        food = new int[n+1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++){
            food[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(food);

        tea_effect = new int[n+1];
        for(int i = 1; i <= n; i++){
            tea_effect[i] = i - Math.max(0,binary_serach(i));
        }

        dp = new int[n+1][k+1];
        bottomUp();
        System.out.println(dp[n][k]);
    }

    // 차를 마셔서 현재 음식까지 적용되었을때,
    // 어느 음식부터 적용된건지 찾기
    public static int binary_serach(int idx){
        int start = 0;
        int end = idx;

        while(start <= end){
            int mid = (start+end)/2;
            //본인이 포함되므로 == 도 영향을 받지 않은것으로 생각해야함
            if(food[mid] <= food[idx] -d){
                start = mid +1;
            }else{
                end = mid -1;
            }
        }
        return end;
    }

    public static void bottomUp(){
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= k; j++){
                int t = tea_effect[i];
                dp[i][j] = Math.max(dp[i-1][j],t + dp[i-t][j-1]);
            }
        }
    }
}
