import java.util.*;
/*
N+1-> 퇴사
i일에 상담하면 D[i]일 ,m[i]원
day == n+1 -> 정답을 찾은 경우
day > n+1 문제 조건 위배

*/
public class Main {
    static final int inf = -100000000;
    static int[] d;
    static int[] m;
    static int n;
    static int ans = 0;
    
    // day일에 상담 할지 말지 결정, 수입은 sum
    static void sol(int day, int sum) {
        
        if (day == n+1) {
            if (ans < sum) ans = sum;
            return;
        }
        if (day > n+1) {
            return;
        }
        //상담 안할때
        sol(day+1, sum);
        //상담 할때
        sol(day+d[day], sum+m[day]);
    }
    
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        d = new int[n+1];
        m = new int[n+1];
        for (int i=1; i<=n; i++) {
            d[i] = sc.nextInt();
            m[i] = sc.nextInt();
        }
        sol(1, 0);
        System.out.println(ans);
    }
}