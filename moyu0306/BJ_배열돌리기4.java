import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
        static int[][] map;
        static int[] dy = new int[]{0,1,0,-1};
        static int[] dx = new int[]{1,0,-1,0};
        static ArrayList<int[]> list = new ArrayList<>();
        static int min = 5000;

    public static void main(String[] args) throws IOException {
        // write your code here
        InputStreamReader ir = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(ir);

        String[] input =br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);
        int K = Integer.parseInt(input[2]);
        map = new int[N][M];

        for(int i=0; i<N; i++){
            String[] numbers = br.readLine().split(" ");
            for(int j=0; j<M; j++){
                map[i][j] = Integer.parseInt(numbers[j]);
            }
        }
        for(int k=0; k<K; k++){
            String[] numbers = br.readLine().split(" ");
            list.add(new int[]{Integer.parseInt(numbers[0]),Integer.parseInt(numbers[1]),Integer.parseInt(numbers[2])});
        }
        int[] sch = new int[K];
        for(int i=0; i<K;i++) sch[i] = i;
        perm(sch,0,K,0);
        System.out.println(min);

    }

    public static void rotate(int[][] mapc,int[] order,int dir, boolean clockwise){
        int rm = order[0];
        int cm = order[1];
        int s = order[2];
        int r =rm -s - 1;
        int c =cm -s - 1;
        int size = 2*s +1;
        int ceil = r-1;
        int floor = r+size;
        int left = c -1;
        int right = c +size;
        int y = r, x =c;
        int ny =-1, nx =-1;

        int tmp = mapc[r][c];
        while(ny!= r ||nx != c){
            ny = y +dy[dir];
            nx = x +dx[dir];

            if(ny>=floor||ny<=ceil||nx<=left||nx>=right){
                dir = (clockwise)? (dir+3)%4 : (dir+1)%4 ;
                continue;
            }

            if(ny!=r || nx!=c) mapc[y][x] = mapc[ny][nx];
            else mapc[y][x] = tmp;

            x = nx;
            y = ny;
        }
    }

    public static int[][] rotateAll(int[] order, int[][] mapc){
       int p = order[2];

       for(;p>0;p--){
           rotate(mapc,new int[]{order[0],order[1],p},3,true);
       }
       return mapc;
    }

    public static void perm(int[] sch,int idx, int n, int r){

        if(n==r){
            int cnt =0;
            int[][] result = clone(map);
            for(int k : sch) {
                rotateAll(list.get(k), result);
            }
            cnt = cnt(result);
            min = Integer.min(min,cnt);
        }

        for(int i=idx; i<n; i++){
            swap(sch,idx,i);
            perm(sch,idx+1,n,r+1);
            swap(sch,idx,i);
        }
    }
    public static void swap(int[] sth,int i, int j){
        int temp = sth[i];
        sth[i] = sth[j];
        sth[j] = temp;
    }

    public static  int[][] clone(int[][] original){
        int[][] clone = new int[original.length][original[0].length];
        for(int i=0;i< original.length;i++){
            for(int j=0;j<original[0].length; j++){
                clone[i][j] = original[i][j];
            }
        }
        return clone;
    }

    public static int cnt(int[][] arr){
        int mini = 50000;
        for(int i=0; i<arr.length; i++){
            int num = 0;
            for(int j=0;j<arr[0].length;j++){
                num += arr[i][j];
            }
            mini = Integer.min(mini,num);
        }
        return mini;
    }

    public static void print(int[][] arr){
        for(int i=0; i<arr.length; i++){
                 for(int j=0;j<arr[0].length;j++){
                System.out.print(arr[i][j]+" ");
            }
            System.out.println("");
        }
    }
}