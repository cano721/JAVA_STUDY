package week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_1766 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] num = new int[N+1];
        int[] map = new int[N+1];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            num[A] = B;
        }
        ArrayList<Integer> arr = new ArrayList<>();


        for (int i = 1; i <= N; i++) {

            if(num[i]==0){
                arr.add(i);
                map[i] = arr.size()-1;
                System.out.println(i+" "+map[i]);
            }else{

                arr.add(map[num[i]],i);
                map[i] = map[num[i]];
                map[num[i]] = map[i]+1;

                System.out.println(i+" "+map[num[i]]);
            }
        }

        for (int i = 0; i < arr.size(); i++) {
            System.out.println(arr.get(i));
        }

    }
}
