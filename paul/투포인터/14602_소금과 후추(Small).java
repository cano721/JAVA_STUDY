import java.util.*;
import java.io.*;
/**
 *  입력값이 매우 작아서 시간초과 안남.
 */
public class Main {
    static int n, m, k, w;
    static int[][] arr = new int[31][31];
    static int[][] b = new int[31][31];
    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
    
    public static void input() throws Exception{
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       StringTokenizer st = new StringTokenizer(br.readLine());
       m = Integer.parseInt(st.nextToken());
       n = Integer.parseInt(st.nextToken());
       k = Integer.parseInt(st.nextToken());
       w = Integer.parseInt(st.nextToken()); 
       
       for(int i =0; i<m; i++){
           st = new StringTokenizer(br.readLine());
           for(int j= 0 ;j<n; j++){
               arr[i][j] = Integer.parseInt(st.nextToken()); 
           }
       }
    }

    public static void pro(){
        StringBuilder sb = new StringBuilder();
        /**
         * 행렬 b의 크기 : bm x bn 
         * bm : [0, m-w+1), bn : [0, n-w+1)  
         */
        int bm = m-w+1, bn = n-w+1;
        for(int i =0; i<bm; i++){
            for(int j=0; j<bn; j++){
                b[i][j] = median(i,j);
                sb.append(b[i][j]).append(' ');
            }
            sb.append('\n');
        }

        System.out.println(sb);
    }

    public static int median(int y, int x){

        List<Integer> list = new ArrayList<>();
        for(int i =y; i< y+w; i++){
            for(int j = x; j< x+w; j++){
                list.add(arr[i][j]);
            }
        }

        Collections.sort(list);

        return list.get(w*w/2);
    }

}