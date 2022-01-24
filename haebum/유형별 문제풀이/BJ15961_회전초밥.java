/*
    슬라이딩 윈도우로 범위를 정해놓고
    투포인터로 채워나가기
    범위만큼 찼다면 현재 먹은 개수 파악 및
    쿠폰 초밥이랑 겹치는지 확인 ( 해쉬맵을 사용해서 할거임)

    그 후 처음 고른 초밥 빼기 및 다음 초밥 확인

    초밥은 배열에서 고를건데 선형구조이지만
    반복이 안되는선에서 최대는
    마지막 고른 초밥에서 k개까지 고르는것이므로
    배열을 n+k개로 선언한 후
    0~k번째 초밥을 배열의 n번부터 다시 넣은다음에 돌릴 예정.
*/

import java.util.*;
import java.io.*;

public class BJ15961_회전초밥 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        //배열 담기
        int[] arr = new int[n+k];
        for(int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }
        // 선형구조 변형하기 위해 넣는 것!
        for(int i = 0; i < k; i++){
            arr[n+i] = arr[i];
        }
        
        // 고른 원소 담을 해쉬맵
        HashMap<Integer,Integer> map = new HashMap<>();

        // 투포인터 및 윈도우크기
        int start = 0;
        int end = 0;
        int window = k;

        // 정답
        int cnt = 0;

        while(true){
            
            // 윈도우크기만큼 골랐으면 확인
            if((end-start) == window){
                // 현재 고른 초밥의 종류
                int curCnt = map.size();
                // 현재 고른 초밥에 쿠폰 초밥이 없는가?
                if(map.get(c) == null){
                    curCnt++;
                }
                // 현재 종류의 개수가 최대값인지 확인
                cnt = Math.max(cnt,curCnt);

                // 다 찼으니까 맨 앞에꺼 빼서 다음꺼 넣어줄 준비
                // 1개밖에 안먹었으면 해쉬맵에서 삭제
                if(map.get(arr[start]) == 1){
                    map.remove(arr[start]);
                // 1개 이상이면 벨류 감소
                }else{
                    map.put(arr[start],map.get(arr[start])-1);
                }
                start++;
            }

            // 마지막초밥까지 다 확인해봤으면 탈출
            if(end == n+k) break;
            
            // 현재 초밥이 고르지 않은 종류면 해쉬맵에 추가
            if(map.get(arr[end]) == null){
                map.put(arr[end],1);
            // 현재 초밥이 기존에 골랐던 초밥이면 벨류 증가
            }else{
                map.put(arr[end],map.get(arr[end])+1);
            }
            end++;
        }

        System.out.println(cnt);

    }
}
