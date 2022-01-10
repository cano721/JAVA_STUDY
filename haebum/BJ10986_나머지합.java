
/**
 * 누적합 풀이
 * 
 * 최대 20만개의 배열이 있고,
 * 이 배열의 부분합 중 m으로 나눠서 0인 개수 찾기
 * 
 * 누적합을 구한 후
 * 2중 포문으로 i,j(시작과 끝) 지정하면 시간초과
 * 
 * i,j의 부분합은
 * sumArr[i] - sumArr[j] = a 일때 m으로 나눠서 0이 되어야하므로
 * sumArr[i] - sumArr[j] = a%m = 0
 * sumArr[i] - sumArr[j] 이 m으로 나눴을때 0으로 떨어져야함
 * (sumArr[i] - sumArr[j])%m = 0
 * sumArr[i]%m = sumArr[j]%m
 * 
 * 이 추론을 통해 값 구하기
 */
import java.util.*;
import java.io.*;

public class BJ10986_나머지합 {

    public static long[] sumArr;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        sumArr = new long[n+1];
        
        for(int i = 1; i <= n; i++){
            int num = Integer.parseInt(st.nextToken()); 
            sumArr[i] = sumArr[i-1]+num;
        }

        // 누적합의 개수를 저장한 맵
        Map<Long,Integer> map = new HashMap<>();
        map.put(0l,1);
        long cnt = 0;
        
        for(int i = 1; i <= n; i++){
            // 현재 누적합%m 과 같은 값이 있다면 개수만큼 더하기 아니면 0
            cnt += map.getOrDefault(sumArr[i]%m,0);

            // 기존에 누적합을 저장햇었다면 +1, 아니면 1
            map.put(sumArr[i]%m,map.getOrDefault(sumArr[i]%m,0)+1);
        }
        System.out.println(cnt);
    }
}
