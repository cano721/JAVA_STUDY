package day220521;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_11000_강의실배정 {

    static class ClassInfo {
        int s, t;

        public ClassInfo(int s, int t) {
            this.s = s;
            this.t = t;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        PriorityQueue<ClassInfo> pq = new PriorityQueue<>((o1, o2) -> o1.t - o2.t);
        int N = Integer.parseInt(br.readLine());
        ClassInfo[] ci = new ClassInfo[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            ci[i] = new ClassInfo(s, t);
        }
        Arrays.sort(ci, (o1, o2) -> {
            if (o1.s != o2.s) return o1.s - o2.s;
            else return o1.t - o2.t;
        });
        int answer = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            int s = ci[i].s;
            int t = ci[i].t;

            //1. 현재 수업의 시작시간 이하의 마무리시간을 가진 수업들은 다 종료시킨다.
            while (!pq.isEmpty() && pq.peek().t <= s) {
                pq.poll();
            }

            //2. 현재 수업을 넣는다.
            pq.offer(new ClassInfo(s, t));
            answer = Math.max(answer, pq.size());
        }

        System.out.println(answer);

    }
}
