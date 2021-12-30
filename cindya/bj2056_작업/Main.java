package cindya.bj2056_작업;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 작업 클래스
class Task implements Comparable<Task>{
    int number, time, cnt;
    List<Task> next = new ArrayList<>(), before = new ArrayList<>();

    public Task(int number, int time, int cnt) {
        this.number = number;
        this.time = time;
        this.cnt = cnt;
    }

    @Override
    public int compareTo(Task task) {
        return this.cnt - task.cnt;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        List<Task> tasks = new ArrayList<>();
        int[] time = new int[n + 1];

        for(int i = 1; i <= n; i++){
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken());
            Task task = new Task(i, t, m);
            tasks.add(task);
            for(int j = 0; j < m; j++) {
                int nt = Integer.parseInt(st.nextToken()) - 1;
                tasks.get(nt).next.add(task); // 이전 작업의 다음 작업으로 등록
                task.before.add(tasks.get(nt)); // 작업의 이전 작업 등록
            }
        }

        while (!tasks.isEmpty()){
            Collections.sort(tasks); // cnt 순으로 정렬
            Task task = tasks.remove(0); // 가장 첫번째 작업을 꺼냄
            // 이전 작업 루프를 돌며
            for(Task bt : task.before)
                // 이전 작업의 종료 시간이 가장 나중인 것을 선택
                time[task.number] = Math.max((time[task.number]), time[bt.number]);
            time[task.number] += task.time; // 작업 시간 더함

            // 다음 작업 루프를 돌며 cnt 감소
            for(Task nt : task.next)
                nt.cnt--;
        }

        // 가장 큰 값을 출력
        System.out.println(Arrays.stream(time).max().getAsInt());
        br.close();
    }
}
