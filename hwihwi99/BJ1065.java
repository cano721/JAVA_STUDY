import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ1065 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int ans = 0;
        if(N<100){
            ans = N;
        }
        else{
            for(int i = 100; i<=N;i++){
                if((i/100 + i % 10) == ((i % 100)/10 * 2)){
                    ans ++;
                }
            }
            ans += 99;
        }
        System.out.println(ans);
    }
}
