import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());
        int[] top = new int[h+1];
        int[] bottom = new int[h+1];

        for (int i = 0; i < n; i++) {
            int idx = Integer.parseInt(br.readLine());
			if(i%2 == 0) {
				top[idx] += 1;
			}
			else bottom[idx] += 1;
        }

        for (int i = h-1; i >0; i--) {
            top[i] += top[i+1];
            bottom[i] += bottom[i+1];
        }

        int[] stone = new int[h+1];
        int min = Integer.MAX_VALUE;

        for (int i = 1; i <= h; i++) {
            stone[i] = bottom[i] + top[h-i + 1];
            min = Math.min(min, stone[i]);
        }
        int cnt = 0;
        for (int i = 1; i <= h; i++) {
            if(stone[i] == min)
                cnt++;
        }
        System.out.println(min + " " + cnt);        
    }
}