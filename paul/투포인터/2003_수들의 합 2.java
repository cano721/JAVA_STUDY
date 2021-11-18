import java.util.*;
import java.io.*;

public class P2003 {
    static int n, m;
    static ArrayList<Integer> arr = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
    
    public static void input() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for(int i =0; i<n; i++){
            arr.add(Integer.parseInt(st.nextToken()));
        }
    }

    public static void pro(){
        int mid = 0, end = 0, cnt = 0;
        for(int start = 0; start <n; start++){
            if(mid < m && end < n){
                mid += arr.get(end);
                end++;
            }
            if(mid == m){
                cnt++;
            }
            mid -= arr.get(start);
        }

        System.out.println(cnt);
    }
}
