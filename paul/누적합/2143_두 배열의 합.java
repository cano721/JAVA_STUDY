import java.io.*;
import java.util.*;

public class Main{
    static int t;
    static int n, m;
    static int[] a,b;
    static List<Integer> sumA;
   
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());
        n = Integer.parseInt(br.readLine());
        a= new int[n];
        StringTokenizer st =new StringTokenizer(br.readLine());
        for(int i =0; i<n; i++){
            a[i] = Integer.parseInt(st.nextToken());
        }
        
        m = Integer.parseInt(br.readLine());
        b = new int[m];
        st = new StringTokenizer(br.readLine());
        for(int i= 0; i< m; i++){
            b[i] = Integer.parseInt(st.nextToken());
        }
        pro();
    }

    static void pro(){
        sumA = new ArrayList<>();
        for(int i =0; i<n; i++){
            int tmp = 0;;
            for(int j=i; j<n; j++){
                tmp += a[j];
                sumA.add(tmp);
            }
        }

        Map<Integer, Integer> map = new HashMap<>();
        for(int i =0; i<m; i++){
            int tmp = 0;;
            for(int j=i; j<m; j++){
                tmp += b[j];
                map.put(tmp, map.getOrDefault(tmp, 0) + 1);
            }
        }

        long ans = 0;
        for(int value : sumA){
            ans += map.getOrDefault(t-value, 0);
        }
        System.out.println(ans);
    }
}