import java.util.*;
import java.io.*;

/**
 * 노드마다 자식이나 깊이를 구하기엔 10^15라 시간초과
 * 
 * 노드가 주어졌을때 부모를 찾는 식
 * 부모노드 = (노드-2)/자식수 +1
 * 
 * 이를통해서 숫자 큰 쪽의 부모노드를 찾고 찾을때마다 거리 1씩 증가(1만큼 움직인것이므로)
 * 두 숫자가 같아졌을때, 거리 출력
 * 
 * 자식 수가 1일땐 이방법을 쓰면 10^15만큼 다 도므로 시간초과
 * 1일땐 그냥 더 큰 숫자에서 더 작은 숫자를 빼면 정답
 */
public class BJ11812_K진트리 {

    public static long n,answer;
    public static int k;
    public static ArrayList<Long>[] graph;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st;
        st = new StringTokenizer(br.readLine());

        n = Long.parseLong(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());

        for(int i = 0; i < q; i++){
            st = new StringTokenizer(br.readLine());
            long a = Long.parseLong(st.nextToken());
            long b = Long.parseLong(st.nextToken());
            //k=1 일때, lca를 돌리면 시간초과
            if(k == 1){
                answer = a<b ? b-a: a-b;
            }else{
                answer = lca(a,b);
            }
            bw.write(answer +"\n");
        }
        bw.flush();
        bw.close();
    }

    public static long lca(long a, long b){
        long dis = 0;
        //둘이 같지 않다면 부모찾기
        while(a != b){
            // b가 더 큰 숫자면 부모 찾고 아니면 a 부모 찾기
            if(a < b){
                b= (b-2)/k +1;
            }else{
                a = (a-2)/k +1;
            }
            //거리 증가
            dis++;
        }
        return dis;
    }
}
