import java.io.*;
import java.util.*;

/*
    배열 정렬

    두개의 용액 더한 값이
    음수면 start 포인터 인덱스 증가
    양수면 end 포인터 인덱스 감소

    0이면 두 용액을 찍고 탈출
    아니면 배열의 끝까지 진행하며 최소값 담아두기

*/

public class BJ2470_두용액 {

    public static int minNum,maxNum;
    public static int[] arr;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        //배열 생성 및 담기
        arr = new int[n];
        for(int i = 0; i < n; i++){
            int num = Integer.parseInt(st.nextToken());
            arr[i] = num;
        }

        // 배열 정렬
        Arrays.sort(arr);

        // 포인터
        minNum = 0;
        maxNum = n-1;

        // 정답 담기
        int check = Integer.MAX_VALUE;
        // 두용액 최소값 구하기
        twoPoint(minNum,maxNum,check);

        bw.write(arr[minNum] + " " + arr[maxNum]);
        bw.flush();
        bw.close();
    }

    public static void twoPoint(int start, int end, int check){
        while(start < end){
            // 두 용액의 합(절대값)
            int sum = arr[start] + arr[end];
            // 현재 값이 최소값이면 변경
            if(Math.abs(sum) < check){
                check = Math.abs(sum);
                minNum = start;
                maxNum = end;
            }
            // 두 용액 더한값이 음수냐 양수냐 0 이냐에 따른 처리
            if(sum > 0) end--;
            else if(sum < 0) start++;
            else break;
        }
    }
}