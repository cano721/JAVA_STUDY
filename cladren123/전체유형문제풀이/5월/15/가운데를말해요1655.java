package studyGroup.may.may15;

import java.util.*;
import java.io.*;

/*

중간값 말하기
짝수라면 두 수 중에 작은 수

100,000을 매번 정렬하면 N * N * logn
시간초과가 발생한다.

최대힙과 최소힙을 이용한다.
우선순위큐
https://gh402.tistory.com/32

*/

public class 가운데를말해요1655 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> maxPQ = new PriorityQueue<>(Comparator.reverseOrder());
        PriorityQueue<Integer> minPQ = new PriorityQueue<>();

        for(int i = 0; i < n; i++)
        {
            int number = Integer.parseInt(br.readLine());

            if(maxPQ.size() == minPQ.size())
            {
                maxPQ.add(number);

                // 자리 교체
                if(!minPQ.isEmpty() && maxPQ.peek() > minPQ.peek()) {
                    minPQ.add(maxPQ.poll());
                    maxPQ.add(minPQ.poll());
                }
            }

            else {
                minPQ.add(number);

                // 자리 교체
                if (maxPQ.peek() > minPQ.peek()) {
                    minPQ.add(maxPQ.poll());
                    maxPQ.add(minPQ.poll());
                }
            }



            System.out.println(maxPQ.peek());

        }
    }

}
