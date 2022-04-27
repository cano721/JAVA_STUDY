import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static char[][] map;
    static boolean[][][] visited;
    static int[] dx = new int[]{-1,-1,0,1,1,1,0,-1,0};
    static int[] dy = new int[]{0,-1,-1,-1,0,1,1,1,0};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new char[N][N];
        visited = new boolean[N][N][2];
        int cnt = 0;
        Queue<Status> q = new LinkedList<>();
        Status start = null;
        Status end = null;
        int bCount = 1;
        int bRow = 0;
        int eCount = 1;
        int eRow =0;
        for(int i=0; i<N; i++){
           map[i] = br.readLine().toCharArray();
           for(int j=0; j<N; j++){
                if(map[i][j] == 'B'){
                    if(bCount==1){
                        bRow = i;
                    }else if(bCount==0){
                        if(bRow == i) start = new Status(i,j,true,0);
                        else start = new Status(i,j,false,0);
                    }
                    bCount--;

                }
               if(map[i][j] == 'E'){
                   if(eCount==1){
                       eRow = i;
                   }else if(eCount==0){
                       if(eRow == i) end = new Status(i,j,true,0);
                       else end  = new Status(i,j,false,0);
                   }
                   eCount--;

               }
           }
        }

        q.offer(start);
        while(!q.isEmpty()){
            Status status = q.poll();
            if(status.r == end.r&& status.c == end.c && status.isHorizontal== end.isHorizontal){
                cnt = status.cnt;
                break;
            }

            for(int i=0; i<9; i+=2){
                int posY = status.r+dy[i];
                int posX = status.c+dx[i];
                if (i==8){
                    if(isRotatable(status))status.isHorizontal = !status.isHorizontal;
                    else continue;
                }
                int horizontal = (status.isHorizontal)? 0 : 1;
                if(posY<0||posX<0||posY>=N||posX>=N||map[posY][posX]=='1'||visited[posY][posX][horizontal]) continue;
                if(status.isHorizontal) {
                    if (posX - 1 < 0 || posX + 1 >= N || map[posY][posX + 1] == '1' || map[posY][posX - 1] == '1')
                        continue;
                }else{
                    if(posY-1<0||posY+1>=N||map[posY+1][posX]=='1'||map[posY-1][posX]=='1')
                        continue;
                }
                visited[posY][posX][horizontal]= true;
                q.offer(new Status(posY,posX,status.isHorizontal,status.cnt+1));
            }
        }


        System.out.println(cnt);
    }

    public static boolean isRotatable(Status status){
        int row = status.r;
        int col = status.c;
        boolean isPossible  = true;
        for(int i=0; i<8; i++){
            int posY = row +dy[i];
            int posX = col +dx[i];
            if(posY<0||posX<0||posY>=N||posX>=N||map[posY][posX]=='1'){
                isPossible = false;
                break;
            }
        }
        return isPossible;
    }

}
class Status{
    int r;
    int c;
    boolean isHorizontal;
    int cnt;
    public Status(int row, int col, boolean hori, int count){
        r = row;
        c = col;
        isHorizontal = hori;
        cnt = count;
    }
}
