import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st= new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken()) ;
        int[] house = new int[N];
        int answer = 0;
        for (int i = 0; i < N; i++) {
            house[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(house);
        int left = 1;
        int right = house[N - 1] - house[0];
        while (left <= right) {
            int mid = (left + right)/2; 
            int prevHouse = house[0];
            int count = 1; 
            
            for (int i = 0; i < N; i++) {
                int distance = house[i] - prevHouse;
                if (distance >= mid) { 
                    count++;
                    prevHouse = house[i];
                }
            }
            if (count >= C) { 
                left = mid + 1;
                answer = mid;
            } else {
                right = mid - 1;
            }
        }
        System.out.println(answer);

    }
}