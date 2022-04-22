public class Main {

    static int[] DP;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String code = br.readLine();
        int len = code.length();
        int divider = 1000000;
        DP = new int[len+1];
        DP[0] = 1;
        DP[1] = 1;
        if(code.substring(0,1).equals("0")){ System.out.println(0); return;}

        for(int i=2; i<len+1; i++){
            int num1 =Integer.parseInt(code.substring(i-1,i));
            int num2 = Integer.parseInt(code.substring(i-2,i));
            if(num1!=0) DP[i]+=DP[i-1];
            if(num2<27&&num2>=10) DP[i]+=DP[i-2];
            else if(num2%10==0){ System.out.println(0); return;}
            DP[i]%=divider;
        }
        System.out.println(DP[len]);
    }

}