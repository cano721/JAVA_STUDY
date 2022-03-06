/**
    1. 0,0부터 맵의 범위를 전체 확인(30*30)
    
    2. 2*2블록이 전부 같은 블록인지 확인하고, 다 같다면 지워질 블록으로 별도 체크
    2-1) boolean 변수로 같은지 체크
    
    3. 맵 전체 확인 후 체크해놨던 지워질 블록 지우기
    
    4. 지워진 맵에서 아래부터 공간 채우기
    
    5. 1번부터 4번까지 반복하여 변경되는게 없을 때 지워진 블록 개수 반환
    
**/

class Solution {
    
    String[][] map;
    
    public int solution(int m, int n, String[] board) {
        int answer = 0;
        map = new String[m][n];
        
        for(int i = 0; i < m; i++){
            map[i] = board[i].split("");
        }
        
        while(true){
            // 지울 블록 체크
            int[][] removeMap = checkRemove(m,n);
            
            // 지우기
            int removeCnt = remove(m,n,removeMap);
            if(removeCnt == 0) break;
            
            answer += removeCnt;
            
            // 블럭 내리기
            fillDown(m,n);
        }
        
        return answer;
    }
    
    // 아래부터 채우기
    public void fillDown(int m, int n){
        
        for(int i = m-1; i >= 0; i--){
            for(int j = 0; j < n; j++){
                if(map[i][j].equals(" ")){
                    int k = i;
                    while(k > 0 && map[k][j].equals(" ")){
                        k--;
                    }
                    if(!map[k][j].equals(" ")){
                        map[i][j] = map[k][j];
                        map[k][j] = " ";
                    }
                }
            }
        }
    }
    
    public int remove(int m, int n, int[][] removeMap){
        
        int removeCnt = 0;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(removeMap[i][j] == 1){
                    map[i][j] = " ";
                    removeCnt++;
                }
            }
        }
        
        return removeCnt;
    }
    
    public int[][] checkRemove(int m, int n){
        
        int[][] removeMap = new int[m][n];
        
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                String cur = map[i][j];
                if(cur.equals(" ")) continue;
                // 맵 범위 안
                if(i+1 < m && j +1 < n){
                    if(cur.equals(map[i+1][j]) && cur.equals(map[i][j+1]) && cur.equals(map[i+1][j+1])){
                        removeMap[i][j] = 1;
                        removeMap[i+1][j] = 1;
                        removeMap[i][j+1] = 1;
                        removeMap[i+1][j+1] = 1;
                    }
                }
            }
        }
        
        return removeMap;
    }
}