import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int com = Integer.parseInt(br.readLine());
        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n][2];
        StringTokenizer st;
        
        for(int i=0; i<n;i++){
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }
        
        boolean[] check = new boolean[com+1];
        int answer = 0;
        Queue<Integer> q = new LinkedList<>();
        check[1] = true;
        q.offer(1);
        
        while(!q.isEmpty()){
            int tmp = q.poll();
            for(int i=0 ; i<n;i++){
                if(arr[i][0] == tmp && !check[arr[i][1]]){
                    check[arr[i][1]] = true;
                    answer++;
                    q.offer(arr[i][1]);
                    
                }else if(!check[arr[i][0]] && arr[i][1] == tmp){
                    check[arr[i][0]] = true;
                    answer++;
                    q.offer(arr[i][0]);
                }
            }
        }
        System.out.println(answer);
    }
}