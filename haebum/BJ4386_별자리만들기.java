import java.io.*;
import java.util.*;

/**
 * 각 좌표별 별들을 저장해두고
 * 별들끼리 비교하여 간선 생성
 * 간선을 만든 후부터는 
 */
public class BJ4386_별자리만들기 {

    public static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());

        StringTokenizer st;
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            
            double x = Double.parseDouble(st.nextToken());
            double y = Double.parseDouble(st.nextToken());


        }
    }
}
