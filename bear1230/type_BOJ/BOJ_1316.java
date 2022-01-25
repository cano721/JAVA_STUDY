import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //n 단어갯수 입력
        int n = Integer.parseInt(br.readLine());
        int count = 0;
        for(int i =0; i<n; i++){
            //문자열
            String s = br.readLine();

            //알파벳
            int a[]  = new int[26];
            int check =0; 
            for (int j=0; j<s.length(); j++){
                int ca = s.charAt(j);
                if (a[ca - 'a'] == 0){
                    if (j < s.length() - 1) {
                        if (ca != s.charAt(j+1)){
                           
                            a[ca - 'a'] = 1;
                        }
                    } else {
                     
                        a[ca - 'a'] = 1;
                    }
                } else {
                    check = 1;
                }
            }
            
           
            if (check == 0) {
                count++;
            }
        }

        // 결과 출력
        System.out.println(count);
    }
}