import java.util.*;
class Solution {
    
    static boolean[][][] out ;
    static int[] dy = {-1,0,1,0};
    static int[] dx = {0,1,0,-1};
    

    public List<Integer> solution(String[] grid) {
        List<Integer> answer = new ArrayList<>();
        out = new boolean[grid.length][grid[0].length()][4];
        for(int i =0; i< grid.length; i++){
            for(int j =0; j< grid[0].length(); j++){
                for(int dir =0; dir< 4; dir++){
                    if(!out[i][j][dir]){
                        int k = traversal(i, j, dir, grid);
                        answer.add(k);
                    }
                }
            }
        }
        
        Collections.sort(answer);
        return answer;
    }
    
    public static int traversal(int y, int x, int dir, String[] grid){
        //0, 0에서 dir 방향으로 탐색 시작
        int cnt = 0;
        while(true){
            
            if(out[y][x][dir]) return cnt;
            out[y][x][dir] = true;
            
            cnt++;
            
            y = update(dy[dir] + y, grid.length);
            x = update(dx[dir] + x, grid[0].length());
            
            dir = getDir(dir, grid[y].charAt(x));
            
        }
    }
    
    static int update(int loc, int n){
        if(loc < 0) loc = n-1;
        else if (loc >= n) loc = 0;
        return loc;
    }
    
    // dir : 상, 우, 하, 좌 순서
    static int getDir(int dir, char k){
        switch(k){
            case 'S':
                return dir;
            case 'L':
                return dir = (dir+3)%4;
            case 'R':
                return dir = (dir+1)%4;
        }
        return dir;
    }
    
}