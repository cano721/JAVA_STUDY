import java.io.*;
import java.util.ArrayList;


public class Main {
    static ArrayList<Integer> answerList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] info = br.readLine().split(" ");
        int M =Integer.parseInt(info[0]);
        int N =Integer.parseInt(info[1]);
        String[] numInfo = br.readLine().split(" ");
        int[] nums = new int[numInfo.length];
        int lo = 0;
        int hi = 0;
        int mid;
        for(int i=0; i<numInfo.length;i++){
            nums[i] = Integer.parseInt(numInfo[i]);
            lo = Integer.max(lo,nums[i]);
            hi += nums[i];
        }

        int ans = hi;
        while(lo<=hi){
            mid = (lo+hi)/2;
            int cnt = checkPossible(nums,mid, N,false);
            if(cnt > N) lo = mid + 1;
            else {
                hi = mid - 1;
                ans = Integer.min(ans,mid);
            }
        }
        mkAnswerList(nums,N,ans);
        System.out.println(ans);
        for(int i :answerList) System.out.print(i+" ");

    }
    public static void mkAnswerList(int[] nums, int N ,int mid){
        int sum =0;
        int cnt = 0;
        for(int i=0; i< nums.length;i++){
            sum+= nums[i];
            if(sum>mid) {
                N--;
                answerList.add(cnt);
                sum = nums[i];
                cnt =0;
            }

            cnt ++;
            if(nums.length - i == N) break;
        }
        answerList.add(cnt);
        while(--N>0) answerList.add(1);
    }
    public static int checkPossible(int[] nums,int mid,int N, boolean mode){
        int sum = 0;
        int cnt = 1;
        int pre = 0;
        for(int i=0 ; i<nums.length;i++){
            sum+= nums[i];
            if(sum>mid){
                sum = nums[i];
                cnt += 1;
            }
        }
        return cnt;
    }
}