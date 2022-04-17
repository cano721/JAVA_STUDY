import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    static char[][] gears;
    static int[] pointer;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        gears = new char[4][8];
        pointer = new int[4];
        int sum =0;
        for(int i=0;i<4; i++){
            gears[i]= br.readLine().toCharArray();
        }
        int count = Integer.parseInt(br.readLine());
        for(int i =0; i<count; i++){
            String[] nums =br.readLine().split(" ");
            rotate(Integer.parseInt(nums[0])-1,Integer.parseInt(nums[1]),-1);
        }

        for(int i=0; i<4; i++){
            sum+= Character.getNumericValue(gears[i][pointer[i]])* (1<<i);
        }
        System.out.println(sum);
    }

    public static void rotate(int idx, int dir, int pre){
        if(idx>0&& pre!=idx-1){
           int lgear = (pointer[idx-1] + 2)%8;
           int rgear = (pointer[idx] - 2 + 8)%8;
           if(gears[idx-1][lgear] != gears[idx][rgear]) rotate(idx-1,-dir, idx);
        }

        if(idx<3&&pre!= idx+1){
            int lgear = (pointer[idx] + 2)%8;
            int rgear = (pointer[idx+1] - 2+8)%8;
            if(gears[idx][lgear] != gears[idx+1][rgear]) rotate(idx+1,-dir,idx);
        }

        pointer[idx] = (pointer[idx]+8 - dir)%8;
    }


 }
© 2022 GitHub, Inc.
Terms