import java.util.*;

class Solution {
    static boolean[][][] visited;
    public int[] solution(String[] grid) {
        int[] dr = new int[]{0,0,-1,1};
        int[] dc = new int[]{-1,1,0,0};
        ArrayList<Integer> list = new ArrayList<>();
        int rlen = grid.length;
        int clen = grid[0].length();
        visited = new boolean[rlen][clen][4];
        char[][] cGrid =  new char[rlen][clen];

        for(int i=0; i<rlen; i++) cGrid[i] = grid[i].toCharArray();

        for(int i=0; i< rlen; i++){
            for(int j=0; j<clen; j++){
                for(int k=0; k<4; k++){
                    countMovement(dr[k],dc[k],i,j,cGrid,list);
                }

            }
        }
        int[] answer = new int[list.size()];
        for(int i=0; i< list.size() ;i++){
            answer[i] =list.get(i);
        }

        Arrays.sort(answer);

        return answer;
    }


    public void countMovement(int dy, int dx,int posY, int posX , char[][] cGrid ,ArrayList<Integer> list){
        int ylength = cGrid.length;
        int xlength = cGrid[0].length;
        int cnt = 0;
        while(true){

        char direction = cGrid[posY][posX];

        int temp, dir;

        if(direction =='R'){
            temp =dy;
            dy = -dx;
            dx = temp;
        }else if(direction =='L'){
            temp = dy;
            dy = dx;
            dx = -temp;
        }

        if(dy==-1) dir = 2;
        else if(dy==1) dir =0;
        else if(dx== -1) dir =3;
        else dir =1;

        if(visited[posY][posX][dir]) {
            if(cnt!=0) list.add(cnt);
            break;
        }else {
            visited[posY][posX][dir] =true;
            cnt++;
        } 

        posX = (posX + dx + xlength)% xlength;
        posY = (posY + dy + ylength)% ylength;
    }
    }
}
class Pair{
    int row;
    int col;
    int cnt;
    public Pair(int r, int c, int count){
        row =r;
        col =c;
        cnt = count;
    }
}
