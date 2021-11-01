import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static char[] a = new char[20];
    static ArrayList<String> ans = new ArrayList<>();
    static boolean[] check = new boolean[10];
    
    static boolean ok(char x, char y, char op) {
        
        if (op == '<') {
            if (x > y) return false;
        }
        if (op == '>') {
            if (x < y) return false;
        }
        return true;
    }
    
    //index -> 부등호 위치
    static void solve(int index, String num) {
        if (index == n+1) { 
            ans.add(num);
            
            return;
        }
        for (int i=0; i<=9; i++) {
            if (check[i]) continue;
            //index가 0이면 앞의수가 0이니 항상 가능/이전수, 선택하려는수, 부등호 만족할때만 <i>번째로넣음
            if (index == 0 || ok(num.charAt(index-1), (char)(i+'0'), a[index-1])) {
                check[i] = true;
                solve(index+1, num+Integer.toString(i));
                check[i] = false;
            }
        }
    }
    public static void main(String args[])throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine(), " ");
        for (int i=0; i<n; i++) {
            a[i] = st.nextToken().charAt(0);
        }
        solve(0, "");
        Collections.sort(ans);
        int m = ans.size();
        System.out.println(ans.get(m-1));
        System.out.println(ans.get(0));
    }
}