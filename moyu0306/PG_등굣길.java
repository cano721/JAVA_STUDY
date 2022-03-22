import java.util.*;

class Solution {
    public int solution(int m, int n, int[][] puddles) {
        int[][] table = new int[n][m];
        for(int i =0;i<puddles.length;i++){
            int[] wet = puddles[i];
            table[wet[1]-1][wet[0]-1] = -1;
        }
        
        table[0][0]=1;
        for (int i=0; i<n;i++){
            for(int j=0; j<m;j++){
                if(table[i][j]<0) continue;
                if(j>0 && table[i][j-1]>=0) {
                    table[i][j]+=table[i][j-1]; 
                }
                if(i>0&&table[i-1][j]>=0){
                    table[i][j]+=table[i-1][j]; 
                }

                table[i][j]=table[i][j]%1000000007;
    
            }
        }
        
        
        int answer = table[n-1][m-1];
        return answer;
    }
}