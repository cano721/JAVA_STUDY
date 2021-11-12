package baekjoon.silverⅤ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
수강신청의 마스터 김종혜 선생님에게 새로운 과제가 주어졌다. 
김종혜 선생님한테는 Si에 시작해서 Ti에 끝나는 N개의 수업이 주어지는데, 최소의 강의실을 사용해서 모든 수업을 가능하게 해야 한다. 
참고로, 수업이 끝난 직후에 다음 수업을 시작할 수 있다. (즉, Ti ≤ Sj 일 경우 i 수업과 j 수업은 같이 들을 수 있다.)
수강신청 대충한 게 찔리면, 선생님을 도와드리자!

입력
첫 번째 줄에 N이 주어진다. (1 ≤ N ≤ 200,000)
이후 N개의 줄에 Si, Ti가 주어진다. (0 ≤ Si < Ti ≤ 109)

출력
강의실의 개수를 출력하라.

3
1 3
2 4
3 5

2

*/

class Lecture {
    int start;
    int end;

    Lecture(int start, int end) {
        this.start = start;
        this.end = end;
    }
}


public class Boj11000_강의실배정 {


    // 블로그 참조했고 이해했음. 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = stoi(br.readLine());
        
        //1. Input Data를 2차원 배열로 받는다. (new int[n][2])
        // int[][] arr = new int[N][2];
        StringTokenizer st;
        Lecture[] lectures = new Lecture[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int s = stoi(st.nextToken());
            int t = stoi(st.nextToken());
            // arr[i][0] = s;
            // arr[i][1] = t;
            lectures[i] = new Lecture(s, t);
        }
        //2. 입력 데이터를 오름차순으로 정렬해준다. (시작 시간이 같다면, 끝나는 시간을 오름차순으로 정렬)
        // Arrays.sort(arr, new Comparator<int []>() {
        //     @Override
        //     public int compare(int[] o1, int[] o2) {
        //         if (o1[0] == o2[0]) {
        //             return o1[1] - o2[1];
        //         } else {
        //             return o1[0] - o2[0];
        //         }
        //     }
        // });
        Arrays.sort(lectures, (o1, o2) -> o1.start == o2.start ? o1.end - o2.end : o1.start - o2.start);

        //3. PriorityQueue(우선순위 큐)를 만들어주고, (정렬된)배열의 첫 번째 end값을 큐에 넣는다.
        
        
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        // pq.add(arr[0][1]);
        pq.add(lectures[0].end);


       //4. 배열을 두 번째 값부터 순회하면서,
		// for(int i = 1; i < N; i++) {
		// 	//start가 PriorityQueue의 peek()보다 크거나 같다면, pq에서 하나 뺀다.
		// 	if(pq.peek() <= arr[i][0]) pq.poll();
			
		// 	//4-1. 순회하면서, 현재 end값을 새로 pq에 넣어준다.
		// 	pq.add(arr[i][1]);
		// }

        for(int i = 1; i < N; i++) {
			//start가 PriorityQueue의 peek()보다 크거나 같다면, pq에서 하나 뺀다.
			if(pq.peek() <= lectures[i].start) pq.poll();
			
			//4-1. 순회하면서, 현재 end값을 새로 pq에 넣어준다.
			pq.add(lectures[i].end);
		}
		
		//5. pq에 남아있는 데이터의 갯수가 필요한 강의실의 갯수이다.
		System.out.println(pq.size());
		br.close();




                
    }

    public static int stoi(String str) {return Integer.parseInt(str);}
}
