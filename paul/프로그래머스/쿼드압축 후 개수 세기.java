import java.util.Arrays;

public class Solution {
    
    private static int[] ans;
    
    public int[] solution(int[][] arr){
        ans = new int[2];
        div(0, 0, arr. length, arr);
        return ans;
    }

    public void div(int x, int y, int len, int[][] arr){

        boolean zero = true;
        boolean one = true;

        for (int i = x; i < x+len  ; i++) {
            for (int j = y; j <y+len; j++) {
                      if(arr[i][j] == 1) zero = false;
                      if(arr[i][j] == 0) one= false;
            }
        }


        if(one){
            ans[1]++;
            return;
        }

        if(zero){
            ans[0]++;
            return;
        }


        div(x,y,len/2,arr);
        div(x+len/2,y,len/2,arr);
        div(x,y+len/2,len/2,arr);
        div(x+len/2,y+len/2,len/2,arr);
    }

}