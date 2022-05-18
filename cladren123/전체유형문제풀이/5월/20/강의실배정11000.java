package studyGroup.may.may20;

// https://www.acmicpc.net/problem/11000

/*
https://velog.io/@jkh9615/%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98-%EB%B0%B1%EC%A4%80-11000-%EA%B0%95%EC%9D%98%EC%8B%A4-%EB%B0%B0%EC%A0%95-Java


시작시간순으로 오름차순 정렬 (시작시간이 같다면 종료시간을 오름차순으로 정렬)
정렬된 시간들 중 첫번째 종료시간을 우선순위큐에 넣는다.
배열의 두번째 값부터 순회하면서 시작시간의 우선순위큐에 시간보다 작거나 같으면 우선순위큐에서 뺀다.
순회하면서 현재의 end를 우선순위에 넣는다.
우선순위큐에 남아있는 데이터의 갯수가 필요한 강의실의 갯수이다.

poll()을 수행하는 것은 이전 강의실에 이어서 진행
end 값을 add 하는 것은 해당 강의실에 끝나는 시간이 갱신됨을 의미

최종적으로는 pq에는 각 강의실마다 끝나는 시간이 남아있게 되며, 이는 강의실의 갯수와 동일하다.

 */

import java.util.*;
import java.io.*;

public class 강의실배정11000 {

    static int n; // 강의 갯수
    static ArrayList<lecture> lec;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        lec = new ArrayList<>();

        for(int i = 0; i < n; i++)
        {
            StringTokenizer st = new StringTokenizer(br.readLine());
            lec.add(new lecture(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        // 정렬
        Collections.sort(lec, new Comparator<lecture>() {
            @Override
            public int compare(lecture o1, lecture o2)
            {
                if(o1.start == o2.start)
                {
                    return o1.end - o2.end;
                }

                return o1.start - o2.start;
            }
        });

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.add(lec.get(0).end);

        for(int i = 1; i < n; i++)
        {
            int start = lec.get(i).start;
            int end = lec.get(i).end;

            if(start >= pq.peek()) {
                pq.poll();
            }

            pq.add(end);
        }

        System.out.println(pq.size());

    }

    public static class lecture
    {
        int start;
        int end;

        lecture(int start, int end)
        {
            this.start = start;
            this.end = end;
        }
    }

}
