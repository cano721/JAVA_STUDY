import java.io.*;
import java.util.*;

public class BJ11021_A_Plus_B_Minus_7 {
    public static void main(String[] args) throws IOException{
        // 입력값 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력값 한줄 인트형태로 치환 받기
        int t = Integer.parseInt(br.readLine());

        // 출력값 쓰기
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));


        for(int i = 1; i <= t; i++){
            // 입력값 한줄 ' ' 기준으로 나누기
            StringTokenizer st = new StringTokenizer(br.readLine()," ");

            //나눈 것들 인트형으로 받기
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            //출력값 쓰기
            bw.write("Case #" + i + ": " +(a+b) +"\n");
        }

        bw.flush();
        bw.close();
    }
}
