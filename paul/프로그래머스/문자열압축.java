import java.util.*;
class Solution {
    
    public int solution(String s) {
        int answer = 1005;
        int n = s.length();
        if( n == 1) return 1;
        for(int i =1; i<=n; i++){
            answer = Math.min(answer, sol(i,s));
        }
        return answer;
    }

    public static int sol(int n, String s){
        //string을 n개씩 잘라서 총 길이를 반환하는 문자열.
        String t = s.substring(0, n);
        int len =0, cnt =1;
        boolean flag = true;
        for(int i = 1; i < s.length()/n; i++){
            int st = i*n;
            int en = st+n;
            if(t.equals(s.substring(st,en))){
                cnt++;
                flag= true;
            }else{
                len += cnt == 1? n: n + Integer.toString(cnt).length();
                cnt = 1;
                t = s.substring(st,en);
                flag= false;
            }
        }

        len += cnt == 1? n: n+Integer.toString(cnt).length();

        len += s.length()%n;

        return len;
        
    }
}