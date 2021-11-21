import java.util.*;
import java.io.*;

public class Main {
    static int n;
    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
    
    public static void input() throws Exception{
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       n = Integer.parseInt(br.readLine());
    }

    public static void pro(){
        int rememberWeight = 1, currentWeight =2;
        StringBuilder sb = new StringBuilder();
        
        while (rememberWeight< currentWeight && currentWeight < n){
            int value = currentWeight*currentWeight - rememberWeight*rememberWeight;
            if(value == n) sb.append(currentWeight++).append(' ');
            else if( value < n) currentWeight++;
            else rememberWeight++;
        }
        
        if(sb.toString().equals("")) System.out.println("-1");
        else System.out.println(sb);
    }

}