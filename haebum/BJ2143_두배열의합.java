/**
 * 누적합 풀이
 * 
 * a의 누적합 배열을 만들고, b의 누적합 배열 만들기
 * 
 * t = a부분합 + b부분합이므로
 * 
 * map을 2개 만들어서 b와 a의 부분합을 다 저장해둠. ( key: 부분합값 value: 개수)
 * (2중포문 사용 10^6)
 *  
 * 한쪽 부분합map을 돌면서 t-현재 선택된 부분합이 다른 맵에 있을때,
 * 정답 += 부분합값의 개수*다른 부분합값의 개수
 */

import java.util.*;
import java.io.*;

public class BJ2143_두배열의합 {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
    public static void main(String[] args) throws IOException{
        
        int t = Integer.parseInt(br.readLine());
        long answer = 0;

        // 누적합 배열 만들기
        int n = Integer.parseInt(br.readLine());
        int[] sumA = makeSumArr(n);

        int m = Integer.parseInt(br.readLine());
        int[] sumB = makeSumArr(m);

        
        Map<Integer,Integer> mapA = makeMap(sumA);
        Map<Integer,Integer> mapB = makeMap(sumB);

        for(int Akey : mapA.keySet()){
            // t에서 a의 부분합을 뺀 값
            int cur = t - Akey;
            // b의 부분합이 있을때
            if(mapB.containsKey(cur)){
                // 개수의 곱 더하기
                answer += (long)mapA.get(Akey)*(long)mapB.get(cur);
            }
        }

        System.out.println(answer);

    }

    // 누적합 배열 만드는 함수
    public static int[] makeSumArr(int n) throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] sum = new int[n+1];
        for(int i = 1; i <= n; i++){
            int num = Integer.parseInt(st.nextToken());
            sum[i] = sum[i-1]+num;
        }

        return sum;
    }


    // 누적합의 부분합 맵에 저장 함수
    public static Map makeMap(int[] arr){
        Map<Integer,Integer> map = new HashMap<>();
        // 끝의 원소
        for(int i = 1; i < arr.length; i++){
            // 시작 원소
            for(int j = 0; j < i; j++){
                // 맵에 저장
                map.put(arr[i]-arr[j],map.getOrDefault(arr[i]-arr[j],0)+1);
            }
        }
        return map;
    }
}
