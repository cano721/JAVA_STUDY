/**
    1. 해당 크기의 2차원 배열 생성 및 숫자 집어넣기
    
    2. 지정된 직사각형 회전
    
    3. 회전된 숫자들 중 작은 숫자 반환
    
    4. 작은 숫자 반환 배열을 최종 반환
**/

class Solution {
    
    public int[][] map;
    
    public int[] solution(int rows, int columns, int[][] queries) {
        
        map = createMap(rows,columns);
        
        int[] answer = new int[queries.length];
        
        int idx = 0;
        for(int[] query : queries){
            answer[idx++] = rotationMap(query);
        }
        
        return answer;
    }
    
    public int rotationMap(int[] query){
        int x1 = query[0]-1;
        int y1 = query[1]-1;
        int x2 = query[2]-1;
        int y2 = query[3]-1;
        
        int temp = map[x1][y1];
        int minNum = temp;
        
        for(int i = x1; i < x2; i++){
            map[i][y1] = map[i+1][y1];
            minNum = Math.min(minNum,map[i][y1]);
        }
        
        for(int i = y1; i < y2; i++){
            map[x2][i] = map[x2][i+1];
            minNum = Math.min(minNum,map[x2][i]);
        }
        
        for(int i = x2; i > x1; i--){
            map[i][y2] = map[i-1][y2];
            minNum = Math.min(minNum,map[i][y2]);
        }
        
        for(int i = y2; i > y1+1; i--){
            map[x1][i] = map[x1][i-1];
            minNum = Math.min(minNum,map[x1][i]);
        }
        map[x1][y1+1] = temp;
        
        return minNum;
    }
    
    
    public int[][] createMap(int row, int columns){
        
        int[][] newMap = new int[row][columns];
        
        int idx = 1;
        
        for(int i = 0; i < row; i++){
            for(int j = 0; j < columns; j++){
                newMap[i][j] = idx++;
            }
        }
        
        return newMap;
    }
}