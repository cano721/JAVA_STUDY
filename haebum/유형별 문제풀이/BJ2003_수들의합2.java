import java.io.*;
import java.util.*;

/*
    투포인터로 풀 예정
    n개의 수로 된 수열에서
    일정부분의 합이 m이 되는 경우의 수 찾기

    1. start, end로 인덱스를 지정하기
    2. start 0 end를 증가 시켜가면서 m과 비교하기
    3. m과 같아지면 경우의수 증가 start+1
    4. m보다 크면 start+1
    5. m보다 작으면 계속 end 증가
    6. 최종 끝까지 다다랐을때 반복문을 탈출하면서 경우의 수 출력
*/

public class BJ2003_수들의합2 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        //배열 생성 및 담기
        int[] arr = new int[n];
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(st2.nextToken());
        }

        int start = 0;
        int end = 0;
        int sum = 0;
        int cnt = 0;
        while(true){
            // 현재 합이 m보다 크거나 같으면 스타트를 올리고 첫번째 값 빼기
            if(sum >= m){
                sum -= arr[start++];
            }
            // 마지막까지 도달했으면 종료
            else if(end == arr.length){
                break;
            }
            // 현재 합이 m보다 작으면 end값을 더하고 다음 포인트로 이동
           else{
                sum += arr[end++];
            }
            if(sum == m){
                cnt++;
            }
        }
        bw.write(cnt+"\n");
        bw.flush();
        bw.close();
    }
}
