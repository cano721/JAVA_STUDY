package studyGroup.may.may22;

import java.util.*;
import java.io.*;

/*

w <= p 이기 때문에
t-n일 째 되는 날부터 수확하자.

 */

public class 당근훔쳐먹기18234 {

    static int n; // 당근의 종류 수
    static long t; // 재배할 예정 일 수
    static ArrayList<carrot> carrots;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        t = Long.parseLong(st.nextToken());

        carrots = new ArrayList<>();

        for(int i = 0; i < n; i++)
        {
            st = new StringTokenizer(br.readLine());

            long w = Integer.parseInt(st.nextToken());
            long p = Integer.parseInt(st.nextToken());

            carrots.add(new carrot(w,p));
        }

        Collections.sort(carrots, (o1, o2) -> (int) (o1.p - o2.p));

        long answer = 0;

        for(int i = 0; i < n; i++)
        {
            long result = (i + t - n) * carrots.get(i).p + carrots.get(i).w;
            answer += result;
        }

        System.out.println(answer);


    }

    public static class carrot {

        long w;
        long p;

        carrot(long w, long p)
        {
            this.w = w;
            this.p = p;
        }


    }



}
