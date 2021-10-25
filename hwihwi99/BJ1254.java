import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BJ1254 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        int length = str.length();
        ArrayList<Character> ans = new ArrayList<>();
        for(int i = 0; i<str.length();i++){
            ans.add(str.charAt(i));
        }

        for(int i = 0; i<length;i++){
            char c =ans.get(i);
            char d = ans.get(ans.size()-i-1);
            if(c != d){
                ans.add(ans.size()-i,c);
            }
        }
        System.out.println(ans.size());


    }
}
