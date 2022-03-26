import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    static Fish[][] map;
    static int[] dy = new int[]{-1, -1,0,1,1,1,0,-1};
    static int[] dx = new int[]{0,1,1,1,0,-1,-1,-1};
    public static void main(String[] args) throws IOException {
        // write your code here
        InputStreamReader ir = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(ir);
        map = new Fish[4][4];
        for(int i=0; i<4; i++)
        {
            String[] input = br.readLine().split(" ");
            for(int k=0; k<4; k++){
                map[i][k] = new Fish(Integer.parseInt(input[k*2]),Integer.parseInt(input[2*k+1])-1);
            }
        }

        map[0][0].isShark = true;
        map[0][0].eat += map[0][0].num;
        int answer = moveAll(map[0][0],0,0,map);
        System.out.println(answer);
    }


    public static int moveAll(Fish shark, int r, int c, Fish[][] cmap){
        int posY, posX, cnt = shark.eat;

        for(int i=0; i<16; i++){
            int[] pos = findFish(i,cmap);
            if(pos[0] == -1) continue;
            posY = pos[0];
            posX = pos[1];
            moveFish(cmap[posY][posX],posY,posX,cmap);
        }

        for(int i=1;i<4; i++){
            posY = r + dy[shark.dir]*i;
            posX = c + dx[shark.dir]*i;
            if(posY<0||posX<0||posY>=4||posX>=4||cmap[posX][posY].eaten) continue;
            Fish[][] nmap = copyMap(cmap);
            swapFish(r,c,posY,posX,nmap);
            nmap[r][c].eaten = true;
            shark.eat += nmap[r][c].num;
            cnt =Integer.max(cnt,moveAll(shark,posY,posX,cmap));
        }
        return cnt;
    }

    public static int[] findFish(int idx,Fish[][] cmap){
        for(int i=0; i<4; i++){
            for(int j=0; j<4; j++){
               if((cmap[i][j].num == idx)&&!cmap[i][j].eaten) return new int[]{i,j};
            }
        }
        return new int[]{-1,-1};
    }

    public static Fish[][] moveFish(Fish fish, int r, int c, Fish[][] cmap){
        boolean flag = true;
        int posY =0;
        int posX =0;
        int pastDir = fish.dir;
        while(flag){
            flag = false;
            posY = r +dy[fish.dir];
            posX = c +dx[fish.dir];
            if(posY<0||posX<0||posY>=4||posX>=4||cmap[posX][posY].isShark){
                fish.dir = (fish.dir+1)%8;
                flag = true;
            }
            if (fish.dir == pastDir) break;
        }
        if(fish.dir!=pastDir) swapFish(r,c,posY,posX, cmap);
        return cmap;
    }

    public static void swapFish(int r1, int c1, int r2, int c2, Fish[][] cmap){
        Fish temp = cmap[r1][c1];
        cmap[r1][c1] = cmap[r2][c2];
        cmap[r2][c2] = temp;
    }

    public static Fish[][] copyMap(Fish[][] map){
        Fish[][] nmap = new Fish[4][4];
        for(int i=0; i<map.length; i++){
            for(int j=0; j< map.length;j++){
                nmap[i][j] =new Fish(map[i][j]);
            }
        }
        return nmap;
    }

}
class Fish{
    int num;
    int dir;
    boolean isShark = false;
    boolean eaten = false;
    int eat = 0;
    public Fish(int n, int d){
        num = n;
        dir = d;
    }
    public Fish(Fish past){
        num = past.num;
        dir = past.dir;
        isShark = past.isShark;;
        eaten = past.eaten;
        eat = past.eat;
    }
}