import java.util.*;
import java.io.*;

public class P11728 {
    static int n, m;
    static ArrayList<Integer> a = new ArrayList<>(), b = new ArrayList<>();
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
            a.add(Integer.parseInt(st.nextToken()));
        }

        st = new StringTokenizer(br.readLine());
        for(int i =0; i<m; i++){
            b.add(Integer.parseInt(st.nextToken()));
        }
    }

    public static void pro(){
        int ap =0, bp= 0;
        StringBuilder sb = new StringBuilder();
        
        for(int i =0; i< n+m; i++){
            if(ap == n) {
                sb.append(b.get(bp++)).append(' ');
            }
            else if (bp == m){
                sb.append(a.get(ap++)).append(' ');
            }
            else if (a.get(ap) < b.get(bp)){
                sb.append(a.get(ap++)).append(' ');
            }
            else{
                sb.append(b.get(bp++)).append(' ');
            }
        }

        System.out.println(sb);
    }
}
