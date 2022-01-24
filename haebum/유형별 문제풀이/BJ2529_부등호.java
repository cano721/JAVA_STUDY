/*
    k범위 2~9

    숫자는 0~9까지

    백트래킹으로 숫자를 돌며
    사용했는지 여부와 각 부등호에 알맞는지 확인후
    최종 부등호 다 사용했을때 숫자를 비교하여 최소값 최대값 변경

    처음엔 맥스밸류와 민밸류를 설정하고 비교하여 저장할려고했지만 인트범위를 벗어남(21억)
    어레이리스트를 만들고 최종스트링값들을 다 넣은후 정렬하여
    맨 앞에값이 최소값, 맨 끝에 값이 최대값으로 뽑아냄.
*/


import java.util.*;
import java.io.*;
import java.math.BigInteger;

public class BJ2529_부등호 {

    public static int k;
    public static String minNum,maxNum;
    public static String[] arr;
    public static int[] used;
    public static ArrayList<String> arrayList = new ArrayList<>();

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        k = Integer.parseInt(br.readLine());
        
        // 배열크기 생성
        arr = new String[k];
        used = new int[10];
        
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        for(int i = 0; i < k; i++){
            String sign = st.nextToken();
            arr[i] = sign;
        }

        // 초기값 설정
        minNum = String.valueOf(Integer.MAX_VALUE);
        maxNum = String.valueOf(Integer.MIN_VALUE);
        
        // 백트래킹 돌기
        for(int i = 0; i< 10; i++){
            used[i] = 1;
            solve(0,i,String.valueOf(i));
            used[i] = 0;
        }
        Collections.sort(arrayList);
        //최대값
        System.out.println(arrayList.get(arrayList.size()-1));
        //최소값
        System.out.println(arrayList.get(0));
    }

    // 백트래킹
    public static void solve(int num,int pnum,String choiceNum){
        // 다 방문했으면 정답리스트에 담기
        if(num == k){
            arrayList.add(choiceNum);
            return;
        }

        // 숫자 돌기
        for(int i = 0; i < 10; i++){
            if(used[i] == 0){
                // 부등호에 알맞지않으면 고르지 않기
                if(arr[num].equals("<") && pnum > i){
                    continue;
                }else if(arr[num].equals(">") && pnum < i){
                    continue;
                }
                used[i] = 1;
                solve(num+1,i,choiceNum + String.valueOf(i));
                used[i] = 0;
            }
        }
    }
}
