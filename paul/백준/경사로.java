import java.util.*;
import java.io.*;
import java.nio.Buffer;


public class Main {
    
    static int n , l;
    static int[][] board = new int[101][101];

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }

    public static void input() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        n = Integer.parseInt(s[0]);
        l = Integer.parseInt(s[1]);

        for(int i =0; i<n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j =0; j<n; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    public static int checkLoad(int ro, int pivot){
        
        int cnt = 1;
        int prev = ro == 0 ? board[pivot][0]:board[0][pivot];
        int idx = 1;
        while(idx < n){
            int now = ro == 0 ? board[pivot][idx]:board[idx][pivot];
            if(prev == now){
                cnt++;
            }else{
                if( Math.abs(prev -now ) > 1) return 0; 
            
                // prev가 더 높은 경우 
                // now랑 같은 값을 가지는 idx가 L개 있을 때까지 continue
                if(prev > now){

                    int now_cnt = 1;
                    while(now_cnt != l) {
                        if(idx == n-1) break;
                        if(ro == 0 && board[pivot][++idx] != now) break; 
                        else if( ro == 1 && board[++idx][pivot] != now) break;
                        now_cnt++;
                    }

                    if(now_cnt != l) return 0;
                    cnt = 0;
                    prev = now;

                }else{
                    // now가 더 높은 경우
                    // cnt가 L가 이상있는지 확인해야함.
                    if( cnt < l) return 0;
                    cnt =1;
                    prev = now;
                }
            }
            idx++;
        }
        
        return 1;
    }

    public static void pro(){
        
        int answer = 0;
        //2*n개 확인
        for(int i = 0; i<2; i++){
            for(int j =0; j<n; j++){
                answer += checkLoad(i, j);
            }
        }


        System.out.println(answer);
    
    }
    
    
}

