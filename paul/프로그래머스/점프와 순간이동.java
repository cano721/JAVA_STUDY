import java.util.*;

public class Solution {
    public int solution(int n) {
        
        int ans = recursive(n);
        return ans;
    }
    
    static int recursive(int n){
        if(n == 1 || n == 0) return n;
        if(n%2 == 0 ) return recursive(n/2);
        else return recursive(n-1)+1;
    }
}