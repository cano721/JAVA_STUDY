import java.util.*;

public class Main {
    static int arr[][];
    static int n;
    static boolean check[];
    static int total_min = Integer.MAX_VALUE;
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        arr = new int [n+1][n+1];
        check = new boolean[n+1];
        for(int i=1; i<=n; i++) {
            for(int k=1; k<=n; k++) {
                arr[i][k] = sc.nextInt();
            }
        }
        solve(1,0);
        System.out.println(total_min);
    }
    public static int get_a_min() {
        int mins = 0;
        for(int i=1; i<=n; i++) {
            if(!check[i])
                continue;
            for(int k=1;k<=n; k++) {
                if(i==k)
                    continue;
                if(check[i] && check[k]) {
                    mins+= arr[i][k];
                }
            }
        }
        return mins;
    }
    public static int get_b_min() {
        int mins = 0;
        for(int i=1; i<=n; i++) {
            if(check[i])
                continue;
            for(int k=1;k<=n; k++) {
                if(i==k)
                    continue;
                if(!check[i] && !check[k]) {
                    mins+= arr[i][k];
                }
            }
        }
        return mins;
    }
    public static void solve(int index, int depth) {
        if(depth == n/2) {
  
            total_min = Math.min(total_min, Math.abs(get_a_min()-get_b_min()));
        }
        for(int i=index; i<=n; i++) {
            if(!check[i]) {
                check[i] = true;
                solve(i+1,depth+1);
                check[i] = false;
            }
        }
    }
}