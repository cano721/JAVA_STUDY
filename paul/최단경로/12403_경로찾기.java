import java.util.*;
import java.io.*;

public class Main {
    
    static int n;
    static int[][] map= new int[101][101];

    public static void main(String[] args) throws Exception {
       
       input();
       pro();
    }

    static void input() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        for(int i =0; i<n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }        
    }

    static void pro(){
        for(int k=0; k<n; k++){
            for(int i =0; i<n; i++){
                for(int j =0; j<n; j++){
                    if(map[i][k] == 1 && map[k][j] == 1){
                        map[i][j] = 1;
                    }

                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i =0; i<n; i++){
            for(int j =0; j<n; j++){
                sb.append(map[i][j] + " ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }
}
