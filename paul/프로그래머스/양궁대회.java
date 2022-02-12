//test case 8, 18 틀림
import java.util.*;
class Solution {
    static int[] list = new int[11];
    static int[] answer ;
    static int max = 0;
    
    public int[] solution(int n, int[] info) {
        dfs(n, info, 0);
        if(max == 0 ){
            int[] temp = {-1};
            return temp;
        }
        return answer;
    }
    
    static void dfs(int n, int[] info, int idx){
        
        if(n == 0 || idx == info.length){
            // 점수 계산.
            int gap = getPoint(info);
            //showResult(points);
            if(gap >= max){
                max = gap;
                answer = Arrays.copyOf(list, list.length);
                answer[10] +=n;
            }
            return;
        }
        
        if(n > info[idx]){
            list[idx] = info[idx]+1;
            dfs(n-info[idx]-1, info, idx+1);
            list[idx] = list[idx] - (info[idx]+1);
        }
        
        dfs(n, info, idx+1);   
    }
    
    static int getPoint(int[] info){
        // 0 : 어피치, 1: 라이언 
        int[] points = new int[2];
        for(int i =0; i< info.length; i++){
            int point = 10-i;
            if(info[i] == 0 && list[i] ==0) continue;
            else if(info[i] >= list[i]) points[0] += point;
            else points[1] += point;
        }
        
        return points[1] - points[0];
    }
    
    static void showResult(int[] gap){
        for(int i =0; i< list.length; i++){
            System.out.print(list[i] + " ");
        }
        System.out.println("->" + gap[0] +  ", " + gap[1]);
    }
}