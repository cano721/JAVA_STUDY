import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class n2143_두배열의합 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        long T = Long.parseLong(br.readLine());
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        long[] a = new long[N];
        for (int i = 0; i < N; i++) {
			a[i] = Long.parseLong(st.nextToken());
		}
        
        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        long[] b = new long[M];
        for (int i = 0; i < M; i++) {
			b[i] = Long.parseLong(st.nextToken());
		}
        
        ArrayList<Integer> sumA = new ArrayList<Integer>();
        ArrayList<Integer> sumB = new ArrayList<Integer>();
        for (int i = 0; i < N; i++) {
			int sum = 0;
			for (int j = i; j < N; j++) {
				sum += a[j];
				sumA.add(sum);
			}
		}
        for (int i = 0; i < M; i++) {
			int sum = 0;
			for (int j = i; j < M; j++) {
				sum += b[j];
				sumB.add(sum);
			}
		}
        Collections.sort(sumA);
        Collections.sort(sumB);
        
        int answer = 0;
        int pa = 0; 
        int pb = sumB.size()-1;
        while(pa < sumA.size() && pb >= 0) {
        	long sum = sumA.get(pa) + sumB.get(pb);
        	if(sum == T) {
        		int aSum = sumA.get(pa);
        		int bSum = sumB.get(pb);
        		long aCnt = 0;
        		long bCnt = 0;
        		while(pa < sumA.size() && sumA.get(pa) == aSum) {
        			aCnt++;
        			pa++;
        		}
        		while(pb < sumB.size() && sumB.get(pb) == bSum) {
        			bCnt++;
        			pb++;
        		}
        		answer += aCnt * bCnt;
        	}
        	else if(sum < T) {
        		pa++;
        	}
        	else if(sum > T) {
        		pb--;
        	}
        }
        
        System.out.println(answer);
	}

}
