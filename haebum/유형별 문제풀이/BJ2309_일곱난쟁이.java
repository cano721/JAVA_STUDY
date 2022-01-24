/*

    9개 중에 2개를 선택하여 그 두개를 전체합계에서 뺄 때
    100일 시 출력

    시간복잡도: 9*8*9 

*/


import java.util.*;
import java.io.*;

public class BJ2309_일곱난쟁이 {

    public static int[] arr,choice;
    public static void main(String[] args) throws IOException{
        
        arr = new int[9];
        choice = new int[7];
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int sum = 0;
        for(int i = 0; i<9; i++){
            int num = Integer.parseInt(br.readLine());
            arr[i] = num;
            sum += num;
        }

        Arrays.sort(arr);

        end:for(int i = 0; i <9; i++){
            for(int j = i+1; j <9; j++){
                if(sum - arr[i] - arr[j] == 100){
                    for(int v : arr){
                        if(v != arr[i] && v != arr[j]){
                            System.out.println(v);
                        }
                    }
                    break end;
                }
            }
        }
        
    }

}
