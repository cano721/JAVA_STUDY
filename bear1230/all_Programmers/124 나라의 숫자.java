class Solution {
    public String solution(int n) {
        String answer = "";
        String[] num = {"4", "1", "2"};
        
        while(n > 0){
            int a = n % 3;
            n /= 3;
            if(a == 0) n--;
            
            answer = num[a] + answer;
            
        }
        
        return answer;
    }
}
