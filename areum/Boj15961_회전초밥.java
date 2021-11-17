import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj15961_회전초밥 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 접시 수
        int d = Integer.parseInt(st.nextToken()); // 초밥의 가짓수
        int k = Integer.parseInt(st.nextToken()); // 연속해서 먹는 접시의 수
        int c = Integer.parseInt(st.nextToken()); // 쿠폰 번호

        int[] arr = new int[N];
        int[] eat = new int[d+1]; // 초밥 먹은 개수 저장
        for(int n=0; n<N; n++) {
            arr[n] = Integer.parseInt(br.readLine());
        }

        int ans = 0;
        int cnt = 0;
        int start = 0;

        // 처음 k개의 수만큼 연속해서 먹기
        for(int i=0; i<k; i++) {
            // 한번도 먹은 적이 없는 초밥이라면 cnt++
            if(eat[arr[i]] == 0) cnt++;
            eat[arr[i]]++;
        }

        ans = cnt; // 처음부터 k개를 선택했을 때의 cnt
        for(int i=1; i<N; i++) {
            if(ans <= cnt) { // 최댓값 갱신
                if(eat[c] == 0) { // 쿠폰에 해당하는 초밥이 없다면,
                    ans = cnt + 1; // 쿠폰 해당하는 것까지 +1
                } else {
                    ans = cnt;
                }
            }

            // 맨 앞의 것을 빼주고
            eat[arr[i-1]]--;
            // 전에 먹었던 것이 없어진 경우 -1
            if(eat[arr[i-1]] == 0) cnt--;

            // 새로운 것 추가
            // (i+k+1)%N 으로 순환구조 만들기
            // 새로운 것이 먹은 적이 없다면, cnt+1;
            if(eat[arr[(i+k-1)%N]] == 0) cnt++;
            // 해당 초밥 +1
            eat[arr[(i+k-1)%N]]++;

        }

        System.out.println(ans);
    }
}
