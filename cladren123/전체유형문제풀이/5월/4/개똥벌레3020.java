package studyGroup.may.may4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import java.util.*;

/*
이분탐색
누적합
 */

public class 개똥벌레3020 {

    static int n,h;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        int[] bottom = new int[h+1];
        int[] top = new int[h+1];

        for(int i = 0; i < n/2; i++)
        {
            bottom[Integer.parseInt(br.readLine())] += 1;
            top[Integer.parseInt(br.readLine())] += 1;
        }

        int[] bottomSum = new int[h+1];
        int[] topSum = new int[h+1];

        for(int i = 1; i < h+1; i++)
        {
            bottomSum[i] = bottom[i] + bottomSum[i-1];
            topSum[i] = top[i] + topSum[i-1];
        }

        int answer = Integer.MAX_VALUE;
        int count = 0;

        for(int i = 1; i < h+1; i++)
        {
            int crush = 0;

            crush += bottomSum[h] - bottomSum[i-1];
            crush += topSum[h] - topSum[h-i];

            if(crush < answer)
            {
                answer = crush;
                count = 1;
            }
            else if(crush == answer)
            {
                count += 1;
            }
        }

        System.out.println(answer + " " + count);




    }


}
