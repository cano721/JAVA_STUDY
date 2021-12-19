package cindya.bj4386_별자리만들기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 별의 위치를 저장하는 클래스
class Star{
    int number;
    double x, y;

    public Star(int number, double x, double y) {
        this.number = number;
        this.x = x;
        this.y = y;
    }

    // 다른 별과의 거리를 방환하는 함수
    public double getDistance(Star star){
        return Math.sqrt((Math.pow(this.x - star.x, 2) + Math.pow(this.y - star.y, 2)));
    }
}

// 별 사이의 선을 저장하는 클래스
class Line implements Comparable<Line>{
    int to;
    double distance;

    public Line(int to, double distance) {
        this.to = to;
        this.distance = distance;
    }

    // 거리를 기준으로 비교
    @Override
    public int compareTo(Line o) {
        return (int)(this.distance - o.distance);
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Star[] stars = new Star[n];
        boolean[] visit = new boolean[n];
        PriorityQueue<Line> lines = new PriorityQueue<>();
        double cost = 0;

        for(int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            stars[i] = new Star(i, Double.parseDouble(st.nextToken(" ")), Double.parseDouble(st.nextToken()));
        }
        br.close();

        visit[0] = true; // 첫번째 별을 방문 처리함
        // 첫번째 별과 다른 별 사이의 거리를 우선순위 큐에 삽입
        for(int i = 1; i < n; i++)
            lines.offer(new Line(i, stars[0].getDistance(stars[i])));

        while (!lines.isEmpty()){
            Line line = lines.poll(); // 가장 거리가 짧은 선을 꺼냄
            if(visit[line.to]) continue; // 이미 방문한 별이라면 넘어감
            visit[line.to] = true; // 아직 방문하지 않았다면 방문 처리를 하고
            cost += line.distance; // 거리를 총 비용에 더하고
            // 다음 별과 나머지 별 간의 거리를 우선순위 큐에 삽입
            for(int i = 0; i < n; i++){
                if(!visit[i])
                    lines.offer(new Line(i, stars[line.to].getDistance(stars[i])));
            }
        }
        System.out.println(String.format("%.2f",cost)); // 소수점 둘째자리까지 출력
    }
}
