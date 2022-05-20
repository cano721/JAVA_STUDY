package elwlahd555.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class baekjoon11000_강의실_배정 {
	private static class Point{
		int start,end;

		public Point(int start, int end) {
			super();
			this.start = start;
			this.end = end;
		}
		
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int N=Integer.parseInt(br.readLine());
		Point study[]=new Point[N];
		StringTokenizer st=null;
		for (int i = 0; i < N; i++) {
			st=new StringTokenizer(br.readLine());
			study[i]=new Point(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
		}
        // 시작 시간을 기준으로 오름차순 정렬하되,
        // 시작 시간이 같다면, 종료 시간을 기준으로 오름차순 정렬한다.
		Arrays.sort(study, (l1, l2) -> l1.start == l2.start ? l1.end - l2.end : l1.start - l2.start);
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.offer(study[0].end);
        for (int i = 1; i < N; i++) {
            // 우선순위 큐에서 가장 작은 종료 시간과
            // 현재 lectures[i]의 시작 시간을 비교한다.
            if (pq.peek() <= study[i].start) {
                pq.poll();
            }
            pq.offer(study[i].end);
        }
        System.out.println(pq.size());
	}
}
