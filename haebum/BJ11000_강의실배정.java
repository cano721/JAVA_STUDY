import java.util.*;
import java.io.*;

/*
    강의시간을 시작시간을 기준으로 리스트 정렬해두고(같다면 종료시간으로 정렬)

    우선순위큐에 종료시간을 기준으로 넣어둘거임.

    정렬된 1번 강의의 종료시간을 일단 우선순위 큐에 넣어두고

    2번강의부터 돌면서 우선순위큐 맨 앞의 종료시간보다 시작시간이 적다면

    우선순위큐에 넣고(뉴 강의실) 종료시간보다 시작시간이 크다면

    우선순위큐에서 하나 빼고 넣을거임!(쓰던 강의실 이어 쓰기)

    돌때마다 큐 사이즈를 맥스값과 체크해야한다고 생각했지만
    사용했던 강의실을 다 빼는게 아니라 새로운 강의를 파악할때
    뉴 강의실을 여느냐 아니면 기존 강의실을 재사용하느냐 체크하기때문에
    다 끝난후에 큐 사이즈를 파악해도 됨.
*/
public class BJ11000_강의실배정 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n][2];

        // 우선순위 큐 생성(종료시간만 넣을것)
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        // 배열 담기
        for(int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            
            int startTime = Integer.parseInt(st.nextToken());
            int endTime = Integer.parseInt(st.nextToken());

            arr[i][0] = startTime;      
            arr[i][1] = endTime;      
        }
        // 정렬 시작시간 기준(같으면 종료시간기준)
        Arrays.sort(arr, new Comparator<int[]>() {
            public int compare(int[] o1, int[] o2) {
                if(o1[0] == o2[0]) return o1[1] - o2[1];
                return o1[0] - o2[0];
            }
        });
        // 첫번째 값 넣어두기
        pq.offer(arr[0][1]);
        // 두번째부터 돌기
        for(int i = 1; i < n; i++){
            // 현재 강의실 종료시간보다 시작시간이 같거나 크면 기존강의실 재사용(한개 강의 종료)
            if(pq.peek() <= arr[i][0]) pq.poll();
            pq.offer(arr[i][1]);
        }
        // 사용한 강의실 개수 출력
        bw.write(pq.size() + "\n");

        // 다 출력 후 종료
        bw.flush();
        bw.close();
    }    
}
