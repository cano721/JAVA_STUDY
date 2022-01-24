
/**
 * 누적합 풀이
 * 
 * 최대 20만개의 배열이 있고,
 * 이 배열의 부분합 중 k인 합의 개수 찾기
 * 
 * 누적합을 구한 후
 * 2중 포문으로 i,j(시작과 끝) 지정하면 시간초과
 * 
 * 현재위치의 누적합에서 k를 뺐을때 나오는 값이
 * 누적합중에 있는지를 확인.
 * 
 */
import java.util.*;
import java.io.*;

public class BJ2015_수들의합4 {

    public static int[] sumArr;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        sumArr = new int[n+1];
        
        for(int i = 1; i <= n; i++){
            int num = Integer.parseInt(st.nextToken()); 
            sumArr[i] = sumArr[i-1]+num;
        }

        // 누적합의 개수를 저장한 맵
        Map<Integer,Integer> map = new HashMap<>();
        map.put(0,1);
        long cnt = 0;
        // 처음부터 끝까지 돌면서 k를 뺐을때의 누적합이 있는지 확인
        for(int i = 1; i <= n; i++){
            // 현재 누적합에서 k를 뺀 차이가 있다면 더해주고 아니면 0
            cnt += map.getOrDefault(sumArr[i]-k,0);

            // 기존에 누적합을 저장햇었다면 +1, 아니면 1
            map.put(sumArr[i],map.getOrDefault(sumArr[i],0)+1);
        }
        System.out.println(cnt);
    }
}