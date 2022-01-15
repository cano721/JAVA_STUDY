import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        String p = sc.nextLine();
        int i, n = p.length(), max = 0;
        for(i=0;i<n;i++)
            max = Math.max(max, getMax(p.substring(i, n)));
        System.out.println(max);
        sc.close();
    }

    private static int getMax(String p){
        int i, j = 0, n = p.length(), max = 0;
        int fail[] = new int[n];
        for(i=1;i<n;i++){
            while(j>0 && p.charAt(i) != p.charAt(j))
                j = fail[j-1];
            if(p.charAt(i) == p.charAt(j))
                max = Math.max(max, fail[i] = ++j);
        }
        return max;
    }
}
