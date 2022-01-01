import java.io.*;
import java.nio.Buffer;
import java.util.*;

public class Main {

    static int n, m, k;
    static List<Integer>[] list;
    static int[] indegree;
    static int[] built;
    public static void main(String[] args) throws IOException {
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       StringTokenizer st = new StringTokenizer(br.readLine());
       n = Integer.parseInt(st.nextToken());
       m = Integer.parseInt(st.nextToken());
       k = Integer.parseInt(st.nextToken());
       list = new ArrayList[n+1];
       for(int i=0; i<n+1; i++) list[i] = new ArrayList<>();
       indegree = new int[n+1];
       built = new int[n+1];

       for(int i =0; i<m; i++){
           st = new StringTokenizer(br.readLine());
           int prev = Integer.parseInt(st.nextToken());
           int next = Integer.parseInt(st.nextToken());
           list[prev].add(next);
           indegree[next]++;
       } 

       String ans = "King-God-Emperor";

       for(int i =0; i<k; i++){
           st = new StringTokenizer(br.readLine());
           int action = Integer.parseInt(st.nextToken());
           int number = Integer.parseInt(st.nextToken());

           if(action == 1){
               if(indegree[number] > 0 ) {
                   // 지울 수 없음.. 이전 조건 만족 못함.
                   ans = "Lier!";
                   break;
               }
               // 지울 수 있음.
               built[number]++;
               //연결된거 indegree 삭제
               for(int n : list[number]) indegree[n]--;
           }else{
               if(built[number] == 0 ){
                   ans = "Lier!";
                   break;
               }

               built[number]--;
               //연결된 indegree 증가.
               for(int n : list[number]) indegree[n]++;
           }
       }

       System.out.println(ans);
    }

   

    
    

}