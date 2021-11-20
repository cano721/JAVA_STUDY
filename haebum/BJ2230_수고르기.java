import java.io.*;
import java.util.*;

/*
    배열 정렬

    start end 두개 포인터로

    원소들을 비교하여 m 이상일때 차이값 비교

*/

public class BJ2230_수고르기 {

    public static int n,m,answer;
    public static int[] arr;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        
        //배열 생성 및 담기
        arr = new int[n];
        for(int i = 0; i < n; i++){
            int num = Integer.parseInt(br.readLine());
            arr[i] = num;
        }

        // 배열 정렬
        Arrays.sort(arr);

        // 정답 담기
        answer = Integer.MAX_VALUE;
        // 두 수 비교
        twoPoint(0,0);

        bw.write(answer +"\n");
        bw.flush();
        bw.close();
    }

    public static void twoPoint(int start, int end){
        while(true){
            //범위 벗어나면 탈출
            if(end == n || start == n) break;
            // 두 수의 차이
            int d = arr[end] - arr[start];
            // 차이가 m 이상이면
            if(d > m){
                answer = Math.min(answer,d);
                start++;
            }else if(d < m){
                end++;
            }else{
                answer = m;
                break;
            }
        }
    }
}
