import java.io.*;
import java.util.*;

public class BJ1065_한수 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int answer = 0;

        // 한자리수나 두자리 수는 모두 한수임
        if (n <100){
            answer = n;
        // 그외
        }else{
            // 99까진 한수이므로 99개부터 시작
            answer = 99;
            // 한수 체크
            for (int i = 100; i <= n; i++){
                int check1 = i/100 - i%100/10;
                int check2 = i%100/10 - i%10;
                
                if (check1 == check2){
                    answer +=1;
                }
            }
        }
        System.out.println(answer);
    }
}
