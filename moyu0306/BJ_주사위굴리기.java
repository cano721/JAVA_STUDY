import java.io.*;


public class Main {
    static int[] dice;
    static int [][] movePath =new int[][]{ // 확실한 로직을 세우고 들어갈 것. 편한 것보다는 정확한 것 선택.
                                        {2,0,5,3,4,1},
                                        {1,5,0,3,4,2},
                                        {3,1,2,5,0,4},
                                        {4,1,2,0,5,3}
                                };
    static int N;
    static int M;
    static int row;
    static int col;
    static int[][] map;
    static final int HEAD =0;
    static final int BOT = 5;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
         N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        row = Integer.parseInt(input[2]);
        col = Integer.parseInt(input[3]);
        int num = Integer.parseInt(input[4]);
        dice = new int[6];
        map = new int[N][M];


        for(int i=0; i<N; i++){
            String[] splits = br.readLine().split(" ");
            for(int j=0; j<M;j++){
                map[i][j] = Integer.parseInt(splits[j]);
            }
        }

        String[] order = br.readLine().split(" ");
        for(String str : order){
            int dir = Integer.parseInt(str);
            int val;
            if((val=move(dir)) != -1)  System.out.println(val);
        }


    }
    public static int move(int dir){
        int[] rdir = new int[]{0, 0, -1, 1};
        int[] cdir = new int[]{1, -1, 0, 0};
        int trueDir = dir - 1;
        int r = row + rdir[trueDir];
        int c = col + cdir[trueDir];
        if(r<0||r>=N||c<0||c>=M) return -1;
        row = r; col = c;
        roll(trueDir);

        if(map[row][col] == 0) map[row][col] = dice[BOT];
        else{
            dice[BOT] = map[row][col];
            map[row][col] = 0;
        }
        return dice[HEAD];
    }
    public static void roll(int dir){
        int[] description =movePath[dir];
        int[] copy = dice.clone();
        for(int i = 0 ; i<6; i++){
            copy[i] = dice[description[i]];
        }
        dice = copy;
    }
}