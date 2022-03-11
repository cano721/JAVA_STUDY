import java.util.*;

class Solution {
    char[] charList = new char[]{'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
    HashMap<Character,Integer> map = new HashMap<>();
    public String solution(int n, int t, int m, int p) {

        int nextIdx;
        int stringIdx = 1;
        String current = "0";
        StringBuilder sb = new StringBuilder("");
        for(int i=0; i< 16; i++ ){
            map.put(charList[i],i);
        }

        for(int j=0; j<t; j++){
            nextIdx = p + j * m;
            while(stringIdx<nextIdx){
                current = nextNumber(current, n);
                stringIdx+= current.length();
            }
            int pos = nextIdx - (stringIdx - current.length()) -1;

            sb.append(current.charAt(pos));
        }



        String answer = sb.toString();
        return answer;
    }

    public String nextNumber(String num, int n){
        boolean up = false;
        int numsize = num.length();
        StringBuilder sb = new StringBuilder("");
        for(int i=numsize-1; i>=0; i--){
            char c = num.charAt(i);
            int idx = map.get(c);
            if(up||i == numsize-1) idx++;

            sb.append(charList[idx%n]);
            up = (idx >= n);
        }

        if(up) sb.append(charList[1]);
        sb.reverse();
        return sb.toString();
    }
}