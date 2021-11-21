import java.io.*;
import java.util.*;

/*
    1~ 돌면서

    투포인터로 몸무게 제곱 차이 계산

    start를 기억하고 있던 몸무게
    end를 현재 몸무게

*/

public class BJ1484_다이어트 {

    public static int g;
    public static boolean flag = false;

    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        g = Integer.parseInt(br.readLine());
        // 두 수 비교
        twoPoint(1,1);
        // 알맞는 몸무게 없으면 -1 출력
        if(flag == false) bw.write(-1 + "\n");
        bw.flush();
        bw.close();
    }

    public static void twoPoint(int start, int end) throws IOException{
        while(true){
            // 한칸 아래와의 차이가 10만이 넘어가면 종료
            if(Math.pow(end,2) -Math.pow(end-1,2) > g) break;
            
            // 동일하면 출력 및 몸무게 있다고 flag 변환
            // 다음 몸무게찾으러 end 증가
            if(Math.pow(end,2) -Math.pow(start,2) == g){
                bw.write(end + "\n");
                flag = true;
                end++;
            }else if(Math.pow(end,2) -Math.pow(start,2) > g){
                start++;
            }else{
                end++;
            }
        }
    }
}
