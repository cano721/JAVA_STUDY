import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static Org[] sharkInfo;
    static int[][][] dir;
    static Org[][] map;
    static int[] dy = new int[]{100,-1,1,0,0};
    static int[] dx = new int[]{100,0,0,-1,1};
    static int N,M,K; //
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        K = Integer.parseInt(input[2]);

        sharkInfo = new Org[M + 1];
        dir = new int[M + 1][5][4];
        map = new Org[N][N];

        for (int i = 0; i < N; i++) {
            String[] nums = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                int type = Integer.parseInt(nums[j]);
                Org shark = new Org(type, i, j, 0);
                sharkInfo[type] = shark;
                map[i][j] = shark;
            }
        }
        String[] currdir = br.readLine().split(" ");

        for (int i = 1; i < currdir.length + 1; i++) {
            sharkInfo[i].dir = Integer.parseInt(currdir[i - 1]);
        }

        for (int i = 1; i < M + 1; i++) {
            for (int j = 1; j < 5; j++) {
                String[] dirs = br.readLine().split(" ");
                for (int k = 0; k < 4; k++) {
                    dir[i][j][k] = Integer.parseInt(dirs[k]);
                }
            }
        }

        int t = 0;
        boolean moveNext = true;

        while (t <= 1000 && moveNext ) {
            moveNext= moveAll();
            t++;
        }
        if(t>1000) t= -1;
        System.out.println(t);
    }
    public static boolean moveAll(){
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                Org org = map[i][j];
                System.out.print(org.type+" ");
                if(org.type>0){
                    moveShark(org);
                }else if(org.type<0) {
                    org.dur--;
                }
            }
            System.out.println("");

        }
        System.out.println("");
        if(placeShark(M) == 1) return false;
        else return true;
    }


    public static void moveShark(Org org){
        int t = org.type;
        int d= org.dir;
        int r = org.row;
        int c = org.col;
        int N = map.length;
        for(int k : dir[t][d]){
            int posR = r + dy[k];
            int posC = c + dx[k];
            if(posR<0||posR>=N||posC<0||posC>=N||map[posR][posC].type!=0) continue;
            changeSharkInfo(t,k,r,c,posR,posC);
            return;
        }
        for(int k: dir[t][d]){
            int posR = r + dy[k];
            int posC = c + dx[k];
            if(posR<0||posR>=N||posC<0||posC>=N) continue;
            if(map[posR][posC].type == -t){
                changeSharkInfo(t,k,r,c,posR,posC);
                return;
            }
        }

    }

    public static void changeSharkInfo(int type, int d, int r, int c ,int posR, int posC){
        map[r][c] = new Org(-type,r,c,K-1);
        sharkInfo[type].row = posR;
        sharkInfo[type].col = posC;
        sharkInfo[type].dir = d;

    }

    public static int placeShark(int M){
        int cnt = 0;

        for(int i=1; i<M+1; i++){
            int r = sharkInfo[i].row;
            int c = sharkInfo[i].col;
            if(sharkInfo[i].type > 0){
                if(map[r][c].type <=0){
                    map[r][c] = sharkInfo[i];
                    cnt++;
                }
                else if(map[r][c].type > sharkInfo[i].type){
                    map[r][c].type = 0;
                    map[r][c] = sharkInfo[i];
                }else{
                    sharkInfo[i].type =0;
                }
            }
        }
        for(int i=0; i<N; i++){
            for(int j=0; j<N;j++){
                Org org = map[i][j];
                if(org.type <0 &&org.dur ==0) org.type =0;
            }
        }
        return cnt;
    }


}

class Org{
    int type;
    int row;
    int col;
    int dir= -1;
    int dur=0;

    public Org(int t, int r, int c, int d){
        type = t;
        row = r;
        col = c;
        dur = d;
    }

}