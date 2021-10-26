import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String s = br.readLine();
        

        for (int i = 0; i < s.length(); i++) {
            boolean isPalin = true;
            
            for (int j = 0; i + j < (s.length() + i) / 2; j++) {
                if (s.charAt(i + j) != s.charAt(s.length() - j - 1)) {
                    isPalin = false;
                    break;
                }
            }
            if (isPalin) {
                System.out.println(i + s.length());
                break;
            }
        }

    }
}
