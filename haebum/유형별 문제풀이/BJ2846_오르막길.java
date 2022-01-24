import java.util.*;
import java.io.*;

public class BJ2846_오르막길 {
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 측정한 높이의 수
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        
        // 각각의 높이를 배열에 담기
        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 정답
        int answer = 0;

        // n이 1이면 오르막길이 없는것이므로 0 출력
        if(n == 1){
            System.out.println(0);
        }else{
            // 현재 오르막길의 높이 체크
            int temp = 0;
            for(int i = 1; i < n; i++){
                // 오르막길이면 높이 증가
                if(arr[i-1] < arr[i]){
                    temp += arr[i]-arr[i-1];
                // 평지이거나 내리막길이면 초기화
                }else{
                    temp = 0;
                }
                // 매번 정답과 비교하여 최대값으로 변환
                answer = Math.max(temp,answer);
            }
        }
        
        System.out.println(answer);
    }
}


