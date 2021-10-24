import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BJ1316 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int ans = N;
        for(int i =0 ; i<N;i++){
            String str = br.readLine();
            ArrayList<Character> alpha = new ArrayList<>();
            alpha.add(str.charAt(0));
            for(int k = 1; k<str.length(); k++){
                if(str.charAt(k-1) == str.charAt(k)){
                    continue;
                }
                if(alpha.contains(str.charAt(k))){
                    ans--;
                    break;
                }
                alpha.add(str.charAt(k));
            }
        }
        System.out.println(ans);
    }
}
