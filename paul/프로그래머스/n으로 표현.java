class Solution {
    public int answer = -1;
    public void solve(int n, int num, int cnt, int val) {
        if(cnt > 8) return; 
        if(num == val){
            if(answer > cnt || answer == -1) answer = cnt;
            return;
        }
        int dif = 0;
        for(int i = 1; i <= 8; i++){
            dif = dif * 10 + n;
            solve(n, num, cnt + i, val + dif);
            solve(n, num, cnt + i, val - dif);
            solve(n, num, cnt + i, val * dif);
            solve(n, num, cnt + i, val / dif);
        }
    }
    
    public int solution(int N, int number) {
        solve(N, number, 0, 0);
        return answer;
    }
}