   import java.io.BufferedReader;
    import java.io.InputStreamReader;
    import java.util.Arrays;

public class Main {
        static int N;
        static int M;
        static int[][] DP;
        static boolean[] isNotPossible;
        public static void main(String[] args) throws Exception {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String[] input = br.readLine().split(" ");
            N = Integer.parseInt(input[0]);
            M = Integer.parseInt(input[1]);
            DP = new int[101][101];
            for(int i=0; i<101; i++) Arrays.fill(DP[i],Integer.MAX_VALUE);
            isNotPossible = new boolean[101];
            if(M!=0){
                String[] notPossibleDay = br.readLine().split(" ");
                for(String i : notPossibleDay){
                    isNotPossible[Integer.parseInt(i)] = true;
                }
            }

            System.out.println(solve(1,0,0));
        }

        public static int solve(int day, int coupon, int price){
            if(N<day) return price;
            if(DP[day][coupon]!=Integer.MAX_VALUE) return DP[day][coupon] + price;
            if(isNotPossible[day]) return solve(day+1,coupon,price);
            int min =Integer.min(solve(day+5,coupon+2,price+37000),
                    Integer.min(solve(day+1,coupon,price+10000),solve(day+3,coupon+1,price+25000)));
            if(coupon>=3){
                min = Integer.min(min,solve(day+1,coupon-3,price));
            }
            DP[day][coupon] = min - price;
            return min;
        }
    }