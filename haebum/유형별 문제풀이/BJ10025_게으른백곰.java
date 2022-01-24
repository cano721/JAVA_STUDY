import java.io.*;
import java.util.*;

/*
    슬라이딩 윈도우로 풀 예정
    
    앨버트가 움직일 수 있는 범위 내에서 고를 수 있는 얼음의 최대값 구하기

    (범위: 윈도우)
*/

public class BJ10025_게으른백곰 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        //배열 생성 및 담기
        int[] arr = new int[1_000_001]; // 좌표 백만개
        for(int i = 0; i < n; i++){
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            int ice = Integer.parseInt(st2.nextToken());
            int x = Integer.parseInt(st2.nextToken());
            arr[x] = ice;
        }
        // 부분 배열의 합계
        int sum = 0;
        // 최대 얼음 수
        int max = 0;
        // 윈도우 크기 (움직일 수 있는 범위 양방향 k*2 현재위치 +1)
        int window = k*2+1;
        for(int i = 0; i <= 1_000_000; i++){
            // 윈도우 크기를 벗어나면 맨 앞에꺼를 자르기
            if(i >= window){
                sum -= arr[i-window];
            }
            // 뒤에 더하기(윈도우 크기 범위까진 더하기만 진행)
            sum += arr[i];

            // 현재 합이 맥스값이면 변경
            max = Math.max(max,sum);
        }
        bw.write(max+"\n");
        bw.flush();
        bw.close();
    }
}