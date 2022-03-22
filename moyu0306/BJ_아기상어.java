import java.io.*;
import java.util.LinkedList;
import java.util.Queue;


public class Main {
    static int[][] map;
    static Shark babyShark;
    static int[] dy = new int[]{ -1, 0, 0, 1};
    static int[] dx = new int[]{0,-1,1, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for(int i=0; i< N; i++){
            String[] row = br.readLine().split(" ");
            for(int j=0; j< N ; j++){
                int num = Integer.parseInt(row[j]);
                if(num == 9) {babyShark = new Shark(i,j); map[i][j]= 0; continue;}
                map[i][j] = num;

            }
        }
        int time = -1;
        int answer = 0;
        while( time != 0){
//            System.out.println(babyShark.row+" "+babyShark.col+" "+time+" "+ babyShark.size);

            time = eat(findPrey(map,babyShark));
            answer += time;

        }

        System.out.println(answer);
    }

    public static int[] findPrey(int[][] map,Shark shark){
        int len = map.length;
        int size  = shark.size;
        int max = Integer.MAX_VALUE;
        int[] prey = new int[]{max,max,max};
        boolean[][] isVisited = new boolean[len][len];
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{shark.row,shark.col,0});

        isVisited[shark.row][shark.col] = true;
        while(!q.isEmpty()){
            int[] pos = q.poll();
            int row = pos[0];
            int col = pos[1];
            int time = pos[2];
            for(int i=0; i<4; i++){
                int r = row+ dy[i];
                int c = col+ dx[i];

                if(r<0||r>=len||c<0||c>=len||isVisited[r][c]||map[r][c]>size) continue;
                if(map[r][c]!= 0 && map[r][c]<size){
                    int[] cand = new int[]{r,c,time+1};
                    if(time+1<prey[2]){
                        prey = cand;
                    } else if(time +1 == prey[2] && r< prey[0]) prey = cand;
                    else if(time + 1 == prey[2] && r == prey[0] && c< prey[1]) prey = cand;
                }
                isVisited[r][c] = true;
                q.offer(new int[]{r,c,time+1});
            }
        }
        return prey;
    }
    public static int eat(int[] prey){
        if(prey[0] == Integer.MAX_VALUE) return 0;
        babyShark.row = prey[0];
        babyShark.col = prey[1];
        babyShark.eat++;
        if(babyShark.eat == babyShark.size){
            babyShark.eat =0;
            babyShark.size++;
        }
        map[babyShark.row][babyShark.col] = 0;

        return prey[2];
    }

}

class Shark{
    int row;
    int col;
    int size = 2;
    int eat = 0;

    public Shark(int r, int c){
        row = r;
        col = c;
    }
}