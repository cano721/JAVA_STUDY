import java.util.*;
import java.io.*;

/* 
    집의 좌표를 정렬 시키고
    집을 돌면서 공유기 설치

    공유기를 설치할 거리를 이분탐색으로 정해서 설치

    공유기가 더 많이 설치되었다면 거리를 늘리기(거리를 늘릴때마다 정답으로 저장해야 최대값 저장)
    적게 설치되었으면 거리를 줄이기
*/

public class BJ2110_공유기설치 {

    public static int n,c,max,answer;
    public static ArrayList<Integer> homes = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        max = 0;
        for(int i = 0; i < n; i++){
            int home = Integer.parseInt(br.readLine());
            homes.add(home);
            max = Math.max(max,home);
        }
        Collections.sort(homes);

        binary_search();
        System.out.println(answer);
    }

    public static void binary_search(){
        int start = 1;
        int end = max- start;

        while(start <= end){
            int mid = (start + end) /2;
            int cnum = c-1;
            int phome = homes.get(0);
            for(int i = 1; i < homes.size(); i++){
                if(homes.get(i) - phome >= mid){
                    cnum--;
                    phome = homes.get(i);
                }
            }

            if(cnum > 0){
                end = mid-1;
            }else{
                start = mid+1;
                answer = mid;
            }
        }
    }
}