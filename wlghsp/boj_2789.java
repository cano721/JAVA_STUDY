package baekjoon.bronzeⅡ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*

5 21
5 6 7 8 9

21

10 500
93 181 245 214 315 36 185 138 216 295

497
*/

// 브루트 포스, 전체 경우의 수를 다 적용하여 적합한 값을 찾아냄 


public class boj_2789 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        
        int N = Integer.parseInt(st.nextToken());
        
        int M = Integer.parseInt(st.nextToken());
        
        int[] arr = new int[N];
        
        st = new StringTokenizer(br.readLine(), " ");

        for (int i = 0; i <N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        
        int result = search(arr, N, M);
        System.out.println(result);
        
    }
    
    static int search(int[] arr, int N, int M) {
        int result = 0;
        
        
        // 3개를 고르기 때문에 첫 번째 카드는 N-2만 순회
        for(int i = 0; i <N-2; i++) {
            
            // 첫 번째 카드가 M보다 크면 skip
            if (arr[i] > M) {
                continue;
            }
            
            // 두 번째 카드는 첫 번째 카드 다음부터 N - 1 까지만 순회
            for (int j = i+1; j < N-1; j++){

                // 두 번째 카드와 첫 번째 카드의 합이 M보다 크면 skip
                if (arr[i] + arr[j] > M) {
                    continue;
                }


                //세 번째 카드는 두 번째 카드 다음부터 N까지 순회
                for (int k = j + 1; k < N; k++) {
                    // 3개 카드의 합 변수 temp
                    int temp = arr[i] + arr[j] + arr[k];

                    // M과 세 카드의 합이 같다면 temp return 및 종료
                    if (M == temp) {
                        return temp;
                    }

                    // 세 카드의 합이 이전 합보다 크면서 M 보다 작을 경우 result 갱신 
                    if (result < temp && temp < M) {
                        result = temp; 
                    }
                }
            }
        }

        // 모든 순회를 마치면 result 리턴
        return result;
    }
}
