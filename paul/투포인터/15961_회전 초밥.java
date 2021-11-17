import java.util.*;
import java.io.*;

public class Main {
    static int n, d, k, c;
    static ArrayList<Integer> arr = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
    
    public static void input() throws Exception{
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       StringTokenizer st = new StringTokenizer(br.readLine());
       n = Integer.parseInt(st.nextToken());
       d = Integer.parseInt(st.nextToken());
       k = Integer.parseInt(st.nextToken());
       c = Integer.parseInt(st.nextToken());
       for(int i =0; i<n; i++){
           int t = Integer.parseInt(br.readLine());
           arr.add(t);
       }

       //원형으로 잇기 위해서 k개 만큼을 다시 이어 붙여줌.
       for(int i =0; i< k; i++){
           arr.add(arr.get(i));
       }
    }

    public static void pro(){
        //m : 연속해서 고른 접시 수, cnt : 먹은 접시의 종류 수.
        int m =0, end = 0, cnt =0, ans = 0; 
        int length = arr.size();
        int[] vis = new int[d+1];
        for(int start = 0; start < length; start++){
            while(m < k && end < length){
                //처음 먹어보는 음식이면 cnt 증가.
                if(vis[arr.get(end)] == 0) cnt++;
                vis[arr.get(end)]++;
                m++;
                end++;
            }
            if(m == k){
                if(vis[c] == 0) ans = Math.max(ans, cnt+1);
                else ans = Math.max(ans, cnt);
            }

            vis[arr.get(start)]--;
            if(vis[arr.get(start)] == 0) cnt--;
            m--;
        }

        System.out.println(ans);
    }
}
