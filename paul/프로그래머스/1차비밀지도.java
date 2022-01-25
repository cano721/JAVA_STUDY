import java.util.*;
import java.io.*;
class Solution {
    public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[n];
        int[] sum = new int[n];
        for(int i =0; i<n; i++){
            int v1 = arr1[i];
            int v2 = arr2[i] ;
            sum[i] = v1|v2;
            //System.out.println(Integer.toBinaryString(sum[i]));
        }
        
        
        for(int i =0; i<n; i++){
            String now = Integer.toBinaryString(sum[i]);
            
            int len = now.length();
            for(int k =0; k< n-len; k++){
                now = "0" + now;
            }
                
            StringBuilder tmp = new StringBuilder();
            for(int j=0; j<n; j++){
                if(now.charAt(j) == '0'){
                    tmp.append(" ");
                }else{
                    tmp.append("#");
                }
            }
            
            answer[i] = tmp.toString();
        }    
        
        
        return answer;
    }
}