import java.util.*;
import java.io.*;

public class Main {

    static int n,m,r;
    static int[][] arr;
    static int[] dy = {0, 1, 0, -1}; //오른쪽 -> 아래 -> 왼쪽 -> 위
    static int[] dx = {1,0,-1,0}; // 

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }

    public static void input() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        
        arr = new int[n][m];
        
        for(int i = 0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<m; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    public static void pro(){

        // n,m중 작은 값의 절반 만큼 돌려야함
        int k = Math.min(n,m)/2;

        for(int i =0; i<k; i++){
            rotate(i, r);
        }

        show();
    }
    
    
    // (i,i) 를 기준으로 rr 번 회전.
    public static void rotate(int i , int rr){
        
        // 현재 구간에서의 n과 m의 크기,, 원안 으로 들어갈 수록
        // -2 만큼 감소함.
        int nn = n - 2*i;
        int mm = m - 2*i;

        //몇번 회전 시켜야 하지?
        // (rr % 둘레의 갯수) 번 만큼 같은 반복되는 작업이다.
        rr = rr%(2 *nn + 2*mm - 4);

        for(int k=0; k<rr; k++){
            int idx = 0;
            int tmp = arr[i][i];
            int y = i;
            int x = i;
            while(idx < 4){
                int cy = y+ dy[idx];
                int cx = x+ dx[idx];
                if(cy < i || cy >= i+nn || cx<i || cx>=i+mm){
                    idx++;
                    continue;
                }

                arr[y][x] = arr[cy][cx];
                y = cy;
                x = cx;
            }
            arr[i+1][i] = tmp;
        }
    }

    static void show(){
        StringBuilder sb = new StringBuilder();
        for(int i =0; i<n; i++){
            for(int j =0; j<m; j++){
                sb.append(arr[i][j]).append(' ');
            }
            sb.append('\n');
        }
        System.out.println(sb);
    }
}

