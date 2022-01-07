import java.io.*;
import java.util.*;
/**
 *  모든 위치에서 누적합을 구한다.
 *  sum[i] - sum[j] = k 임을 만족해야함.
 *  sum[i] -k = sum[j] 를 찾으면 된다. 
 *  map을 이용해서 sum[i]-k 가 존재하는지 확인.
 */
public class Main{
    static int n, k;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        arr = new int[n+1];
        st = new StringTokenizer(br.readLine());
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);

        long answer = 0;
        for(int i =0; i<n; i++){
            arr[i+1] = arr[i]+Integer.parseInt(st.nextToken());
            answer += map.getOrDefault(arr[i+1]-k, 0);
            map.put(arr[i+1], map.getOrDefault(arr[i+1],0) + 1);
        }
        
        System.out.println(answer);
    }
}