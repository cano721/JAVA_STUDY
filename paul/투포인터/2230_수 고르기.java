import java.util.*;
import java.io.*;

public class Main {
    static int n;
    static long m;
    static ArrayList<Long> arr = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
    
    public static void input() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());;
        n = Integer.parseInt(st.nextToken());
        m = Long.parseLong(st.nextToken());
        
        for(int i =0; i<n; i++){
            arr.add(Long.parseLong(br.readLine()));
        }
    }

    public static void pro(){
        Collections.sort(arr);
        int left = 0, right = 1;
        long ans = Long.MAX_VALUE, cnt=0;
        while(left < n && left <= right){
           cnt = arr.get(right) - arr.get(left);
           if(cnt >= m){
               ans = Math.min(cnt, ans);
               left++;
           }else{
               right++;
           }
           if(right == n) break;
        }
        System.out.println(ans);
    }
}
