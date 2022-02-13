import java.util.*;

/*
n이 0이 될때까지 연산
짝수 n /= 2
홀수 n -= 1
*/

public class Solution {
    public int solution(int n) {
        int ans = 0;
        int idx = 0;
        while(n != 0){
            if(n%2 == 0){
                n /= 2;
            } else{
                n -= 1;
                ans++;
            }
        }
        return ans;
    }
}
