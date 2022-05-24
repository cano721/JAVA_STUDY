package studyGroup.may.may23;

import java.util.*;
import java.io.*;

/*

100%에서 널포인트에러가 발생하는 이유

n 은 0 일 수도 있다.
우선순위 큐의 초기 크기를 0으로 설정하면 예외가 발생한다.

n = 0 일 때의 if문을 추가해서 예외처리를 해준다.

 */

public class 순회강연2109 {

    static int n; // 대학에서 제사 갯수
    static PriorityQueue<suggest> suggestList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        if(n == 0)
        {
            System.out.println(0);
            return;
        }

        // 여유 기간이 앞에 나오도록 우선순위 정렬
        suggestList = new PriorityQueue<>((o1, o2) -> o2.d - o1.d);

        for(int i = 0; i < n; i++)
        {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int p = Integer.parseInt(st.nextToken()); // 금액
            int d = Integer.parseInt(st.nextToken()); // 날짜

            suggestList.add(new suggest(p,d));
        }

        // 큰 값이 먼저 나오게 우선순위큐 생성
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());


        long answer = 0;

        int bigD = suggestList.peek().d;

        // 최대 일수부터 시작
        for(int i = bigD; i >= 1; i--)
        {

            while(!suggestList.isEmpty() && i == suggestList.peek().d)
            {
                suggest s = suggestList.poll();
                pq.add(s.p);
            }


            if(!pq.isEmpty())
            {
                answer += pq.poll();
            }
        }

        System.out.println(answer);

    }


    public static class suggest
    {
        int p;
        int d;

        suggest(int p, int d)
        {
            this.p = p;
            this.d = d;
        }
    }



}
