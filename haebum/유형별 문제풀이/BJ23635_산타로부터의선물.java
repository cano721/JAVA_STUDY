import java.util.*;
import java.io.*;

/**
 * 각 아이에게 분배되는 선물의 묶음 중 기쁨합계가 가장 작은값을 구하기
 * 선물은 1번선물부터 주어야하며 연속된 선물을 묶을 수 있고,
 * 다음 아이는 바로 다음 선물부터 묶어서 줘야함.(최소 1개의 선물)
 * 각 선물의 합계는 서로의 차이가 최소화되어야함.
 * 
 * 전체선물 묶음에서 선물 묶음의 기쁨합계가 가장 작은 값을 뺀 값이 정답
 * 
 * 정답을 mid로 정하고
 * 
 * left를 전체 선물에서 제일 작은 기쁨선물을 뺀 값
 * left = 0
 * right = 전체선물기쁨합계 -(제일작은기쁨선물*2)
 * 
 * 해당 mid값이 나왔을때를 기준으로
 * 
 * 다시 이분탐색을 돌리기
 * 선물 묶음의 기쁨합계가 가장 작은 값을 mid로 설정하고
 * 모든 아이가 못받는 조건이면 mid 줄이기.
 * 모든 아이가 받는다면 기쁨합계 증가.
 * 
 */
public class BJ23635_산타로부터의선물 {

    public static int k,n,min,max,answer;
    public static int[] gift;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        k = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        min = Integer.MAX_VALUE;
        
        gift = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i =0; i < n; i++){
            int happy = Integer.parseInt(st.nextToken());
            gift[i] = happy;
            min = Math.min(min,happy);
            max += happy;
        }

        binary_search();
        System.out.println(answer);
    }

    public static void binary_search(){
        int left = 0;
        int right = max-2*min;

        while(left <= right){
            int mid = (left+right)/2;

            if(present(mid)){
                answer = mid;
                right = mid-1;
            }else{
                left = mid+1;
            }
            
        }
    }

    public static boolean present(int minNum){

        int start = min;
        int end = max;

        while(start <= end){
            int mid = (start+end) /2;

            int child = 1;
            int childHappy = 0;
            int minHappy = Integer.MAX_VALUE;
            int checkMinNum = 0;

            ArrayList<Integer> giftPackage = new ArrayList<>();
            for(int i = 0; i < n; i++){
                if(childHappy < mid){
                    childHappy += gift[i];
                }else{
                    minHappy = Math.min(childHappy,minHappy);
                    giftPackage.add(childHappy);
                    childHappy = 0;
                    child++;
                }
            }

            for(int i = 0; i < giftPackage.size(); i++){
                checkMinNum += giftPackage.get(i) - minHappy;
            }

            // 다 돌았는데 모든아이가 받지 못했으면 mid 감소
            if(child < k){
                end = mid -1;
            }else{
                start = mid+1;
                if(checkMinNum == minNum){
                    return true;
                }
            }
        }
        return false;
    }
}
