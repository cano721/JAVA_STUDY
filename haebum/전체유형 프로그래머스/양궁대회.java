/**
    1. n과 m으로 처리
    
    2. info를 돌면서 이기는 방법과 넘어가는 법으로 계산
    
    3. 한번 넘어간곳은 중복체크 안하게 설정
    
    4. 이긴 라운드를 체크해놨다가, 계산
    
    5. 못이겼을 시엔 -1 반환
    
    6. 이겼을시엔 빅토리 배열을 돌면서 가중치를 둬서 계산
    6-1) 화살이 남았으면 0에 넣기
**/
class Solution {
    
    public int max;
    public int[] victory , answer;
    public int[] solution(int n, int[] info) {
        
        victory = new int[info.length];
        answer = new int[1];
        answer[0] = -1;
        solve(-1,n,info);
        
        return answer;
    }
    
    public void solve(int pidx, int n, int[] info){
        
        // 마지막까지 봤거나, 화살이 떨어졌을때
        if(pidx == info.length-1 || n <= 0){
            // 남은 화살은 마지막 0점으로 쓰는게 제일 낮은 점수 더 많이 맞힌 경우임
            if(n > 0){
                victory[10] = n;
            }
            
            // apeach 점수, rian 점수, 가중치점수 구하기
            int[] check = cal(info);
            int apeach = check[0];
            int rian = check[1];
            // 이겼을때
            if(rian > apeach){
                // 현재 점수차 보다 크다면
                if(max < rian-apeach){
                    answer = victory.clone();
                    max = rian-apeach;
                // 같은 점수일땐 낮은 점수 더 많이 맞춘거 찾기
                }else if(max == rian-apeach){
                    calv(victory);
                }
            }
            victory[10] = 0;
            return;
        }
        
        for(int i = pidx+1; i < info.length; i++){
            // 이겼을때
            if(n > info[i]){
                victory[i] = info[i]+1;
                solve(i,n-victory[i],info);
                victory[i] = 0;
            // 마지막이면 무조건 확인
            }else if(i == info.length-1){
                solve(i,n,info);
            }
        }
    }
    
    // 낮은 점수 맞춘거 확인
    public void calv(int[] victory){
        
        for(int i = 10; i >= 0; i--){
            if(answer[i] > victory[i]){
                return;
            }else if(answer[i] < victory[i]){
                answer = victory.clone();
                return;
            }
        }
    }
    
    public int[] cal(int[] info){
        int apeach = 0;
        int rian = 0;
        
        for(int i = 0; i < victory.length; i++){
            // 라이언이 이겼을때
            if(victory[i] > info[i]){
                rian += (10-i);
            // 어피치가 이겼을때
            }else{
                // 0발이 아니면
                if(info[i] > 0){
                    apeach += (10-i);   
                }
            }
        }
        
        return new int[] {apeach,rian};
    }
}