/**
 * 일정 높이일때, 최소한의 종유석+석순 파괴하는 개수 및 같은 개수일경우 몇개있는지 체크
 * 
 * 석순과 종유석을 분리하여 저장
 * 종유석 top[높이] = 개수
 * 석순 bottom[높이] = 개수
 * 
 * 석순과 종유석의 누적합 배열 생성
 * 
 * 1~높이를 돌며
 * 석순과 종유석을 몇개 부시는지 확인.
 * 
 * 그게 현재 정답값보다 적다면 변경하여 저장.
 */
import java.util.*;
import java.io.*;

public class BJ3020_개똥벌레 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());

        int[] top = new int[h+1];
        int[] bottom = new int[h+1];

        // 석순 종유석 높이 개수 체크
        for(int i = 0; i < n; i++){
            int height = Integer.parseInt(br.readLine());

            if(i %2 == 0){
                bottom[height]++;
            }else{
                top[height]++;
            }
        }

        // 누적합 배열 생성
        int[] sumTop = new int[h+1];
        int[] sumBottom = new int[h+1];

        int sumT = 0 , sumB = 0;
        for(int i = 1; i <= h; i++){
            sumT += top[i];
            sumB += bottom[i];

            sumTop[i] = sumT;
            sumBottom[i] = sumB;
        }

        int answer = n;
        int cnt = 0;

        // 개똥벌레 높이를 돌면서 파괴할 수 있는 석순,종유석 개수 파악
        for(int i = 1; i <= h; i++){
            // 전체개수에서 개똥벌레 높이보다 낮은 석순들은 빼기.
            int bottomTemp = sumBottom[h] - sumBottom[i-1];
            // 전체개수에서 개똥벌레 높이보다 위에있는 종유석들은 빼기
            int topTemp = sumTop[h] - sumTop[h-i];

            if(answer > bottomTemp + topTemp){
                answer = bottomTemp + topTemp;
                cnt = 1;
            }else if(answer == bottomTemp + topTemp){
                cnt++;
            }
        }

        System.out.println(answer + " " + cnt);
    }
}
