import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static int[][] map;
    static boolean found = false;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new int[9][9];
        for(int i=0; i<9; i++){
            String[] nums=br.readLine().split("");
            for(int j=0; j<9; j++){
                map[i][j] = Integer.parseInt(nums[j]);
            }
        }
        DFS(0,0);



    }
    public static void DFS(int i, int j){
        if(i ==8 && j == 9){
            printMap();
            found = true;
            return;
        }
        if(found) return;

        if(j == 9) DFS(i+1,0);
        else if(map[i][j]!= 0) DFS(i,j+1);
        else{
            boolean[] number = new boolean[10];
            checkNum(i,j,number);

            for(int m=1; m<10; m++){
                if(!number[m]){
                    map[i][j] = m;
                    DFS(i,j+1);
                    map[i][j] = 0;
                }
            }
        }



    }
    public static void checkNum(int i, int j,boolean[] number){
        int row = i/3 *3;
        int col = j/3 *3;

        for(int m =0; m<3; m++){
            for(int n=0; n<3; n++){
                number[map[row+m][col+n]] = true;
            }
        }

        for(int m=0; m<9; m++){
            number[map[m][j]] = true;
            number[map[i][m]] = true;
        }

    }
    public static void printMap(){
        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                System.out.print(map[i][j]);
            }
            System.out.println("");
        }
    }
}