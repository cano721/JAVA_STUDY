package BruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj2798_BlackJack {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        // 입력받기
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // 배열 생성
        int[] num = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(blackJack(num, N, M));
    }

    public static int blackJack(int[] num,int N,int M) {
        int ans = 0;

        for(int i=0; i<N-2; i++) { // 첫번째 카드
            for(int j=i+1; j<N-1; j++) { // 두번째 카드
                for(int k=j+1; k<N; k++) { // 세번째 카드
                    int sum = num[i] + num[j] + num[k];

                    // 세수의 합이 M과 일치하면 return
                    if(M == sum)
                        return sum;

                    // ans < sum < M 사이의 가장 근접한 값 넣어줌
                    if( (ans<sum) && (sum<M) ) {
                        ans = sum;
                    }
                }
            }
        }

        return ans;
    }
}
