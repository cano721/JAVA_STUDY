import java.io.*;
import java.util.*;
/**
 *  구간합 s[i] = a[0] + .. a[i];
 *  부분 구간합 (s[i] - s[j-1])% m = 0 을 만족하는 순서쌍 i,j를 찾는 문제 
 *      -> i,j의 순서는 고정이므로 순서를 고려하지 않아도된다 
 *  모듈러 연산 법칙에 의헤 s[i]%m - s[j-1]%m = 0, s[i]%m = s[j-1]%m .
 *  즉 m으로 나눈 나머지가 같은 i,j의 순서쌍을 구하면 된다.
 *  모든 index에 대해 구간합을 구해준 뒤 해당 값에 m을 나눈 나머지 값을 cnt[] 배열에 저장하여 갯수를 샌다.
 *  s[i]%m =0 인 경우엔, 하나만 뽑아도 조건을 만족하므로 s[i]%m = 0인 경우를 초기에 포함시켜준다.
 *  cnt[s[i]%m] = k 인 경우에, k개중 2개를 뽑는 경우의 수는 kC2 -> k(k-1)/2 로 구할 수 있음.
 */
public class Main{
    static int n,m;
    static int[] sum;
    static int[] cnt; // sum[i]%m 의 갯수를 세기위한 array
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        sum = new int[n+1];
        cnt = new int[m];
        st = new StringTokenizer(br.readLine());
        for(int i =1; i< n+1; i++){
            sum[i] = (sum[i-1] + Integer.parseInt(st.nextToken()))%m;
            cnt[sum[i]]++;
        }

        //sum[i]%m == 0 인 경우는 하나만 있어도 만족하기 때문에 ans 초기값에 cnt[0]을 집어넣고 시작한다.
        long ans = cnt[0];
        for(int i =0; i<m; i++){
            ans += (long) cnt[i]*(cnt[i]-1)/2;
        }

        System.out.println(ans);
    }
}