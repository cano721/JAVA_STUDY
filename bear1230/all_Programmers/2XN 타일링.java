class Solution {
    public int solution(int n) {
        int answer[] = new int[60001];
        
        answer[0] = 1;
        answer[1] = 1;
        for(int i = 2; i<=n; i++){
            answer[i] = answer[i-1] + answer[i-2];
            answer[i] %= 1000000007;
        }
        return answer[n];
    }
}
