/**
    1. 명령어에 따른 이동
    
    2. 명령어가 좌표평면을 넘어가면 그 명령어는 무시
    
    3. 전체 명령어에 의해 새로 가본 좌표의 개수를 반환
    3-1) 현재좌표에서 이동방향 선을 가본것으로 체크
    3-2) 올라간좌표에서 출발좌표까지 선도 써본것으로 체크
**/

class Solution {
    public int solution(String dirs) {
        int answer = 0;
        
        int[][][] map = new int[11][11][4];
        
        int x = 5;
        int y = 5;
        for(char c : dirs.toCharArray()){
            int nx = 0, ny = 0;
            int pos = 0;
            int npos = 0;
            if(c == 'U'){
                nx = x-1;
                ny = y;
                pos = 0;
                npos = 1;
            }else if(c == 'D'){
                nx = x+1;
                ny = y;
                pos = 1;
                npos = 0;
            }else if(c == 'R'){
                nx = x;
                ny = y+1;
                pos = 2;
                npos = 3;
            }else{
                nx = x;
                ny = y-1;
                pos = 3;
                npos = 2;
            }
            
            if(0 <= nx && nx < 11 && 0 <= ny && ny < 11){
                if(map[x][y][pos] == 0){
                    map[x][y][pos] = 1;
                    map[nx][ny][npos] = 1;
                    answer++;
                }
                x = nx;
                y = ny;
            }
        }
        
        return answer;
    }
}