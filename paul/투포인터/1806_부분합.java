import java.util.*;
import java.io.*;

public class Main {
    static int n,s;
    static ArrayList<Integer> list = new ArrayList<>();
    public static void main(String[] args) throws Exception {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      n = Integer.parseInt(st.nextToken());
      s = Integer.parseInt(st.nextToken());
      st = new StringTokenizer(br.readLine());
      for(int i =0; i<n; i++){
          list.add(Integer.parseInt(st.nextToken()));
      }

      pro();

    }
    
    public static void pro(){
        int en = 0;
        long cm = 0L;
        int length =0;
        int ans = 1<<21;
        for(int start = 0; start < n; start++){
            while(cm < s && en < n){
                cm += list.get(en);
                en++;
                length++;
            }
            if(cm >= s){
                ans = Math.min(ans, length);
            }

            cm -= list.get(start);
            length--;
        }
        if(ans == 1<<21){
            System.out.println(0);
        }else{
            System.out.println(ans);
        }
        
    }
}
