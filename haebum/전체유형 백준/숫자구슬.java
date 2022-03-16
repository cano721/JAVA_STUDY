import java.util.*;
import java.io.*;

/**
 * 1. 이분탐색으로 최대값의 크기를 지정
 * 
 * 2. 그에 따른 그룹으로 나누기(각 그룹별 개수 체크)
 * 
 * 3. 최대값이 최소가 될 때 출력
 */

public class 숫자구슬 {

    public static int n,m, result;
    public static int[] groupList, beads;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] strs = br.readLine().split(" ");
        n = Integer.parseInt(strs[0]);
        m = Integer.parseInt(strs[1]);

        beads = new int[n];
        strs = br.readLine().split(" ");
        for(int i = 0; i < n; i++){
            beads[i] = Integer.parseInt(strs[i]);
        }

        groupList = new int[m];

        binarySearch();
        groupCheck();
        bw.write(result +"\n");

        for(int i = 0; i < m; i++){
            bw.write(groupList[i] + " ");
        }

        bw.flush();
        bw.close();
    }
	
    // 그룹 개수 맞추기(0 개수 그룹 없애기)
    public static void groupCheck(){
        int idx = 0;
        for(int i = 0; i < m; i++){
            if(groupList[i] == 0){
                idx = i-1;
                groupList[i]++;
                while(true){
                    if(groupList[idx] != 1){
                        break;
                    }
                    idx--;
                }
                groupList[idx]--;
            }
        }
    }

    public static void binarySearch(){
        int start = 1;
        int end = 100*n;
        
        while(start <= end){
            int mid = (start+end)/2; // 최대값

            if(isPossible(mid)){
                end = mid-1;
                result = mid;
            }else{
                start = mid+1;
            }
        }
    }

    public static boolean isPossible(int mid){
        int groupSum = 0; // 현재 그룹의 합
        int groupIdx = 0; // 그룹 idx

        int[] groupNum = new int[m]; // 그룹별 개수

        for(int i = 0; i < n; i++){
            if(mid < beads[i]){ // 현재 지정된 최대값보다 구슬숫자가 더 크면 false
                return false;
            }
            // 현재 그룹에 더해도 최대값이 안넘으면 더하기
            if(groupSum + beads[i] <= mid){
                groupSum += beads[i];
            }else{
                groupSum = beads[i];
                groupIdx++;
            }
            if(groupIdx == m){
                return false;
            }
            groupNum[groupIdx]++;
        }

        groupList = groupNum;
        return true;
    }
}