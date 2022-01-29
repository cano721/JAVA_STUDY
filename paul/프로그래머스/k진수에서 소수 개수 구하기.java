import java.util.*;
import java.io.*;
class Solution {
    public int solution(int n, int k) {
        int answer = 0;
        String[] arr = Integer.toString(n, k).split("0");
        
        for(String s : arr){
            if(s.length() > 0){
                System.out.println(s);
                long value = Long.parseLong(s);    
                if(value < 2) continue;
                if(!isPrime(value)) continue;
                //System.out.println(value);
                answer++;
            }
        }
        
        return answer;
    }
    
    public static boolean isPrime(long value){
        for(long i=2; i*i <= value; i++){
            if(value % i ==0) return false;
        }
        return true;
    }
}