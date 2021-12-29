package cindya.bj2623_음악프로그램;

import java.io.*;
import java.util.*;

// 가수 클래스
class Singer implements Comparable<Singer>{
    int number, cnt = 0;
    List<Singer> nextSingers = new ArrayList<>();

    public Singer(int number) {
        this.number = number;
    }

    @Override
    public int compareTo(Singer o) {
        return this.cnt - o.cnt;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken());
        List<Singer> singers = new ArrayList<>();
        boolean[] visit = new boolean[n + 1];
        String answer = "";

        for(int i = 0; i <= n; i++)
            singers.add(new Singer(i));

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int cnt = Integer.parseInt(st.nextToken()), s = Integer.parseInt(st.nextToken());
            for(int j = 1; j < cnt; j++){
                int ns = Integer.parseInt(st.nextToken());
                singers.get(s).nextSingers.add(singers.get(ns)); // 이전 차례 nextSingers 다음 차례를 넣고
                singers.get(ns).cnt++; // 다음 차례 cnt를 증가
                s = ns;
            }
        }

        // 의미 없는 0번을 삭제
        singers.remove(0);

        Loop:
        while (!singers.isEmpty()){
            Collections.sort(singers); // cnt 순으로 정렬
            Singer singer = singers.remove(0); // 제일 cnt가 작은 가수를 꺼내고
            answer += singer.number + "\n"; // 출력
            visit[singer.number] = true; // 이미 줄 세웠다는 것을 표시

            // 다음 차례 가수들의 cnt를 감소
            for(Singer ns : singer.nextSingers){
                ns.cnt--;
                // 이미 줄을 서있으면 사이클이 생긴 것이므로 0 출력 후 종료
                if(visit[ns.number]){
                    answer = "0";
                    break Loop;
                }
            }
        }
        System.out.println(answer);
        br.close();
    }
}
