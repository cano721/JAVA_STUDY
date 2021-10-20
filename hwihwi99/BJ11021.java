import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ11021 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        int A,B,result;
        for(int i = 1; i<=T;i++){
            st = new StringTokenizer(br.readLine()," ");
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            result = A+B;
            System.out.println("Case #"+i+": "+result);
        }
    }
}
