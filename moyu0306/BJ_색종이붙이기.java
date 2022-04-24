import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;


public class Main {
    static int[][] map;
    static int[] count;
    static int min = Integer.MAX_VALUE;
    static boolean flag = false;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        count = new int[6];
        map = new int[10][10];
        Arrays.fill(count,5);

        for(int i=0; i<10; i++){
            String[] input = br.readLine().split(" ");
            for(int j=0; j<10; j++){
                map[i][j] = Integer.parseInt(input[j]);
            }
        }
        countMap(0,0);
        DFS(9,9,0);
        if (!flag) System.out.println(0);
        else if(min != Integer.MAX_VALUE) System.out.println(min);
        else System.out.println(-1);

    }
    public static void countMap(int a, int b){
        for(int i= a ; i<10; i++){
            for(int j= b ; j<10 ; j++){
                if(i<1||j<1||map[i][j]==0) continue;
                int val = Integer.min(Integer.min(map[i-1][j],map[i][j-1]),map[i-1][j-1]);
                if(val > 0) map[i][j] = Integer.min(5, val+1);
                else map[i][j] = 1;
            }
        }
    }

    public static void removePaper(int a, int b){
        int val = map[a][b]-1;
        for(int i = a- val; i<= a; i++){
            for(int j= b- val; j<=b; j++){
                map[i][j] = 0;
            }
        }
    }

    public static void recoverPaper(int a, int b, int val){
        val = val -1;
        for(int i= a - val; i<=a; i++){
            for(int j = b-val; j<=b; j++){
                map[i][j] = 1;
            }
        }
    }

    public static void DFS(int a, int b, int cnt){
        int current  = 10* a + b;
        for(int i= 9; i>0; i--){
            for(int j= 9; j>0 ; j--){
                if(10*i+j>current) continue;
                if(map[i][j]>1&&cnt<min){
                    flag = true;
                    int val = map[i][j];
                    if(count[val] == 0) return;
                    else count[val]--;
                    cnt++;
                    removePaper(i,j);
                    countMap(i-val,j-val);
//                    debug();
                    DFS(i,j,cnt);
                    recoverPaper(i,j,val);
                    countMap(i-val,j-val);
                    cnt --;
                    count[val]++;
//                    debug();
                }
            }
        }

        for(int i=0; i<10; i++){
            for(int j=0; j<10; j++){
                if(map[i][j]>0){
                    flag = true;
                    cnt++;
                    count[1]--;
                }
            }
        }
        if(count[1]<0){count[1] = 5; return;}
        else count[1] = 5;

        if(min> cnt){
            min = Integer.min(cnt,min);
        }
    }
}