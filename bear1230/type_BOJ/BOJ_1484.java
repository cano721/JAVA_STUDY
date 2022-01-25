import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws NumberFormatException, IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int start = 1;
        int end = 1;
        boolean flag = false;

        while(true){
            long ans = (long)(Math.pow(start, 2)) - (long)(Math.pow(end, 2));

            if(start - end == 1 && ans > n)    break;
            if(ans >= n)
                end++;
            else
                start++;

            if(ans == n){
                System.out.println(start);
                flag = true;
            }
        }

        if(!flag)
            System.out.println(-1);
    }
}