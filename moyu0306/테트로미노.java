import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class Main {

    static int N, M;
    static int[][] map;
    static ArrayList<int[][]> tetrominos = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");

        tetrominos.add(new int[][]{{1,1,1,1}});
        tetrominos.add(new int[][]{{1},{1},{1},{1}});

        tetrominos.add(new int[][]{{1,1},
                                    {1,1}});

        tetrominos.add(new int[][]{{1,0},
                                    {1,0},
                                    {1,1}});
        tetrominos.add(new int[][]{{1,1,1},
                                    {1,0,0}});


        tetrominos.add(new int[][]{{1,0},
                                    {1,1},
                                     {0,1}});
        tetrominos.add(new int[][]{{0,1,1},
                                    {1,1,0}});


        tetrominos.add(new int[][]{{1,0},
                {1,1},
                {1,0}});
        tetrominos.add(new int[][]{{0,1,0},
                                    {1,1,1}});


        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        map = new int[N][M];
        for(int i=0; i<N; i++){
            String[] numbers = br.readLine().split(" ");
            for(int j=0;j<M; j++){
                map[i][j] = Integer.parseInt(numbers[j]);
            }
        }
        int max = 0;
        for(int[][] tetromino : tetrominos){
            int[][] result = tetromino;
           for(int i=0; i<4;i++){
               if( i== 1) result = RLFlip(tetromino);
               else if(i == 2) result =UDFlip(tetromino);
               else if(i== 3) result = UDFlip(RLFlip(tetromino));
               int val =moveAndMatch(result);
               max= Integer.max(max,val);
           }
        }
        System.out.println(max);
    }
    public static int[][] RLFlip(int[][] tetromino){
        int rlen = tetromino.length;
        int clen = tetromino[0].length;
        int[][] result = new int[rlen][clen];

        for(int i=0; i<rlen; i++){
            for(int j=0; j< clen; j++){
                result[i][j] = tetromino[i][clen-1-j];
            }
        }

        return result;
    }


    public static int[][] UDFlip(int[][] tetromino){
        int rlen = tetromino.length;
        int clen = tetromino[0].length;
        int[][] result = new int[rlen][clen];

        for(int i=0; i<rlen; i++){
            for(int j=0; j< clen; j++){
                result[i][j] = tetromino[rlen-1-i][j];
            }
        }
        return result;
    }

    public static int moveAndMatch(int[][] tetromino){
        int rlen = tetromino.length;
        int clen = tetromino[0].length;
        int max  = 0;

        for(int i=0; i<N-rlen+1;i++){
            for(int j=0;j<M-clen+1;j++){ // map 에서 순회
                int sum = 0;
                for(int k=0; k<rlen; k++){//tetromino match
                    for(int l=0; l<clen; l++){
                        if(tetromino[k][l] == 1) sum += map[i+k][j+l];
                    }
                }
                max= Integer.max(max,sum);
            }
        }

        return max;
    }
}