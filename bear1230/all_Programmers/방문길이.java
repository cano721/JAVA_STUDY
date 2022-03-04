class Solution {
    public int solution(String dirs) {
        int answer = 0;
        
        boolean[][][] map = new boolean[11][11][2];
        int x = 5;
        int y = 5;

        for(int i=0; i< dirs.length(); i++) {        
            switch(dirs.charAt(i)) {
                case 'U' :
                    if(x-1 < 0) {
                        break;
                    }
                    x--;
                    if(map[x][y][0] == false) {
                        map[x][y][0] = true;
                        answer++;
                    }
                    break;
                case 'R' :
                    if(y+1 >=11) {
                        break;
                    }
                    if(map[x][y][1] == false) {
                        map[x][y][1] = true;
                        answer++;
                    }
                    y++;
                    break;
                case 'D' :
                    if(x+1 >=11) {
                        break;
                    }
                    if( map[x][y][0] == false) {
                        map[x][y][0] = true;
                        answer++;
                    }
                    x++;
                    break;
                case 'L' :
                    if(y-1 < 0) {
                        break;
                    }
                    y--;
                    if(map[x][y][1] == false) {
                        map[x][y][1] = true;
                        answer++;
                    }
                    break;
            }

        }
        return answer;
    }
}
