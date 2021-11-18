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


// 틀린 이유 찾아보기

// //배열 생성 및 담기
// ArrayList<Integer> arr1 = new ArrayList<>();
// ArrayList<Integer> arr2 = new ArrayList<>();
// for(int i = 0; i < n; i++){
//     int num = Integer.parseInt(st.nextToken());
//     if(num < 0) arr1.add(num);
//     else arr2.add(num);
// }

// // 두 배열 정렬
// Collections.sort(arr1,Collections.reverseOrder()); // 내림차순
// Collections.sort(arr2);

// // 포인터
// int mp = 0; // 음의 포인터
// int pp = 0; // 양의 포인터

// // 정답 포인터 담기
// int[] answer = new int[2];

// // 두용액의 차이
// int sum = Integer.MAX_VALUE;
// while(true){
//     // 포인터 범위 벗어나면 종료
//     if(mp == arr1.size() || pp == arr2.size()) break;

//     // 두 용액의 합
//     int SumSolution = arr1.get(mp) + arr2.get(pp);

//     if(sum > Math.abs(SumSolution)){
//         sum = Math.abs(SumSolution);
//         answer[0] = mp;
//         answer[1] = pp;
//     }
//     // 두용액의 합 음수면
//     if(SumSolution < 0){
//         pp++;
//     // 양수면
//     }else if(SumSolution > 0){
//         mp++;
//     }else{
//         break;
//     }
// }

// // 음밖에 없거나 양밖에 없는경우
// if(arr1.size() == 0){
//     bw.write(arr2.get(0) + " " + arr2.get(1));
// }else if(arr2.size() == 0){
//     // 음의 경우 내림차순 정렬 해놨기때문에 0번 인덱스가 나중에 나와야함.(오름차순 출력)
//     bw.write(arr1.get(1) + " " + arr1.get(0));
// // 둘다 있는 경우
// }else{
//     bw.write(arr1.get(answer[0]) + " " + arr2.get(answer[1]));
// }
// bw.flush();
// bw.close();
// }