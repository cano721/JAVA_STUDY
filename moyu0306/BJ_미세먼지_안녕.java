import java.io.*;
import java.util.ArrayList;


public class Main {
        static int[][] map;
        static ArrayList<int[]> ac;
        static int[] dy = new int[]{0,0,1,-1}; // 동 서 남 북
        static int[] dx = new int[]{1,-1,0,0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int R = Integer.parseInt(input[0]);
        int C = Integer.parseInt(input[1]);
        int T = Integer.parseInt(input[2]);
        map = new int[R][C];
        ac = new ArrayList<>();
        for(int i=0; i<R; i++){
            String[] nums = br.readLine().split(" ");
            for(int j=0; j<C;j++){
                int num = Integer.parseInt(nums[j]);
                map[i][j] = num;
                if(num == -1) ac.add(new int[]{i,j});
            }
        }

        for(int i=0; i<T; i++){
            spread();

            clockwise(ac.get(0),C-1);
            counterClockwise(ac.get(1),R-1,C-1);
        }
        int cnt =0;
        for(int i=0;i<R; i++){
            for(int j=0; j<C; j++){
                cnt+=map[i][j];

            }
        }
        System.out.print(cnt+2);
    }
    public static void spread(){
        int rlen = map.length;
        int clen = map[0].length;
        int [][] tmp = new int[rlen][clen];
        for(int i=0; i< rlen; i++){
            for(int j=0; j<clen ;j++){
                int val = map[i][j];
                if(val == -1) continue;
                int moved= val/5;
                for(int k=0; k<4; k++){
                    int r = i+dy[k];
                    int c = j+dx[k];
                    if(r<0||c<0||r>=rlen||c>=clen||map[r][c] == -1) continue;
                    tmp[r][c] += moved;
                    map[i][j] -= moved;
                }
            }
        }

        for(int i=0; i<rlen; i++){
            for(int j=0; j<clen; j++){
                if(map[i][j] == -1) continue;
                map[i][j] += tmp[i][j];
            }
        }

    }
    public static void clockwise(int[] ac,int cEnd){
        int r = ac[0];
        int c = ac[1];

        rotate(2,0,0,r,c);
        rotate(1,0,cEnd,0,0);
        rotate(3,r,cEnd,0,cEnd);
        rotate(0,r,c,r,cEnd);

        map[r][c] = -1;
        map[r][c+1] = 0;
    }


    public static  void counterClockwise(int[] ac,int rEnd, int cEnd){
        int r = ac[0];
        int c = ac[1];

        rotate(3,rEnd,c,r,c);
        rotate(1,rEnd,cEnd,rEnd,c);
        rotate(2,r,cEnd,rEnd,cEnd);
        rotate(0,r,c,r,cEnd);
        map[r][c] = -1;
        map[r][c+1] = 0;
    }


    public static void rotate(int dir, int r1, int c1,int r2,int c2){
        int rmove = dy[dir];
        int cmove = dx[dir];

        while(r1!= r2 || c1!=c2){
            map[r2][c2] = map[r2-rmove][c2-cmove];
            r2 -= rmove;
            c2 -= cmove;
        }

    }
    public static void print(int R, int C){
        for(int i=0;i<R; i++){
            for(int j=0; j<C; j++){
                System.out.print(map[i][j]+" ");

            }
            System.out.println("");
        }
        System.out.println("");
    }

}