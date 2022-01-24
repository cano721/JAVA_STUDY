/*
    투포인터를 이용해서 풀 예정

    start 와 end 포인터를 가지고(인덱스를 뜻함)
    부분수열의 합을 구해서
    현재 합이 S보다 작으면 end를 늘려 부분수열의 범위를 넓히고
    S보다 크면 start를 늘려 부분수열의 범위를 좁힐것

    해당 범위의 합이 S보다 클때 end-start를 통해
    해당 범위의 길이가 최소길이인지 확인할 것
*/

import java.util.*;
import java.io.*;

public class BJ1806_부분합 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());

        //배열 담기
        int[] arr = new int[n];
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(st2.nextToken());
        }

        int sum = 0; // 부분수열의 합
        int minLength = Integer.MAX_VALUE; // 부분수열의 최소길이

        // 투포인터
        int start= 0;
        int end = 0;

        while(true){
            // 합계가 작으면 배열[end] 더하고 end포인터 증가
            if(sum < s && end < arr.length){
                sum += arr[end++];
            // 합계가 크거나 같으면 배열[start]를 빼고 start포인터 증가
            }else{
                sum -= arr[start++];
            }
            // 현재 부분수열이 s보다 크거나 같을때 길이 체크
            if(sum >= s){
                minLength = Math.min(minLength,end-start);
            }
            // 배열에 끝까지 다 했다면 종료
            if(end == arr.length && start == arr.length){
                break;
            }
        }
        // 합이 만드는 것이 불가능하다면 0 출력
        if(minLength == Integer.MAX_VALUE){
            System.out.println(0);
        }else{
            System.out.println(minLength);
        }
    }
}
