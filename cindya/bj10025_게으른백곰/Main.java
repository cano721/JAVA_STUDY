package cindya.bj10025_게으른백곰;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 양동이 위치와 무게를 저장하는 클래스
class Bucket implements Comparable{
    int x, weight;

    public Bucket(int x, int weight) {
        this.x = x;
        this.weight = weight;
    }

    // 위치 기준으로 정렬하기 위한 compareTo
    @Override
    public int compareTo(Object o) {
        if(!(o instanceof Bucket)) return -1;
        return this.x - ((Bucket) o).x;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken(" ")), k = Integer.parseInt(st.nextToken());
        Bucket[] buckets = new Bucket[n];
        int left = 0, right = 0, max = 0, sum = 0;
        k = (k * 2) + 1; // k를 가능한 최대 폭으로 변경

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            int g = Integer.parseInt(st.nextToken(" ")), x = Integer.parseInt(st.nextToken());
            buckets[i] = new Bucket(x, g);
        }
        br.close();

        Arrays.sort(buckets); // 양동이를 위치 순으로 정렬

        while (right < n){ // 마지막 양동이에 도달할 때까지
            if(buckets[right].x - buckets[left].x > k){ // 폭이 가능한 범위 이상이면
                sum -= buckets[left++].weight; // sum에서 맨 앞 양동이 무게를 빼고 왼쪽 인덱스 증가
            }
            else{ // 가능한 범위 이내라면
                sum += buckets[right++].weight; // sum에 다음 양동이 무게 더하고 오른쪽 인덱스 증가
                max = Math.max(max, sum); // 새로운 sum과 max 중 더 큰 값 선택
            }
        }

        System.out.println(max);
    }
}
