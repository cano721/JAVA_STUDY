import java.util.*;
import java.io.*;
public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
        
		int[] ans = new int[2];
		int[] arr = new int[n];
        
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		
		int left=0;
		int right=n-1;
		
		int max =Integer.MAX_VALUE;
        
		while(left<right) {
			int sum=arr[left]+arr[right];
			
			if(Math.abs(sum)<max) {
				ans[0]=left;
				ans[1]=right;
				max=Math.abs(sum);
              
			}
			if(sum>0)
				right-=1;
			else
				left+=1;
		}
		System.out.println(arr[ans[0]]+" "+arr[ans[1]]);	
	}
}