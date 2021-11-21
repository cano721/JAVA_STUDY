import java.util.*;
import java.io.*;

public class Main {
    public static int n;
    public static ArrayList<Integer> arr = new ArrayList<>();
   
    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
    
    public static void input() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        for(int i =0; i<n; i++){
            arr.add(Integer.parseInt(st.nextToken()));
        }
    }

    public static void pro(){
        Collections.sort(arr);
        int left = 0, right = n-1, ans_left =0, ans_right = n-1;
        long ans = Long.MAX_VALUE, gap =0;
        while(left < right){
            gap = arr.get(right) + arr.get(left);

            if(Math.abs(gap) < ans){
                ans_left = left;
                ans_right = right;
                ans = Math.abs(gap);
            }

            if(gap == 0){
                ans_left = left;
                ans_right = right;
                break;
            }
            else if(gap > 0){
                right--;
            }else{
                left++;
            }
        }
        
        System.out.println(arr.get(ans_left) + " " + arr.get(ans_right));
    }

}
